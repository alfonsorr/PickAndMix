package org.alfiler.pickandmix.spark.sdk.Actions

import org.alfiler.pickandmix.sdk.Sink
import org.apache.spark.rdd.RDD

trait RDDSink[T,E] extends Sink[RDD[T],E]
