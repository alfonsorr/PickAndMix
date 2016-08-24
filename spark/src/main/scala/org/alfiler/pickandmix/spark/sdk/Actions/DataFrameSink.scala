package org.alfiler.pickandmix.spark.sdk.Actions

import org.alfiler.pickandmix.sdk.Sink
import org.apache.spark.sql.DataFrame

trait DataFrameSink[E] extends Sink[DataFrame,E]
