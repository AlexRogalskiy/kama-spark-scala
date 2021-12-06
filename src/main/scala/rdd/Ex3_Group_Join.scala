package rdd

import org.apache.spark.rdd.RDD
import org.apache.spark.sql.SparkSession

object Ex3_Group_Join extends App {
  val spark = SparkSession.builder()
    .appName("DataFrames Basics")
    .config("spark.master", "local[2]")
    .getOrCreate()

  val sc = spark.sparkContext

}
