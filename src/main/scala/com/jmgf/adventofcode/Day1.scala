package com.jmgf.adventofcode

import com.jmgf.adventofcode.util.File

import scala.util.Try

/**
 * Day 1 puzzle
 *
 */
object Day1 extends Puzzle[Seq[Int], Int, Int] {
  override def parse(resource: String): Seq[Int] =
    File.readResource(resource).flatMap(row => Try(row.toInt).toOption)


  override def part1(inputs: Seq[Int]): Int = {
    val uniquePairs = for {
      (x, idxX) <- inputs.zipWithIndex
      (y, idxY) <- inputs.zipWithIndex
      if idxX < idxY && x+y == 2020
    } yield x * y

    uniquePairs.head
  }

  override def part2(inputs: Seq[Int]): Int = {
    val uniquePairs = for {
      (x, idxX) <- inputs.zipWithIndex
      (y, idxY) <- inputs.zipWithIndex
      (z, idxZ) <- inputs.zipWithIndex
      if idxX != idxY && idxX != idxZ && x+y+z == 2020
    } yield x * y * z

    uniquePairs.head
  }
}
