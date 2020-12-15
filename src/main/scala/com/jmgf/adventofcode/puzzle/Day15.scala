package com.jmgf.adventofcode.puzzle

import com.jmgf.adventofcode.Puzzle
import com.jmgf.adventofcode.util.File

import scala.util.Try

object Day15 extends Puzzle[Seq[Int], Int, Int] {

  override def parse(resource: String): Seq[Int] =
    File.readResource(resource).flatMap(row => Try(row.toInt).toOption)


  override def part1(inputs: Seq[Int]): Int = {
    var gameSeq = inputs

    while(gameSeq.length < 2020) {
      val lastNumber = gameSeq.last
      if (gameSeq.count(_ == lastNumber) == 1) gameSeq = gameSeq :+ 0
      else {
        val a = gameSeq.dropRight(1).indices.filter(pos => gameSeq(pos) == lastNumber).max
        gameSeq = gameSeq :+ (gameSeq.length - (a + 1))
      }
    }
    gameSeq.last
  }

  override def part2(inputs: Seq[Int]): Int = {
    var gameSeq = inputs

    while(gameSeq.length < 30000000) {
      val lastNumber = gameSeq.last
      if (gameSeq.count(_ == lastNumber) == 1) gameSeq = gameSeq :+ 0
      else {
        val a = gameSeq.dropRight(1).indices.find(pos => gameSeq(pos) == lastNumber).max
        gameSeq = gameSeq :+ (gameSeq.length - (a + 1))
      }
    }
    gameSeq.last
  }
}
