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

  val salariesData: List[String] = List("John 1900 January", "Mary 2000 January", "John 1800 February", "John 1000 March", "Mary 1500 February", "Mary 2900 March")

  // Creates RDD with 3 parts
  val salaries = sc.parallelize(salariesData, 3).map(s => s.split(" "));

  val reduceFunc = (t1: (Double, Int), t2: (Double, Int)) => (t1._1 + t2._1, t1._2 + t2._2)

  //  The logic is 'case (key, (num, count)) => (key, num / count)'
  val mapFunc = (tuple: (String, (Double, Int))) => (tuple._1, tuple._2._1 / tuple._2._2)


  salaries
    .map(x => (x(0), (x(1).toDouble, 1)))
    .reduceByKey(reduceFunc)
    .map(mapFunc)
    .collect()
    .foreach(println)

  val rowRdd = salaries.map(x => RowFactory.create(x(0), x(1)))
  rowRdd.toDebugString
  rowRdd.collect().foreach(println)

  val fields = Array(
    DataTypes.createStructField("name", StringType, true),
    DataTypes.createStructField("amount", IntegerType, true))

  val salarySchema = DataTypes.createStructType(fields)

  val df = spark.sqlContext.createDataFrame(rowRdd, salarySchema);

  df.groupBy("name")
    .agg(avg("amount"))
    .show();


}
