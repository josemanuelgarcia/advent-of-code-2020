package com.jmgf.adventofcode

import com.jmgf.adventofcode.puzzle.Day6
import org.scalatest.{FlatSpec, Matchers}

class Day6Test extends FlatSpec with Matchers {

  val inputs: Seq[String] = Seq(
    "abc",
    "",
    "a",
    "b",
    "c",
    "",
    "ab",
    "ac",
    "",
    "a",
    "a",
    "a",
    "a",
    "",
    "b"
  )

  it should "generate day 6 part 1" in {
    assert(Day6.part1(inputs).equals(11))
  }

  it should "generate day 6 part 2" in {
    assert(Day6.part2(inputs).equals(6))
  }


}
