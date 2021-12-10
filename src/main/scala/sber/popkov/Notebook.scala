package sber.popkov

import org.apache.spark.sql.SparkSession

object Notebook {
  val spark = SparkSession.builder()
    .appName("DataFrames Basics")
    .config("spark.master", "local[2]")
    .getOrCreate()

  val sc = spark.sparkContext
}
