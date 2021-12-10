package sber.vopolski

import org.apache.spark.SparkContext
import org.apache.spark.rdd.RDD
import org.apache.spark.sql.{DataFrame, SparkSession}

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

object RddMain extends App {

  import model._

  implicit val spark = SparkSession.builder()
    .appName("DataFrames Basics")
    .config("spark.master", "local[2]")
    .getOrCreate()

  implicit val sc: SparkContext = spark.sparkContext

  import readWriteUtils._

//  val dataRdd = readCsvToRdd("""C:\Users\Vadim\IdeaProjects\kama-spark-scala\src\main\resources\taxi_zones.csv""")


//  dataRdd.collect().foreach(x => println(x))
//

  val taxiEventsDF: DataFrame = spark
    .read
    .load("C:\\Users\\Vadim\\IdeaProjects\\kama-spark-scala\\src\\main\\resources\\yellow_taxi_jan_25_2018")

//  implicit val enc: Encoder[TaxiRideEvents] = Encoders.product[TaxiRideEvents]
  import spark.implicits._

  val taxiEventsDS = taxiEventsDF
    .as[TaxiRideEvents]

  taxiEventsDS
    .map(x => x.fare_amount)

  val taxiRideRdd: RDD[TaxiRideEvents] = taxiEventsDS.rdd

  def getHour(dateTime: String): Int = {
    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
    val parsedDateTime = LocalDateTime.parse(dateTime, formatter)
    parsedDateTime.getHour
  }

  val value = taxiRideRdd
    .map(e => (getHour(e.tpep_pickup_datetime), 1))
    .reduceByKey((t1, t2) => t1 + t2)
    .sortBy ({case (_, c)=> c} , false)

  value.take(20).foreach(x => println(s"${x._1} : ${x._2}"))
  
  

}
