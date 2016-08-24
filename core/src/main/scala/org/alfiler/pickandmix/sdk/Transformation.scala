package org.alfiler.pickandmix.sdk

import org.alfiler.pickandmix.sdk.FlowElement.Action

/**
  * Base trait for a transformation from a element to other
  */

trait Transformation[O,D,E] {
  def apply(origin:FlowElement[O,E]):FlowElement[D,E] = origin.execute(action)
  def action:Action[O,D,E]
  def followedBy[ND](next:Transformation[D,ND,E]):Transformation[O,ND,E] = {
    val actual = this
    new Transformation[O,ND,E] {
      val first = actual
      val second = next
      override def action: Action[O, ND, E] = ???
      override def apply(origin: FlowElement[O, E]): FlowElement[ND, E] = {
        val resultOfFirst = first(origin)
        second(resultOfFirst)
      }
    }
  }
}

trait SinkSingle[T] {
  def apply(elementToSink:T):Unit
}

trait Sink[T,E] {
  def apply(origin:FlowElement[T,E]):Unit = {

  }
}



