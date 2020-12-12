package com.jmgf.adventofcode

import com.jmgf.adventofcode.util.File

import scala.util.Try

/**
  * Day 5 puzzle
  */
object Day5 extends Puzzle[Seq[Array[Char]], Int, Int] {

  override def parse(resource: String): Seq[Array[Char]] =
    File.readResource(resource).flatMap(row => Try(row.toCharArray).toOption)

  override def part1(inputs: Seq[Array[Char]]): Int = {
    inputs.map(input => {
      val row = calculatePosition(input.dropRight(3), 0, 127)
      val column = calculatePosition(input.takeRight(3), 0, 7)
      row * 8 + column
    }).toList.max
  }

  override def part2(inputs: Seq[Array[Char]]): Int = 0

  private def calculatePosition(arrayCharacters: Array[Char], lowRange: Int, highRange: Int): Int = {
   if(arrayCharacters.length == 1) {
     if(arrayCharacters.head.equals('L') || arrayCharacters.head.equals('F')) lowRange else highRange
   } else {
     if (arrayCharacters.head.equals('L') || arrayCharacters.head.equals('F')) {
       val middlePosition = ((highRange - lowRange)/ 2) + lowRange
       calculatePosition(arrayCharacters.drop(1), lowRange, middlePosition)
     } else {
       val middlePosition = roundUp(highRange - lowRange, 2) + lowRange
       calculatePosition(arrayCharacters.drop(1), middlePosition, highRange)
     }
   }
  }

  private def roundUp(num: Int, divisor: Int): Int = (num + divisor - 1) / divisor
}
