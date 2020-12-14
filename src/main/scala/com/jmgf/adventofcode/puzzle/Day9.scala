package com.jmgf.adventofcode.puzzle

import com.jmgf.adventofcode.Puzzle
import com.jmgf.adventofcode.util.File

import scala.util.Try

object Day9 extends Puzzle[Seq[Int], Int, Int] {

  override def parse(resource: String): Seq[Int] =
    File.readResource(resource).flatMap(row => Try(row.toInt).toOption)

  val interval = 25

  override def part1(inputs: Seq[Int]): Int = {
    var isFirstValueFound = false
    var firstValue = 0
    var i = interval
    while (i < inputs.length && !isFirstValueFound) {
      if(getAllPairOfNumbersThatSumX(inputs.slice(i - interval, i), inputs(i)).isEmpty) {
        firstValue = inputs(i)
        isFirstValueFound = true
      }
      i += 1
    }
    firstValue
  }

  override def part2(inputs: Seq[Int]): Int = {
    var isFirstValueFound = false
    var firstValue = 0
    var i = 2
    var step = 2
    while (step < inputs.length && !isFirstValueFound) {
      while (i < inputs.length && !isFirstValueFound) {
        if (inputs.slice(i - step, i).sum == 167829540) {
          firstValue = inputs.slice(i - step, i).min + inputs.slice(i - step, i).max
          isFirstValueFound = true
        }
        i += 1
      }
      i = 0
      step += 1
    }
    firstValue
  }


  private def getAllPairOfNumbersThatSumX(inputs: Seq[Int], goal: Int): Seq[(Int, Int)] = {
    for {
      (x, idxX) <- inputs.zipWithIndex
      (y, idxY) <- inputs.zipWithIndex
      if idxX < idxY && x+y == goal
    } yield (x, y)
  }

}
