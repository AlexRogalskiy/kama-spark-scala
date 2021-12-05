package dataframe

import org.apache.spark.api.java.function.{FilterFunction, MapFunction}
import org.apache.spark.sql.{Dataset, Encoders, SparkSession}

import java.io.Serializable

object Ex4sql extends App {
  val spark = SparkSession.builder()
    .appName("DataFrames Basics")
    .config("spark.master", "local")
    .getOrCreate()

  val personEncoder = Encoders.product[StateNamesBean]
  val nameYearEncoder = Encoders.product[NameYearBean]

  val stateNames = spark.read.parquet("./stateNames").as(personEncoder)
  stateNames.show()
  stateNames.printSchema()

  val filterFunction = (value: StateNamesBean) => value.gender == "F"
  val mapFunction = (value: StateNamesBean) => NameYearBean(value.name, value.cnt)

  val result = stateNames
    .filter(filterFunction)
    .map(mapFunction)(nameYearEncoder)
    .groupBy("name")
    .sum("cnt")

  result.show()
  result.explain(true)


  case class NameYearBean(name: String, cnt: Int)

  case class StateNamesBean(
                             id: Long = 0L,
                             name: String,
                             year: Long = 0L,
                             gender: String,
                             state: String,
                             cnt: Int
                           )


}
