package sber.katkov

import org.apache.spark.sql.SparkSession

object rddAndrew extends App {
  println("Hello, it's Andrew's scope :)")

  val spark = SparkSession.builder()
    .appName("DataFrames Basics")
    .config("spark.master", "local[2]")
    .getOrCreate()

  val sc = spark.sparkContext
}
