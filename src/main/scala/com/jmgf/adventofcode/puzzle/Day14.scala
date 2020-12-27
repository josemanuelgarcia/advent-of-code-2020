package com.jmgf.adventofcode.puzzle

import com.jmgf.adventofcode.Puzzle
import com.jmgf.adventofcode.util.File

import scala.util.Try

object Day14 extends Puzzle[Seq[String], BigInt, BigInt] {

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
        val integerWithMask = encodeApplyMaskAndDecode(actualMask, processed)
        memoryValues += (position -> integerWithMask)
      }
    })
    memoryValues.values.map(_.toLong).sum
  }

  private def encodeApplyMaskAndDecode(actualMask: String, processed: Array[String]) = {
    val value = processed.last.toInt.toBinaryString
    val previous = List.fill(36 - value.length)("0").mkString("")
    val completeValue = previous + value
    val binaryWithMask = actualMask.zipWithIndex.map(value =>
      if (value._1.equals('X')) completeValue(value._2)
      else value._1).mkString
    val integerWithMask = BigInt(binaryWithMask, 2)
    integerWithMask
  }

  override def part2(inputs: Seq[String]): BigInt = {
    var actualMask = ""
    var memoryValues: Map[BigInt, BigInt] = Map()

    inputs.foreach(line => {
      val processed = line.split("\\[|\\]| ")
      if(line.contains("mask")) {
        actualMask = processed.last
      } else {
        val position = processed(1).toInt.toBinaryString
        val integerValue = processed.last.toInt
        val previous = List.fill(36 - position.length)("0").mkString("")
        val completeValue = previous + position
        val binaryWithMask = actualMask.zipWithIndex.map(value =>
          if (value._1.equals('X')) "X"
          else if (value._1.equals('1')) "1"
          else completeValue(value._2)).mkString
        generateAllPosibleBinaries(binaryWithMask).map(BigInt(_, 2))
          .foreach(value => {memoryValues += (value -> integerValue)})
      }
    })
    memoryValues.values.sum
  }

  private def generateAllPosibleBinaries(input: String): Seq[String] = {
    if(!input.contains('X')) {
      Seq(input)
    } else {
      val positionFirstX = input.indexOf('X')
      input.toCharArray.updated(positionFirstX, '0').mkString
      generateAllPosibleBinaries(input.toCharArray.updated(positionFirstX, '0').mkString) ++
        generateAllPosibleBinaries(input.toCharArray.updated(positionFirstX, '1').mkString)
    }
  }

}
