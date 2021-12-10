package sber.vopolski

import org.apache.spark.SparkContext
import org.apache.spark.rdd.RDD

object readWriteUtils {

  import model._

  def readCsvToRdd(path: String)(implicit context: SparkContext) :RDD[TaxiZone] = {
    val stateNamesCSV: RDD[String] = context
      .textFile(path)

    val headerAndRows: RDD[List[String]] = stateNamesCSV
      .map(line => line.split(",").toList.map(x => x.trim))

    val header: Seq[String] = headerAndRows.first()

    val data: RDD[TaxiZone] = headerAndRows
      .filter(x => x.head != header.head)
      .map{x =>
        val array = x.toArray
        TaxiZone(
          LocationID = array(0).toInt,
          Borough = array(1),
          Zone = array(2),
          service_zone = array(3)
        )
      }

    data
  }

}
