package sber.elvira

import org.apache.spark.sql.SparkSession

object RddMain extends App{
    println("Hello")
  val spark = SparkSession.builder()
    .appName("DataFrames Basics")
    .config("spark.master", "local[2]")
    .getOrCreate()

  val sc = spark.sparkContext



}
