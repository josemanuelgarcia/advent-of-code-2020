package com.jmgf.adventofcode.puzzle

import com.jmgf.adventofcode.Puzzle
import com.jmgf.adventofcode.util.File

import scala.util.Try

/**
  * Day 10 puzzle
  */
object Day10 extends Puzzle[Seq[Int], Int, BigInt] {

  override def parse(resource: String): Seq[Int] =
    File.readResource(resource).flatMap(row => Try(row.toInt).toOption)

  override def part1(inputs: Seq[Int]): Int = {
    val sorted = 0 +: inputs.sorted :+ (inputs.max + 3)
    val joltDifferences = sorted.zip(sorted.drop(1)).map({case(a, b) => b - a})
   joltDifferences.count(_.equals(1)) * joltDifferences.count(_.equals(3))
  }

  override def part2(inputs: Seq[Int]): BigInt = {
    val joltsSorted = 0 +: inputs.sorted :+ (inputs.max + 3)
    val joltDifferences = joltsSorted.zip(joltsSorted.drop(1)).map({case(a, b) => b - a})

    val possible_permutations = Seq(1, 1, 2, 4, 7)
    var acc: Long = 1
    var last_range = 0
    joltDifferences.foreach(i => {
      if (i == 1) {
        last_range += 1
      } else if (i == 3) {
        acc *= possible_permutations(last_range)
        last_range = 0
      }
    })
    acc
  }

}
