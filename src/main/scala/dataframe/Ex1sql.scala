package dataframe

import org.apache.spark.sql.{SaveMode, SparkSession}
import org.apache.spark.sql.types._

object Ex1sql extends App {
  val spark = SparkSession.builder()
    .appName("DataFrames Basics")
    .config("spark.master", "local")
    .getOrCreate()

}