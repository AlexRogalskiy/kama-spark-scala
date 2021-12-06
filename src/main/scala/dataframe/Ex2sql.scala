package dataframe

import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions._

object Ex2sql extends App {
  val spark = SparkSession.builder()
    .appName("DataFrames Basics")
    .config("spark.master", "local")
    .getOrCreate()


}
