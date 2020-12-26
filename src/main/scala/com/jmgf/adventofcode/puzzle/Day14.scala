package com.jmgf.adventofcode.puzzle

import com.jmgf.adventofcode.Puzzle
import com.jmgf.adventofcode.util.File

import scala.util.Try

object Day14 extends Puzzle[Seq[String], BigInt, Int] {

  override def parse(resource: String): Seq[String] =
    File.readResource(resource).flatMap(row => Try(row.toString).toOption)

  override def part1(inputs: Seq[String]): BigInt = {
    var actualMask = ""
    var memoryValues: Map[Int, BigInt] = Map()

    inputs.foreach(line => {
      val processed = line.split("\\[|\\]| ")
      if(line.contains("mask")) {
        actualMask = processed.last
      } else {
        val position = processed(1).toInt
        val value = processed.last.toInt.toBinaryString
        val previous = List.fill(36 - value.length)("0").mkString("")
        val completeValue = previous + value
        val binaryWithMask = actualMask.zipWithIndex.map(value =>
        if (value._1.equals('X')) completeValue(value._2)
        else value._1).mkString("")
        val integerWithMask = BigInt(binaryWithMask, 2)

        memoryValues += (position -> integerWithMask)
      }
    })
    memoryValues.values.map(_.toLong).sum
  }

  override def part2(inputs: Seq[String]): Int = 0

}
