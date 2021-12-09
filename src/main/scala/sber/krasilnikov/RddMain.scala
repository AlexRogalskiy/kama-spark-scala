package sber.krasilnikov
import org.apache.spark.sql.SparkSession

object RddMain {
  val spark = SparkSession.builder()
    .appName("DataFrames Basics")
    .config("spark.master", "local[2]")
    .getOrCreate()

  val sc = spark.sparkContext
}
