package com.jmgf.adventofcode.puzzle

import com.jmgf.adventofcode.Puzzle
import com.jmgf.adventofcode.util.File

import scala.util.Try

object Day13 extends Puzzle[Seq[String], Int, Int] {

  override def parse(resource: String): Seq[String] =
    File.readResource(resource).flatMap(row => Try(row.toString).toOption)

  override def part1(inputs: Seq[String]): Int = {
    val timeDeparture = inputs.head.toInt
    val buses: Seq[Int] = inputs.last.split(",").filterNot(_.equals("x")).map(_.toInt)
    var earliestTimestampDepart = timeDeparture
    var found = false
    var earliestBus = 0

    while (!found) {
      val foundBus = buses.map(i => earliestTimestampDepart % i == 0)
      if(foundBus.contains(true)){
        found = true
        earliestBus = buses(foundBus.indexOf(true))
      } else {
        earliestTimestampDepart += 1
      }
    }

    (earliestTimestampDepart - timeDeparture) * earliestBus
  }

  override def part2(inputs: Seq[String]): Int = {
    val sequence: Seq[String] = inputs.last.split(",")
    val buses: Seq[(Int, Int)] = sequence.filterNot(_.equals("x")).map(bus => (bus.toInt, sequence.indexOf(bus)))
    val interval = buses.map(_._1).min
    var earliestTimestampDepart = interval
    var found = false

    while (!found) {
      val conditions = buses.count(bus => (earliestTimestampDepart + bus._2) % bus._1 == 0)
      if(conditions == buses.length)
        found = true
      else earliestTimestampDepart += interval
    }
    earliestTimestampDepart + sequence.length - 1
  }

}
