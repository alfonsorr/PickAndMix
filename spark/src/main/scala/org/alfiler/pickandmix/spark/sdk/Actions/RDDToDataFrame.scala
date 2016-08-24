package org.alfiler.pickandmix.spark.sdk.Actions

import org.alfiler.pickandmix.sdk.Transformation
import org.apache.spark.rdd.RDD
import org.apache.spark.sql.DataFrame

/**
  * Transformation from a RDD element to a DataFrame
  *
  * @tparam O type of the origin RDD content
  */

trait RDDToDataFrame[O] extends Transformation[RDD[O],DataFrame, RDD[String]]
