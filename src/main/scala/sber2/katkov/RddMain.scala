package sber2.katkov

import org.apache.spark.rdd.RDD
import org.apache.spark.sql.SparkSession

import scala.collection.immutable

object RddMain extends App{
  val spark = SparkSession.builder()
    .appName("DataFrames Basics")
    .config("spark.master", "local[2]")
    .getOrCreate()

  val sc = spark.sparkContext


  println("Andrew starts here")

  val stateNamesCSV: RDD[String] = sc.textFile("src/main/resources/data/taxi_zones.csv")

  // split / clean data
  val headerAndRows: RDD[List[String]] = stateNamesCSV.map(line => line.split(",").toList.map(x => x.trim))

  // get header
  val header: immutable.Seq[String] = headerAndRows.first().toList

  // filter out header (eh. just check if the first val matches the first header name)
  val data = headerAndRows.filter(x => x.head != header.head)

  // splits to map (header/value pairs)
  val stateNames = data.map(splits => splits.zip(header).map { case (x, y) => y -> x }.toMap)

}
