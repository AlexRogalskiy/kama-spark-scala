package rdd

import org.apache.spark.sql.SparkSession

import scala.collection.immutable

object Ex8_Parse_CSV extends App {
  val spark = SparkSession.builder()
    .appName("DataFrames Basics")
    .config("spark.master", "local[2]")
    .getOrCreate()

  val sc = spark.sparkContext



}
