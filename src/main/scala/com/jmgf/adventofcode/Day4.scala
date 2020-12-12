package com.jmgf.adventofcode

import com.jmgf.adventofcode.util.File

import scala.util.Try

object Day4 extends Puzzle[Seq[String], Int, Int] {

  val obligatoryFields = Seq("byr", "iyr", "eyr", "hgt", "hcl", "ecl", "pid")

  override def parse(resource: String): Seq[String] =
    File.readResource(resource).flatMap(row => Try(row.toString).toOption)

  override def part1(inputs: Seq[String]): Int = {

    val fieldNameRegex = """[a-z][a-z][a-z](?=:)""".r

    processInput(inputs)
      .map(fieldNameRegex.findAllIn(_).toSeq)
      .count(t => obligatoryFields.forall(t.contains(_)))
  }

  override def part2(inputs: Seq[String]): Int = {
    val inputProcessed = processInput(inputs)
    0
  }

  def processInput(inputs: Seq[String]): List[String] = {
    val newSeq = inputs.map(a => {
      if (a.length < 1) "SEPARATOR"
      else a
    })
    splitBySeparator(newSeq.toList, "SEPARATOR").map(_.mkString(" "))
  }

  def splitBySeparator[T](l: List[T], sep: T): List[List[T]] = {
    l.span( _ != sep ) match {
      case (hd, _ :: tl) => hd :: splitBySeparator(tl, sep)
      case (hd, _) => List(hd)
    }
  }


}
