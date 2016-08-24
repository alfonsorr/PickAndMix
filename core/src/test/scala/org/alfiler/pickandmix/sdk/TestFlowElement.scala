package org.alfiler.pickandmix.sdk

case class TestFlowElement[O](ok:O, errors:List[String] = List.empty) extends FlowElement[O,List[String]]{
  override def handleErrors(possibleNewErrors: Option[List[String]]): List[String] = {
    possibleNewErrors.map(newErrors => errors ++ newErrors).getOrElse(errors)
  }
  override def newObject[R](ok: R, newErrors: List[String]): FlowElement[R, List[String]] = TestFlowElement(ok,newErrors)
}
