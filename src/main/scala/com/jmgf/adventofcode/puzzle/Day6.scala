package com.jmgf.adventofcode.puzzle

import com.jmgf.adventofcode.Puzzle
import com.jmgf.adventofcode.util.File

import scala.util.Try

object Day6 extends Puzzle[Seq[String], Int, Int]{

  override def parse(resource: String): Seq[String] =
    File.readResource(resource).flatMap(row => Try(row.toString).toOption)

  override def part1(inputs: Seq[String]): Int =
    processInput(inputs).map(_.mkString("")).map(_.trim.toCharArray.distinct.length).sum


  override def part2(inputs: Seq[String]): Int =
    processInput(inputs).map(_.map(_.trim.toCharArray)).map(_.reduceLeft(_.intersect(_)).length).sum


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
