package com.jmgf.adventofcode

import com.jmgf.adventofcode.util.File

import scala.util.Try

object Day4 extends Puzzle[Seq[String], Int, Int] {

  private val obligatoryFields = Seq("byr", "iyr", "eyr", "hgt", "hcl", "ecl", "pid")

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

    val lista = inputProcessed.map(_.split(" ").map(t => {
      val a = t.split(":")
      a(0) -> a(1)
    }).toMap)

    lista
      .filter(t => obligatoryFields.forall(t.contains(_)))
      .count(t => isByrCorrect(t("byr"))
        && isIyrCorrect(t("iyr"))
        && isEyrCorrect(t("eyr"))
        && isHgtCorrect(t("hgt"))
        && isHclCorrect(t("hcl"))
        && isEclCorrect(t("ecl"))
        && isPidCorrect(t("pid")))
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

  val isByrCorrect: String => Boolean = (valor: String) => {1920 <= valor.toInt  && valor.toInt <= 2002}
  val isIyrCorrect: String => Boolean = (valor: String) => {2010 <= valor.toInt  && valor.toInt <= 2020}
  val isEyrCorrect: String => Boolean = (valor: String) => {2020 <= valor.toInt  && valor.toInt <= 2030}

  val isHgtCorrect: String => Boolean = (valor: String) => {
    val regex = """(\d+(cm|in)+)""".r
    regex.findFirstMatchIn(valor).isDefined && (
      (valor.takeRight(2) == "cm" && 150 <= valor.dropRight(2).toInt && valor.dropRight(2).toInt <= 193) ||
        (valor.takeRight(2) == "in" && 59 <= valor.dropRight(2).toInt && valor.dropRight(2).toInt <= 76))
  }
  val isHclCorrect: String => Boolean = (valor: String) => {
    val regex = """(#([a-z0-9]{6}))""".r
    valor.length == 7 && regex.findFirstMatchIn(valor).isDefined
  }

  val isEclCorrect: String => Boolean = (valor: String) => {Seq("amb", "blu", "brn", "gry", "grn", "hzl", "oth").contains(valor)}
  val isPidCorrect: String => Boolean = (valor: String) => {valor.length == 9 && valor.forall(Character.isDigit)}

}
