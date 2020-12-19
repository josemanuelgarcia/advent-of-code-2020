package com.jmgf.adventofcode

import com.jmgf.adventofcode.puzzle.Day12
import org.scalatest.{FlatSpec, Matchers}

class Day12Test extends FlatSpec with Matchers {

  val inputs: Seq[(String, Int)] = Seq(
    "F10",
    "N3",
    "F7",
    "R90",
    "F11").map(e => (e.take(1), e.drop(1).toInt))

  it should "generate day 12 part 1" in {
    assert(Day12.part1(inputs).equals(25))
  }

  it should "generate day 12 part 2" in {
    assert(Day12.part2(inputs).equals(0))
  }

}
