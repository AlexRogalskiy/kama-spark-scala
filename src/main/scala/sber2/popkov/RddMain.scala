package sber2.popkov

import org.apache.spark.sql.SparkSession

object RddMain extends App{
  val spark = SparkSession.builder()
    .appName("DataFrames Basics")
    .config("spark.master", "local[2]")
    .getOrCreate()

  val sc = spark.sparkContext

  // read from file
  val stateNamesCSV: RDD[String] = sc.textFile("src/main/resources/statenames.csv")

  // split / clean data
  val headerAndRows: RDD[List[String]] = stateNamesCSV.map(line => line.split(",").toList.map(x => x.trim))

  // get header
  val header: immutable.Seq[String] = headerAndRows.first().toList

  // filter out header (eh. just check if the first val matches the first header name)
  val data = headerAndRows.filter(x => x.head != header.head)

  println("Test")

}
