package rdd

import org.apache.spark.sql.SparkSession

object Ex0 extends App {
  val spark = SparkSession.builder()
    .appName("DataFrames Basics")
    .config("spark.master", "local[2]")
    .getOrCreate()

  val ds = spark.range(100000000);
  println(s"Time is ${System.currentTimeMillis}")
  println(ds.count())
  println(s"Time is ${System.currentTimeMillis}")
  println(ds.count())
  println(s"Time is ${System.currentTimeMillis}")
}
