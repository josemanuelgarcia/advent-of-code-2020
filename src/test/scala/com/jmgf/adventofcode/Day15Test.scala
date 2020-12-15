package com.jmgf.adventofcode

import com.jmgf.adventofcode.puzzle.Day15
import org.scalatest.{FlatSpec, Matchers}

class Day15Test extends FlatSpec with Matchers {

  val inputs: Seq[Int] = Seq(0, 3, 6)

  it should "generate day 9 part 1" in {
    assert(Day15.part1(inputs).equals(436))
  }

  it should "generate day 9 part 2" in {
    assert(Day15.part2(inputs).equals(175594))
  }

}
