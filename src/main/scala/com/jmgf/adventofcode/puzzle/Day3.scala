package com.jmgf.adventofcode.puzzle

import com.jmgf.adventofcode.Puzzle
import com.jmgf.adventofcode.util.File

import scala.util.Try

/**
  * Day 3 puzzle
  */
object Day3 extends Puzzle[Seq[String], Int, BigInt] {

  override def parse(resource: String): Seq[String] =
    File.readResource(resource).flatMap(row => Try(row.toString).toOption)

  override def part1(inputs: Seq[String]): Int = {
    val moveRightStep = 3
    val moveDownStep = 1
    val map = createMap(inputs, moveRightStep).map(_.toCharArray)

    recursiveMove(map, 0, 0, moveRightStep, moveDownStep).count(_.contains('X'))

  }

  override def part2(inputs: Seq[String]): BigInt = {
    val slopeList = Seq((1, 1), (3, 1), (5, 1), (7,1), (1,2))

    val a = slopeList.map(t  => {
      val map = createMap(inputs, t._1).map(_.toCharArray)

      recursiveMove(map, 0, 0, t._1, t._2).count(_.contains('X'))
    })

    a.map(_.toLong).product

  }


  def createMap(inputs: Seq[String], moveRight: Int): Seq[String] = {
    val numberToMultiply = (((inputs.length - 1) * moveRight) / inputs.head.length) + 1
    inputs.map(_ * numberToMultiply)
  }

  def recursiveMove(map: Seq[Array[Char]], xPos: Int, yPos: Int, stepRight: Int, stepDown: Int): Seq[Array[Char]] = {
    if(yPos == map.length - 1) map

    else {
      val newPosX = xPos + stepRight
      val newPosY = yPos + stepDown
      val mapRow = map(newPosY)
      val character = mapRow(newPosX)
      var newRow: Array[Char] = Array()

      if(character.equals('.')){
        newRow = mapRow.updated(newPosX, 'O')
      } else {
        newRow = mapRow.updated(newPosX, 'X')
      }
      val newMap = map.updated(newPosY, newRow)

      recursiveMove(newMap, newPosX, newPosY, stepRight, stepDown)
    }
  }

}
