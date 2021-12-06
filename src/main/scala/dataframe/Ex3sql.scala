package dataframe

import org.apache.spark.sql.SparkSession

object Ex3sql extends App {
  val spark = SparkSession.builder()
    .appName("DataFrames Basics")
    .config("spark.master", "local")
    .getOrCreate()


}
