package org.alfiler.pickandmix.spark.sdk

import org.alfiler.pickandmix.sdk.FlowElement
import org.apache.spark.rdd.RDD
import org.apache.spark.sql.DataFrame

object SparkFlowElement {
  type RDDFlowElement[T] = FlowElementErrorsInRDD[RDD[T]]
  type DataFrameFlowElement = FlowElementErrorsInRDD[DataFrame]
}

case class FlowElementErrorsInRDD[T](info:String, ok: T,errors:RDD[String]) extends FlowElement[T,RDD[String]]{
  override def handleErrors(newErrors: Option[RDD[String]]): RDD[String] = newErrors.map(ne => errors.union(ne)).getOrElse(errors)
  override def newObject[R](ok: R, newErrors: RDD[String]): FlowElement[R, RDD[String]] = FlowElementErrorsInRDD(info, ok, newErrors)
}
