package com.jmgf.adventofcode.puzzle

import com.jmgf.adventofcode.Puzzle
import com.jmgf.adventofcode.util.File

import scala.util.Try

/**
  * Day 10 puzzle
  */
object Day10 extends Puzzle[Seq[Int], Int, Int] {

  override def parse(resource: String): Seq[Int] =
    File.readResource(resource).flatMap(row => Try(row.toInt).toOption)

  override def part1(inputs: Seq[Int]): Int = {
    val sorted = 0 +: inputs.sorted
    val joltDifferences = sorted.zip(sorted.drop(1)).map({case(a, b) => b - a}) :+ 3
   joltDifferences.count(_.equals(1)) * joltDifferences.count(_.equals(3))
  }

  override def part2(inputs: Seq[Int]): Int = 0
}
