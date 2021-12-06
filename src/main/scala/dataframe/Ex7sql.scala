package dataframe

import org.apache.spark.api.java.JavaRDD
import org.apache.spark.api.java.function.Function2
import org.apache.spark.sql.functions.avg
import org.apache.spark.sql.types.{DataTypes, IntegerType, StringType, StructField, StructType}
import org.apache.spark.sql.{Dataset, Row, RowFactory, SQLContext, SparkSession}

import java.util

object Ex7sql extends App {
  val spark = SparkSession.builder()
    .appName("DataFrames Basics")
    .config("spark.master", "local")
    .getOrCreate()

  
  val sc = spark.sparkContext


}
