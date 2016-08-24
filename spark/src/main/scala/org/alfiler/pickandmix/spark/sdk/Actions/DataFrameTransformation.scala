package org.alfiler.pickandmix.spark.sdk.Actions

import org.alfiler.pickandmix.sdk.Transformation
import org.apache.spark.rdd.RDD
import org.apache.spark.sql.DataFrame

/**
  * Transformation from a DataFrame to other DataFrame
  */

trait DataFrameTransformation extends Transformation[DataFrame, DataFrame, RDD[String]]
