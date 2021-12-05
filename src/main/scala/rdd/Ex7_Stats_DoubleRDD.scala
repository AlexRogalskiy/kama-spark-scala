package rdd

import org.apache.spark.sql.SparkSession

object Ex7_Stats_DoubleRDD extends App {
  val spark = SparkSession.builder()
    .appName("DataFrames Basics")
    .config("spark.master", "local[2]")
    .getOrCreate()

  val sc = spark.sparkContext

  val vals = sc.parallelize(List(1.0, 1.0, 2.0, 2.0, 3.0, 150.0, 1.0, 2.0, 3.0, 2.0, 2.0, 1.0, 1.0, 1.0, -100.0, 2.0, 2.0, 3.0, 4.0, 1.0, 2.0, 3.0, 4.0), 3)
  val stats = vals.stats()
  val stddev = stats.stdev
  val mean = stats.mean
  println("Stddev is " + stddev + " mean is " + mean);

  val normalVals = vals.filter(x => Math.abs(x - mean) < 3 * stddev)
  normalVals.collect().foreach(println)
}
