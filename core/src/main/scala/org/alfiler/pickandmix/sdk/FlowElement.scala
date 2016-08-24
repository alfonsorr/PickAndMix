package org.alfiler.pickandmix.sdk

import org.alfiler.pickandmix.sdk.FlowElement.Action

object FlowElement {
  type Action[T,R,E] = T => (R,Option[E])
}


trait FlowElement[T,E] {
  val ok:T
  val errors:E
  def execute[R](action: Action[T,R,E]):FlowElement[R,E] = {
    val (result, errors) = action(ok)
    newObject[R](result,handleErrors(errors))
  }
  def handleErrors(newErrors:Option[E]):E
  def newObject[R](ok:R,newErrors:E):FlowElement[R,E]
}


