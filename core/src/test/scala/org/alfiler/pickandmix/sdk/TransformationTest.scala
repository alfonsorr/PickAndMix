package org.alfiler.pickandmix.sdk
import org.alfiler.pickandmix.sdk.FlowElement.Action
import org.scalatest.{FlatSpec, Matchers}

import scala.reflect.ClassTag
import scala.util.Try

class TransformationTest extends FlatSpec with Matchers{
  class StringToInt extends BasicTransformation[List[String],List[Int],List[String]] {
    override def action: Action[List[String], List[Int], List[String]] = {
      strings:List[String] => {
        val processed = strings.map(s => Try{s.toInt}.map(i => Right(i)).getOrElse(Left(s"""coudn't parse "$s" to integer""")))
        val (ok, errors) = processed.partition(_.isRight)
        (ok.map(_.right.get),Some(errors.map(_.left.get)))
      }
    }
  }

  class FiveDividedByElement extends BasicTransformation[List[Int],List[Int],List[String]] {
    override def action: Action[List[Int], List[Int], List[String]] = {
      ints:List[Int] => {
        val processed = ints.map(i => Try {
          5 / i
        }.map(j => Right(j)).getOrElse(Left(s"5 is not divisible by $i")))
        val (ok, errors) = processed.partition(_.isRight)
        (ok.map(_.right.get), Some(errors.map(_.left.get)))
      }
    }
  }

  "Transformation" should "transform according to the transformation used (string example)" in {
    val original = TestFlowElement(List("1","2","3"))
    val testSubject = new StringToInt
    testSubject(original).ok shouldBe List(1,2,3)
  }
  it should "transform according to the transformation used (division example)" in {
    val original = TestFlowElement(List(1,5))
    val testSubject = new FiveDividedByElement
    testSubject(original).ok shouldBe List(5,1)
  }
  it should "take the errors (string example)" in {
    val originalWithErrors = TestFlowElement(List("1","two","3"))
    val testSubject = new StringToInt
    testSubject(originalWithErrors).errors shouldBe List("""coudn't parse "two" to integer""")
  }
  it should "take the errors (int example)" in {
    val originalWithErrors = TestFlowElement(List(0))
    val testSubject = new FiveDividedByElement
    testSubject(originalWithErrors).errors shouldBe List("""5 is not divisible by 0""")
  }
  it should "mix and apply corretly" in {
    val original = TestFlowElement(List("1","5"))
    val testSubject1 = new StringToInt
    val testSubject2 = new FiveDividedByElement
    val testSubject = testSubject1 followedBy testSubject2
    testSubject(original).ok shouldBe List(5,1)
  }

}
