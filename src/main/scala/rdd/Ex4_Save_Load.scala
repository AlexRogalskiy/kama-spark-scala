package rdd

import org.apache.hadoop.io.{BytesWritable, NullWritable, Text}
import org.apache.spark.sql.SparkSession

object Ex4_Save_Load extends App {
  val spark = SparkSession.builder()
    .appName("DataFrames Basics")
    .config("spark.master", "local[2]")
    .getOrCreate()

  val sc = spark.sparkContext

}
