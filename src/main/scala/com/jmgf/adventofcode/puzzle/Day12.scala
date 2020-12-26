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
    getManhatanDistance(position)
  }

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

  override def part2(inputs: Seq[(String, Int)]): Int = {
    var waypoint = Map("E" -> 10, "N" -> 1)
    var position = Map("E" -> 0, "W" -> 0, "N" -> 0, "S" -> 0)

    inputs.foreach(action=> {
      if(position.keySet.contains(action._1)) {
        if(waypoint.keySet.contains(action._1))
          waypoint += (action._1 -> (waypoint(action._1) + action._2))
        else {
          if(action._1.equals("N")) {
            if(waypoint("S") < action._2){
              waypoint += ("N" -> (action._2 - waypoint("S")))
              waypoint -= "S"
            }
            else waypoint += ("S" -> (waypoint("S") - action._2))
          } else if (action._1.equals("S")) {
            if(waypoint("N") < action._2){
              waypoint += ("S" -> (action._2 - waypoint("N")))
              waypoint -= "N"

            }
            else waypoint += ("N" -> (waypoint("N") - action._2))
          } else if(action._1.equals("E")) {
            if (waypoint("W") < action._2) {
              waypoint += ("E" -> (action._2 - waypoint("W")))
              waypoint -= "W"
            }
            else waypoint += ("W" -> (waypoint("W") - action._2))
          } else {
            if (waypoint("E") < action._2) {
              waypoint += ("W" -> (action._2 - waypoint("E")))
              waypoint -= "E"
            }
            else waypoint += ("E" -> (waypoint("E") - action._2))
          }
        }
      } else if (action._1 == "F") {
        waypoint.foreach(pair => {
          position += (pair._1 -> (position(pair._1) + waypoint(pair._1) * action._2))
        })
      } else if (action._1 == "L") {
        waypoint = turnLeftWaypoint(waypoint, action._2)
      } else {
        waypoint = turnRightWaypoint(waypoint, action._2)
      }
    })
    getManhatanDistance(position)
  }

  private def turnLeftWaypoint(waypoint: Map[String, Int], degrees: Int): Map[String, Int] = {
    if(degrees == 0) waypoint
    else {
      if(waypoint.keySet.contains("N")) {
        if(waypoint.keySet.contains("W")) {
          turnLeftWaypoint(Map("S" -> waypoint("W"), "W" -> waypoint("N")), degrees - 90)
        } else {
          turnLeftWaypoint(Map("W" -> waypoint("N"), "N" -> waypoint("E")), degrees - 90)
        }
      } else {
        if(waypoint.keySet.contains("W")) {
          turnLeftWaypoint(Map("S" -> waypoint("W"), "E" -> waypoint("S")), degrees - 90)
        } else {
          turnLeftWaypoint(Map("N" -> waypoint("E"), "E" -> waypoint("S")), degrees - 90)
        }
      }
    }
  }

  private def turnRightWaypoint(waypoint: Map[String, Int], degrees: Int): Map[String, Int] = {
    if(degrees == 0) waypoint
    else {
      if(waypoint.keySet.contains("N")) {
        if(waypoint.keySet.contains("W")) {
          turnRightWaypoint(Map("N" -> waypoint("W"), "E" -> waypoint("N")), degrees - 90)
        } else {
          turnRightWaypoint(Map("E" -> waypoint("N"), "S" -> waypoint("E")), degrees - 90)
        }
      } else {
        if(waypoint.keySet.contains("W")) {
          turnRightWaypoint(Map("N" -> waypoint("W"), "W" -> waypoint("S")), degrees - 90)
        } else {
          turnRightWaypoint(Map("W" -> waypoint("S"), "S" -> waypoint("E")), degrees - 90)
        }
      }
    }
  }




  private def getManhatanDistance(position: Map[String, Int]) : Int = {
    (position("E") - position("W")).abs + (position("N") - position("S")).abs
  }

}
