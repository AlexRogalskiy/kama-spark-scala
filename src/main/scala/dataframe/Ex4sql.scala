package dataframe

import org.apache.spark.api.java.function.{FilterFunction, MapFunction}
import org.apache.spark.sql.{Dataset, Encoders, SparkSession}

import java.io.Serializable

object Ex4sql extends App {
  val spark = SparkSession.builder()
    .appName("DataFrames Basics")
    .config("spark.master", "local")
    .getOrCreate()


}
