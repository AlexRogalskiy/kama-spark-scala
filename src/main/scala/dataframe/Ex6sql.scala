package dataframe

import org.apache.spark.sql.{Dataset, Row, SparkSession}

object Ex6sql extends App {
  val spark = SparkSession.builder()
    .appName("DataFrames Basics")
    .config("spark.master", "local")
    .getOrCreate()

  val stateNames = spark.read.parquet("./stateNames")
  stateNames.show()
  stateNames.printSchema()

  stateNames.cache

  // Step-1: Use the same UDF in SQL expression
  stateNames.createOrReplaceTempView("stateNames")

  // Step-2: Get names with max length by year
  spark.sql("SELECT Year, MAX(Name) FROM stateNames GROUP BY Year ORDER BY Year").show(100) // <= this approach doesn't work as we wish due to lexicographical order


  // Step-3: Register UDAF function
  spark.udf.register("LONGEST_WORD", new LongestWord)

  // Step-4: Get pairs <Year, Name with max length>
  spark.sql("SELECT Year, LONGEST_WORD(Name) FROM stateNames GROUP BY Year ORDER BY Year").show(100)

}
