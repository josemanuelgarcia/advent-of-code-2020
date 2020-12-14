package com.jmgf.adventofcode.puzzle

import com.jmgf.adventofcode.Puzzle
import com.jmgf.adventofcode.util.File

import scala.util.Try

object Day8 extends Puzzle[Seq[(String, Int)], Int, Int] {

  override def parse(resource: String): Seq[(String, Int)] =
    File.readResource(resource).flatMap(row => Try(row.toString).toOption)
      .map(_.split(" ")).map(e => (e(0), e(1).toInt))

  override def part1(inputs: Seq[(String, Int)]): Int = runInstructionsRec(inputs, Seq(), 0, 0)._2


  override def part2(inputs: Seq[(String, Int)]): Int = {
    var found = 0
    for (i <- inputs.indices) {
      if (inputs(i)._1.equals("jmp")) {
        val newPosValue: Seq[(String, Int)] = inputs.updated(i, ("nop", 0))
        val (instructions, acum) = runInstructionsRec(newPosValue, Seq(), 0, 0)
        if(instructions.contains(inputs.length -1))
          found = acum
      } else if (inputs(i)._1.equals("nop")) {
        val actualPos = inputs(i)
        val newPosValue: Seq[(String, Int)] = inputs.updated(i, ("jmp", actualPos._2))
        val (instructions, accum) = runInstructionsRec(newPosValue, Seq(), 0, 0)
        if(instructions.contains(inputs.length - 1))
          found = accum
      }
    }
    found
  }


  private def runInstructionsRec(instructions: Seq[(String, Int)], positionsVisited: Seq[Int], position: Int, acum: Int): (Seq[Int], Int) = {
    if (positionsVisited.contains(position) || positionsVisited.contains(instructions.length - 1)) (positionsVisited, acum)
    else {
      val instruction = instructions(position)
      instruction._1 match {
        case "nop" => runInstructionsRec(instructions, positionsVisited :+ position, position + 1, acum)
        case "acc" => runInstructionsRec(instructions, positionsVisited :+ position, position + 1, acum + instruction._2)
        case "jmp" => runInstructionsRec(instructions, positionsVisited :+ position, position + instruction._2, acum)
      }
    }
  }
}
