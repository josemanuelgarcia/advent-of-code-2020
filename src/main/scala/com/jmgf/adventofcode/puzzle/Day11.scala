package com.jmgf.adventofcode.puzzle

import com.jmgf.adventofcode.Puzzle
import com.jmgf.adventofcode.util.File

import scala.util.Try
import scala.util.control.Breaks

object Day11 extends Puzzle[Seq[Array[Char]], Int, Int]{

  override def parse(resource: String): Seq[Array[Char]] =
    File.readResource(resource).flatMap(row => Try(row.toCharArray).toOption)

  override def part1(inputs: Seq[Array[Char]]): Int = {
    var actualBoard = inputs
    var occupiedSeatsLastIteration = getOccupiedCount(actualBoard)

    do {
      var nextIterationBoard = actualBoard

      for(x <- actualBoard.head.indices) {
        for(y <- actualBoard.indices) {
          val adjacentOccupiedSeats = getAdjacentOccupied(x, y, actualBoard)
          val row = nextIterationBoard(y)
          var newRow: Array[Char] = row
          if(adjacentOccupiedSeats == 0 && actualBoard(y)(x).equals('L')) {
            newRow = row.updated(x, '#')
          } else if (adjacentOccupiedSeats > 3 && actualBoard(y)(x).equals('#')) {
            newRow = row.updated(x, 'L')
          }
          nextIterationBoard = nextIterationBoard.updated(y, newRow)
        }
      }
      occupiedSeatsLastIteration = getOccupiedCount(actualBoard)
      actualBoard = nextIterationBoard
    } while(occupiedSeatsLastIteration != getOccupiedCount(actualBoard))

    occupiedSeatsLastIteration
  }

  override def part2(inputs: Seq[Array[Char]]): Int = {
    var actualBoard = inputs
    var occupiedSeatsLastIteration = getOccupiedCount(actualBoard)

    do {
      var nextIterationBoard = actualBoard

      for(x <- actualBoard.head.indices) {
        for(y <- actualBoard.indices) {
          val adjacentOccupiedSeats = getSeatsCanSeeCount(x, y, actualBoard)
          val row = nextIterationBoard(y)
          var newRow: Array[Char] = row
          if(adjacentOccupiedSeats == 0 && actualBoard(y)(x).equals('L')) {
            newRow = row.updated(x, '#')
          } else if (adjacentOccupiedSeats > 4 && actualBoard(y)(x).equals('#')) {
            newRow = row.updated(x, 'L')
          }
          nextIterationBoard = nextIterationBoard.updated(y, newRow)
        }
      }
      occupiedSeatsLastIteration = getOccupiedCount(actualBoard)
      actualBoard = nextIterationBoard
    } while(occupiedSeatsLastIteration != getOccupiedCount(actualBoard))

    occupiedSeatsLastIteration
  }

  private def getAdjacentOccupied(posX: Int, posY: Int, board: Seq[Array[Char]]): Int = {
    val positionsToCheck = Seq(
      (posX - 1, posY - 1),
      (posX, posY - 1),
      (posX + 1, posY - 1),
      (posX - 1, posY),
      (posX + 1, posY),
      (posX - 1, posY + 1),
      (posX, posY + 1),
      (posX + 1, posY + 1)
    )

    positionsToCheck
      .filter(pos => 0 <= pos._1 && pos._1 < board.head.length && 0 <= pos._2 && pos._2 < board.length)
      .count(pos => board(pos._2)(pos._1).equals('#'))
  }

  private def getOccupiedCount(board: Seq[Array[Char]]) = board.flatten.count(_.equals('#'))

  private def printBoard(board: Seq[Array[Char]]): Unit = {
    board.foreach( row => {
      row.foreach(print(_))
      println("")
    })

    println("------------")
  }

  private def getSeatsCanSeeCount(posX: Int, posY: Int, board: Seq[Array[Char]]): Int = {
    val positionsToCheck = Seq(
      getNextSeat(posX, posY, board,-1,-1),
      getNextSeat(posX, posY, board,0,-1),
      getNextSeat(posX, posY, board,1,-1),
      getNextSeat(posX, posY, board,-1,0),
      getNextSeat(posX, posY, board,1,0),
      getNextSeat(posX, posY, board,-1,1),
      getNextSeat(posX, posY, board,0,1),
      getNextSeat(posX, posY, board,1,1)
    )

    positionsToCheck
      .filter(pos => 0 <= pos._1 && pos._1 < board.head.length && 0 <= pos._2 && pos._2 < board.length)
      .count(pos => board(pos._2)(pos._1).equals('#'))
  }

  private def getNextSeat(posX: Int, posY: Int, board: Seq[Array[Char]], incrX: Int, incrY: Int): (Int, Int) = {
    var newX = posX
    var newY = posY
    val whileLoop = new Breaks
    whileLoop.breakable {
      do {
        newY += incrY
        newX += incrX
        if (0 > newX || newX >= board.head.length ||
          0 > newY || newY >= board.length) {
          whileLoop.break()
        }
      } while (board(newY)(newX) == '.')
    }
    (newX, newY)
  }



}
