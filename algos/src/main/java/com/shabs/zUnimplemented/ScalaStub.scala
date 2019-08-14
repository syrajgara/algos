package com.shabs.zUnimplemented

import scala.io.Source

object ScalaStub {

  def main(args: Array[String]): Unit = {

    //val (param0, param1) = (args(0), args(1))

    val m : Map[(String, String), Int] = Map( {("a", "c") -> 5} )

    val a = m.collect( {case (("a", _), _) => "a" } )
    System.out.println(a.isEmpty)

    val o: Option[String] = None
  }

  def wordCountSpark() = {

//    SPARK

//    val textFile = sc.textFile("hdfs://...")
//    val counts = textFile.flatMap(line => line.split(" "))
//      .map(word => (word, 1))
//      .reduceByKey(_ + _)
//    counts.saveAsTextFile("hdfs://...")
  }

  def wordCount() = {
    // approach #1
    Source.fromFile("file.txt")
      .getLines
      .flatMap(_.split("\\W+"))
      .foldLeft(Map.empty[String, Int]) {
        (wordMap, word) => wordMap + (word -> (wordMap.getOrElse(word, 0) + 1))
      }

    // approach #2 - better
    Source.fromFile("file.txt")
      .getLines
      .flatMap(_.split("\\W+"))
      .toList
      .groupBy(word => word)
      .mapValues(_.length)
  }
}
