package rdd

import org.apache.spark.sql.SparkSession

object Ex5_Sets extends App {
  val spark = SparkSession.builder()
    .appName("DataFrames Basics")
    .config("spark.master", "local[2]")
    .getOrCreate()

  val sc = spark.sparkContext


}
