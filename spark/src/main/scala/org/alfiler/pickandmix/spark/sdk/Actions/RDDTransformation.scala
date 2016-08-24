package org.alfiler.pickandmix.spark.sdk.Actions

import org.alfiler.pickandmix.sdk.Transformation
import org.apache.spark.rdd.RDD

/**
  * Transformation from a RDD to other RDD
  *
  * @tparam O type of the origin RDD content
  * @tparam D type of the RDD content of the result
  */
trait RDDTransformation[O,D] extends Transformation[RDD[O], RDD[D], RDD[String]]
