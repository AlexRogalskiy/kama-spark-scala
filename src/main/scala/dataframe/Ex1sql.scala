package dataframe

import org.apache.spark.sql.{SaveMode, SparkSession}
import org.apache.spark.sql.types._

object Ex1sql extends App {
  val spark = SparkSession.builder()
    .appName("DataFrames Basics")
    .config("spark.master", "local")
    .getOrCreate()

  val stateNames = spark.read
    .option("header", "true")
    .option("inferSchema", "true")
    .csv("src/main/resources/stateNames/statenames.csv")

  stateNames.show()
  stateNames.printSchema()

  stateNames.write.mode(SaveMode.Overwrite).parquet("./stateNames")


  // Step - 2: In reality it can be too expensive and CPU-burst
  // If dataset is quite big, manual schema definition is preferred
  val fields = Array(
    DataTypes.createStructField("Id", LongType, true),
    DataTypes.createStructField("Year", IntegerType, true),
    DataTypes.createStructField("Gender", StringType, true),
    DataTypes.createStructField("Name", StringType, true),
    DataTypes.createStructField("Cnt", IntegerType, true))

  val nationalNamesSchema = DataTypes.createStructType(fields)
//  val nationalNamesSchema = StructType(fields)

  val nationalNames = spark.read
    .option("header", "true")
    .schema(nationalNamesSchema)
    //                .option("inferSchema", "true")
    .csv("./NationalNames.csv")

  nationalNames.show();
  nationalNames.printSchema();
  nationalNames.write.mode(SaveMode.Overwrite).json("./nationalNames")
  nationalNames.write.mode(SaveMode.Overwrite).orc("./nationalNames-orc")
  nationalNames.write.mode(SaveMode.Overwrite).parquet("./nationalNames-pq")
  nationalNames.cache

  // Step - 3: Simple dataframe operations
  // Filter & select & orderBy
  nationalNames
    .where("Gender == 'M'")
    .select("Name", "Year", "Cnt")
    .orderBy("Name", "Year")
    .explain()

  // Registered births by year in US since 1880
  nationalNames
    .groupBy("Year")
    .sum("Cnt").as("Sum")
    .orderBy("Year")
    .explain()
}