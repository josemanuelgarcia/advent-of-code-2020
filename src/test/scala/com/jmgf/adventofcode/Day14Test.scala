package com.jmgf.adventofcode

import com.jmgf.adventofcode.puzzle.Day14
import org.scalatest.{FlatSpec, Matchers}

class Day14Test extends FlatSpec with Matchers {

  val inputs: Seq[String] = Seq(
    "mask = XXXXXXXXXXXXXXXXXXXXXXXXXXXXX1XXXX0X",
    "mem[8] = 11",
    "mem[7] = 101",
    "mem[8] = 0"
  )

  it should "generate day 14 part 1" in {
    assert(Day14.part1(inputs).equals(165))
  }

  it should "generate day 14 part 2" in {
    assert(Day14.part2(inputs).equals(0))
  }

}
