package com.jmgf.adventofcode

import com.jmgf.adventofcode.puzzle.Day16
import org.scalatest.{FlatSpec, Matchers}

class Day16Test extends FlatSpec with Matchers {

  val inputs: Seq[String] = Seq(
    "class: 1-3 or 5-7",
    "row: 6-11 or 33-44",
    "seat: 13-40 or 45-50",
    "",
    "your ticket:",
    "7,1,14",
    "",
    "nearby tickets:",
    "7,3,47",
    "40,4,50",
    "55,2,20",
    "38,6,12")

  it should "generate day 16 part 1" in {
    assert(Day16.part1(inputs).equals(71))
  }

  it should "generate day 16 part 2" in {
    assert(Day16.part2(inputs).equals(0))
  }

}
