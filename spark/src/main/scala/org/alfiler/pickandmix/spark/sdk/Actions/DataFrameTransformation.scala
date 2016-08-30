package org.alfiler.pickandmix.spark.sdk.Actions

import org.alfiler.pickandmix.sdk.{BasicTransformation, Transformation}
import org.apache.spark.rdd.RDD
import org.apache.spark.sql.DataFrame

/**
  * Transformation from a DataFrame to other DataFrame
  */

trait DataFrameTransformation extends BasicTransformation[DataFrame, DataFrame, RDD[String]]
