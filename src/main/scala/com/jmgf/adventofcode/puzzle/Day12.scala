package com.jmgf.adventofcode.puzzle

import com.jmgf.adventofcode.Puzzle
import com.jmgf.adventofcode.util.File

import scala.util.Try

object Day12 extends Puzzle[Seq[(String, Int)], Int, Int] {

  override def parse(resource: String): Seq[(String, Int)] =
    File.readResource(resource).flatMap(row => Try(row.toString).toOption)
      .map(e => (e.take(1), e.drop(1).toInt))

  override def part1(inputs: Seq[(String, Int)]): Int = {
    var direction = "E"
    var position = Map("E" -> 0, "W" -> 0, "N" -> 0, "S" -> 0)

    inputs.foreach(action=> {
      if(position.keySet.contains(action._1)) {
        position += (action._1 -> (position(action._1) + action._2))
      } else if (action._1 == "F") {
        position += (direction -> (position(direction) + action._2))
      } else if (action._1 == "R") {
        direction = turnRight(direction, action._2)
      } else {
        direction = turnLeft(direction, action._2)
      }
    })

    (position("E") - position("W")).abs + (position("N") - position("S")).abs
  }

  override def part2(inputs: Seq[(String, Int)]): Int = 0


  private def turnLeft(actualDirection: String, degrees: Int): String = {
    val directions = Seq("N", "E", "S", "W")
    val index = directions.indexOf(actualDirection)
    val positionsToMove = degrees / 90

    val nextPosition = index - positionsToMove

    if(nextPosition < 0) directions(nextPosition + 4)
    else directions(nextPosition)

  }

  private def turnRight(actualDirection: String, degrees: Int): String = {
    val directions = Seq("N", "E", "S", "W")
    val index = directions.indexOf(actualDirection)
    val positionsToMove = degrees / 90

    val nextPosition = index + positionsToMove

    if(nextPosition > 3) directions(nextPosition - 4)
    else directions(nextPosition)

  }

}
