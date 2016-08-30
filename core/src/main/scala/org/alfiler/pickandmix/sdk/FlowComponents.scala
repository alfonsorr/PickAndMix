package org.alfiler.pickandmix.sdk


/**
  * Base trait for a transformation from a element to other
  */

sealed trait PartialFlow

trait Transformation[O,D,E] extends PartialFlow{
  def apply(origin:FlowElement[O,E]):FlowElement[D,E]
  def followedBy[ND](next:Transformation[D,ND,E]):Transformation[O,ND,E] = {
    val actual = this
    new Transformation[O,ND,E] {
      val first = actual
      val second = next
      override def apply(origin: FlowElement[O, E]): FlowElement[ND, E] = {
        val resultOfFirst = first(origin)
        second(resultOfFirst)
      }
    }
  }
}

trait Sink[D,E] extends PartialFlow{
  def apply(origin:FlowElement[D,E]):E
}

trait Source[O,E] extends PartialFlow{
  def apply():FlowElement[O,E]
  def followedBy[ND](next:Transformation[O,ND,E]):Source[ND,E] = {
    val mainSource = this
    new Source[ND,E] {
      override def apply(): FlowElement[ND, E] = {
        val element = mainSource.apply()
        next(element)
      }
    }
  }
  def followedBy[ND](next:Sink[O,E]):FullFlow[E] = {
    val mainSource = this
    new FullFlow[E] {
      override def apply(): E = {
        val elementToSink = mainSource()
        next(elementToSink)
      }
    }
  }
}

trait TransformationOfErrors[O,E,ED] extends PartialFlow{
  def apply(origin:FlowElement[O,E]):FlowElement[O,ED]
}

trait FullFlow[E] {
  def apply():E
}

