package com.jmgf.adventofcode.puzzle

import com.jmgf.adventofcode.Puzzle
import com.jmgf.adventofcode.util.File

import scala.util.Try

/**
  * Day 7 puzzle
  */
object Day7 extends Puzzle[Seq[String], Int, Int]{
  override def parse(resource: String): Seq[String] =
    File.readResource(resource).flatMap(row => Try(row.toString).toOption)

  override def part1(inputs: Seq[String]): Int = {
    val graph = parseInput(inputs)
    searchOccurrences(graph, "shiny gold")
  }

  override def part2(inputs: Seq[String]): Int = countNumberOfBags(parseInput(inputs),  "shiny gold")

  private def parseInput(inputs: Seq[String]): Map[String, Set[(String, Int)]] = {
    inputs.map(_.split("bags contain ")).map(row => {
      val colourRegex = """([a-z]+ [a-z]+)""".r
      val appearencesRegex = """(\d+ \w+ \w+)""".r
      val colourBag = colourRegex.findFirstMatchIn(row(0))
      val bagsContained = appearencesRegex.findAllMatchIn(row(1)).toSeq
      val map = bagsContained.map(bag => {
        val number = """(\d+)""".r.findFirstMatchIn(bag.toString())
        val colourBag = colourRegex.findFirstMatchIn(bag.toString())
        (colourBag.get.toString(), number.get.toString().toInt)
      }).toSet
      colourBag.get.toString -> map
    }).toMap
  }

  private def searchOccurrences(graph: Map[String, Set[(String, Int)]],initialState: String): Int = {
    var queue: Seq[String] = Seq(initialState)
    var visited: Set[String] = Set()
    var count = 0

    while (queue.nonEmpty) {
      val bagToSearch = queue.head
      queue = queue.tail
      graph.keySet.foreach(gr => {
        if(graph(gr).map(_._1).contains(bagToSearch)) {
          if (!visited.contains(gr)) {
            queue = queue :+ gr
            count += 1
            visited += gr
          }
        }
      })
    }
    count
  }


  private def countNumberOfBags(graph: Map[String, Set[(String, Int)]], initialState: String): Int = {
    var queue: Seq[(String, Int)] = graph(initialState).toSeq
    var finalNumberOfBags = 0
    while (queue.nonEmpty) {
      val bagToSearch = queue.head
      queue = queue.tail
      graph.keySet.foreach(gr => {
        if(gr.equals(bagToSearch._1)) {
          graph(gr).foreach(bags => {
            queue = queue :+ (bags._1, bagToSearch._2 * bags._2)
          })
          finalNumberOfBags += bagToSearch._2
        }
      })
    }
    finalNumberOfBags
  }


}
