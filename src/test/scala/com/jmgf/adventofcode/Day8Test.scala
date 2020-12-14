package com.jmgf.adventofcode

import com.jmgf.adventofcode.puzzle.Day8
import org.scalatest.{FlatSpec, Matchers}

class Day8Test extends FlatSpec with Matchers {

  val inputs: Seq[(String, Int)] = Seq(
    "nop +0",
    "acc +1",
    "jmp +4",
    "acc +3",
    "jmp -3",
    "acc -99",
    "acc +1",
    "jmp -4",
    "acc +6"
  ).map(_.split(" ")).map(e => (e(0), e(1).toInt))

  it should "generate day 8 part 1" in {
    assert(Day8.part1(inputs).equals(5))
  }

  it should "generate day 8 part 2" in {
    assert(Day8.part2(inputs).equals(8))
  }


}
