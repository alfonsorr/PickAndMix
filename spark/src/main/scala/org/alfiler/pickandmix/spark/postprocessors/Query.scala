package org.alfiler.pickandmix.spark.postprocessors

import com.typesafe.config.Config
import org.alfiler.pickandmix.PAMConfigElementConstructor
import org.alfiler.pickandmix.spark.sdk.Actions.DataFrameTransformation
import org.apache.spark.sql.DataFrame

object QueryConfig{
  def apply(config: Config):QueryConfig = {
    val query = config.getString("query")
    QueryConfig(query)
  }
}

case class QueryConfig(query:String)

object Query extends PAMConfigElementConstructor[DataFrameTransformation] {
  override val ELEMENT_TYPE_TAG: String = "query"

  override def factoryFromConfiguration(config: Config): DataFrameTransformation = {
    new Query(QueryConfig(config))
  }

  override def configExample: String =
    """{
      |  type = "query"
      |  query = "select * from table"
      |}""".stripMargin
}

class Query(config: QueryConfig) extends DataFrameTransformation{
  def action[RDD[String]] = {
    dataframe:DataFrame =>
      (dataframe.sqlContext.sql("hola"),None)
  }
}
