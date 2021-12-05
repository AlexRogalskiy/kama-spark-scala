package rdd

import org.apache.spark.sql.SparkSession

import scala.collection.immutable

object Ex8_Parse_CSV extends App {
  val spark = SparkSession.builder()
    .appName("DataFrames Basics")
    .config("spark.master", "local[2]")
    .getOrCreate()

  val sc = spark.sparkContext

  // read from file
  val stateNamesCSV = sc.textFile("./StateNames.csv")

  // split / clean data
  val headerAndRows = stateNamesCSV.map(line => line.split(",").toList.map(x => x.trim))

  // get header
  val header: immutable.Seq[String] = headerAndRows.first().toList

  // filter out header (eh. just check if the first val matches the first header name)
  val data = headerAndRows.filter(x => x.head != header.head)

  // splits to map (header/value pairs)
  val stateNames = data.map(splits => splits.zip(header).map { case (x, y) => x -> y }.toMap)

  // print top-5
  stateNames.take(5).foreach(println)

  // stateNames.collect // Easy to get java.lang.OutOfMemoryError: GC overhead limit exceeded

  // you should worry about all data transformations to rdd with schema
  //  stateNames
  //    .filter(e => e.get("Name") == "Anna" && e.get("Cnt").map(x => ) > 100)
  //    .take(5)
  //    .forEach(System.out::println);


}
