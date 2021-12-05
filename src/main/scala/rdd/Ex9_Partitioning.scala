package rdd

import org.apache.spark.sql.SparkSession

object Ex9_Partitioning extends App {
  val spark = SparkSession.builder()
    .appName("DataFrames Basics")
    .config("spark.master", "local[2]")
    .getOrCreate()

  val sc = spark.sparkContext

  val cachedInts = sc.textFile("./ints", 4)
    .map(_.toInt)
    .cache()

  println()
  println("Number of partitions " + cachedInts.getNumPartitions)

  // Step 1: Transform each number to its square
  val squares = cachedInts.map(x => x * x)

  println()
  println("--Squares--")
  squares.collect().foreach(println)

  // Step 2: Filter even numbers
  val even = squares.filter(x => x % 2 == 0)

  println("--Even numbers--")
  even.collect().foreach(println)

  even.coalesce(2).glom().collect().foreach(e => println(e.mkString("Array(", ", ", ")")))
  println("Number of partitions " + even.partitions.length)

  even.coalesce(5).glom().collect().foreach(e => println(e.mkString("Array(", ", ", ")")))
  println("Number of partitions " + even.partitions.length)
  println(even.toDebugString)

  // Step - 3: Union with another RDD
  println("--Even and ints numbers");
  val union = even.union(cachedInts);
  union.repartition(7).glom().collect().foreach(e => println(e.mkString("Array(", ", ", ")")))
  println("Number of partitions " + union.partitions.length)
  // yeah, real empty partitions

  // Step - 4: Custom partitioner
  println("Custom partitioner");
  union
    .map(e => (e, e))
    .partitionBy(new EvenPartitioner(2))
    .glom()
    .collect()
    .foreach(e => println(e.mkString("Array(", ", ", ")")))

}
