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

  val inputs2: Seq[String] = Seq(
    "mask = 000000000000000000000000000000X1001X",
    "mem[42] = 100",
    "mask = 00000000000000000000000000000000X0XX",
    "mem[26] = 1"
  )

  it should "generate day 14 part 1" in {
    assert(Day14.part1(inputs).equals(165))
  }

  it should "generate day 14 part 2" in {
    assert(Day14.part2(inputs2).equals(208))
  }

}
