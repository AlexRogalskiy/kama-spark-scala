package sber2.saberov

import org.apache.spark.sql.SparkSession

object RddMain extends App{
  val spark = SparkSession.builder()
    .appName("DataFrames Basics")
    .config("spark.master", "local[2]")
    .getOrCreate()

  val sc = spark.sparkContext


  println("Test")

}
