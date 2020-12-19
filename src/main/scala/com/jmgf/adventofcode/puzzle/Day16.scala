package com.jmgf.adventofcode.puzzle

import com.jmgf.adventofcode.Puzzle
import com.jmgf.adventofcode.util.File

import scala.util.Try

object Day16 extends Puzzle[Seq[String], Int, BigInt] {

  var correctValues:Seq[(String, Seq[Int], Seq[Int])] = Seq();
  var myTicket: Seq[Int] = Seq();
  var nearbyTickets: Seq[Seq[Int]] = Seq();


  override def parse(resource: String): Seq[String] =
    File.readResource(resource).flatMap(row => Try(row.toString).toOption)

  override def part1(inputs: Seq[String]): Int = {
    initializeVariables(inputs)
    val allValues = correctValues.flatMap(a => a._2.union(a._3).distinct)
    nearbyTickets.flatMap(_.diff(allValues)).sum
  }

  override def part2(inputs: Seq[String]): BigInt = {
    initializeVariables(inputs)
    val allValues = correctValues.flatMap(a => a._2.union(a._3).distinct)
    val validTickets = nearbyTickets.filter(_.diff(allValues).isEmpty)
    val columnsToGet = correctValues.filter(_._1.startsWith("departure"))

    val indexes = columnsToGet.map(column => {
      val map = validTickets.head.indices.map(index => {
        index -> validTickets.count(row => column._2.contains(row(index)))
      }).toMap
      println("hello")
      println(map.maxBy(_._2))
      println(map.maxBy(_._2)._1)
      1
    })

    indexes.map(myTicket(_)).product
  }

  private def initializeVariables(inputs: Seq[String]): Unit = {
    val inputProcesed = processInput(inputs)
    correctValues = inputProcesed.head.map(line => {
      val split = line.split(": | or |-")
      (split(0), split(1).toInt to split(2).toInt, split(3).toInt to split(4).toInt)
    })
    myTicket = inputProcesed(1).last.trim.split(",").map(a => a.toInt).toSeq
    nearbyTickets = processInput(inputs)(2).tail.map(_.split(",").map(a => a.toInt).toSeq)
  }


  def processInput(inputs: Seq[String]): List[List[String]] = {
    val newSeq = inputs.map(a => {
      if (a.length < 1) "SEPARATOR"
      else a
    })
    splitBySeparator(newSeq.toList, "SEPARATOR")
  }

  def splitBySeparator[T](l: List[T], sep: T): List[List[T]] = {
    l.span( _ != sep ) match {
      case (hd, _ :: tl) => hd :: splitBySeparator(tl, sep)
      case (hd, _) => List(hd)
    }
  }

}
