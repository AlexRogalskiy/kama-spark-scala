package dataframe

import org.apache.spark.sql.{RowFactory, SparkSession}
import org.apache.spark.storage.StorageLevel

object Ex9sql extends App {
  val spark = SparkSession.builder()
    .appName("DataFrames Basics")
    .config("spark.master", "local")
    .getOrCreate()

  val sc = spark.sparkContext

  val data = 0 to 10000000
  // Make RDD
  val intRDD = sc.parallelize(data.toList, 1024);
  val countRDD = intRDD.persist(StorageLevel.MEMORY_ONLY).count
  println(String.valueOf(countRDD));

//  val rowRdd = intRDD.map(RowFactory.create)
//  rowRdd.toDebugString
//
//  StructField[] fields = new StructField[] {
//    DataTypes.createStructField("value", IntegerType, true)};
//
//  StructType schema = DataTypes.createStructType(fields);
//
//  Dataset<Row> dataframe = new SQLContext(jsc).createDataFrame(rowRdd, schema);
//
//  long countDF = dataframe.persist(StorageLevel.MEMORY_ONLY()).count();
//  println(String.valueOf(countDF));
//
//  while (true) {
//
//  }
//

}
