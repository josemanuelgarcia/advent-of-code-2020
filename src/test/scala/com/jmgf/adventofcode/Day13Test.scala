package com.jmgf.adventofcode

import com.jmgf.adventofcode.puzzle.Day13
import org.scalatest.{FlatSpec, Matchers}

class Day13Test extends FlatSpec with Matchers {

  val inputs: Seq[String] = Seq(
    "939",
    "7,13,x,x,59,x,31,19")

  it should "generate day 13 part 1" in {
    assert(Day13.part1(inputs).equals(295))
  }

  it should "generate day 13 part 2" in {
    assert(Day13.part2(inputs).equals(1068788))
  }

}
