package org.alfiler.pickandmix.sdk

import org.alfiler.pickandmix.sdk.FlowElement.{Action, ErrorAction, SinkAction}

object FlowElement {
  type Action[T,R,E] = T => (R,Option[E])
  type SinkAction[T,E] = T => Option[E]
  type ErrorAction[E,ED] = E => ED
}


trait FlowElement[T,E] {
  val ok:T
  val errors:E
  def execute[R](action: Action[T,R,E]):FlowElement[R,E] = {
    val (result, errors) = action(ok)
    newObject[R](result,handleErrors(errors))
  }
  def sink[R](action: SinkAction[T,E]):E = {
    val errors = action(ok)
    handleErrors(errors)
  }
  def handleErrors(newErrors:Option[E]):E
  def newObject[R](ok:R,newErrors:E):FlowElement[R,E]
  def execute[ED](errorTransformation:ErrorAction[E,ED]):FlowElement[T,ED]
}


