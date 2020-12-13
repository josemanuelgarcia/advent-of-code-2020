package com.jmgf.adventofcode.puzzle

import com.jmgf.adventofcode.Puzzle
import com.jmgf.adventofcode.util.File

import scala.util.Try

/**
 * Day 2 puzzle
 *
 */
object Day2 extends Puzzle[Seq[String], Int, Int] {
  override def parse(resource: String): Seq[String] =
    File.readResource(resource).flatMap(row => Try(row.toString).toOption)


  override def part1(inputs: Seq[String]): Int = {
    getInputPoroccessed(inputs)
      .count(in => comparePolicies(countsMapWord(in._3), in._1, in._2))
  }

  override def part2(inputs: Seq[String]): Int =
    getInputPoroccessed(inputs)
      .count(in => isLetterAtTheRightPosition(in._3, in._1, in._2))


  def getInputPoroccessed(inputs: Seq[String]): Seq[(Char, (Int, Int), String)] = {
    inputs.map(line => {
      val listSplit = line.split(" |-|:")
      (listSplit(2).head, (listSplit.head.toInt, listSplit(1).toInt), listSplit.last)
    })
  }

  def countsMapWord(password: String): Map[Char, Int] = password.groupBy(identity).mapValues(_.length)

  def comparePolicies(passwordCount: Map[Char, Int], letter: Char, occurences : (Int, Int)): Boolean = {
    if(!passwordCount.contains(letter))
      false
    else
      occurences._1 <= passwordCount(letter) && passwordCount(letter) <= occurences._2
  }

  def isLetterAtTheRightPosition(password: String, letter: Char, positions : (Int, Int)): Boolean = {
    Seq(password.charAt(positions._1 - 1).equals(letter), password.charAt(positions._2 - 1).equals(letter))
      .count(_.equals(true)).equals(1)

  }

}
