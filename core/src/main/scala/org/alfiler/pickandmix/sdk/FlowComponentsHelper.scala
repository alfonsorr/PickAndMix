package org.alfiler.pickandmix.sdk

import org.alfiler.pickandmix.sdk.FlowElement.{Action, SinkAction}

trait BasicTransformation[O,D,E] extends Transformation[O,D,E]{
  override def apply(origin: FlowElement[O, E]): FlowElement[D, E] = origin.execute(action)
  def action:Action[O,D,E]
}

trait BasicSink[O,E] extends Sink[O,E]{
  def apply(origin:FlowElement[O,E]):E = origin.sink(action)
  def action:SinkAction[O,E]
}