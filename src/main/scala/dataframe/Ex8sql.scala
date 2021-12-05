package dataframe

import org.apache.spark.sql.SparkSession
import org.apache.spark.storage.StorageLevel

object Ex8sql extends App {
  val spark = SparkSession.builder()
    .appName("DataFrames Basics")
    .config("spark.master", "local")
    .getOrCreate()

  val stateNames = spark.read.parquet("./stateNames")
  stateNames.show()
  stateNames.printSchema()

  // STEP - 1: Simple query without caching
  val query = stateNames.where("Year = 2014");
  query.show();
  query.explain(true)

  // Step - 2: Cache data and see new plan
  stateNames.persist(StorageLevel.MEMORY_ONLY_SER) // or cache
  query.show()
  query.explain(true)
  // you will see  InMemoryTableScan [Id#0, Name#1, Year#2, Gender#3, State#4, Count#5], [isnotnull(Year#2), (Year#2 = 2014)]
  // +- InMemoryRelation

  // Step - 3: Unpersist data and see the old plan
  stateNames.unpersist()
  query.show()
  query.explain(true)
  // no InMemory stuff as you see*/

  // Step - 4: The same picture with the SparkSQL
  stateNames.createOrReplaceTempView("stateNames")
  spark.sql("CACHE TABLE stateNames")
  // Get full list of boy names
  val sqlQuery = spark.sql("SELECT DISTINCT Name FROM stateNames WHERE Gender = 'M' ORDER BY Name")
  sqlQuery.show()
  sqlQuery.explain(true)

  spark.sql("UNCACHE TABLE stateNames")
  sqlQuery.show()
  sqlQuery.explain(true)



}
