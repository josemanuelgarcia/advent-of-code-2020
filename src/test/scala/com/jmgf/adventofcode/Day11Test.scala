package com.jmgf.adventofcode

import com.jmgf.adventofcode.puzzle.Day11
import org.scalatest.{FlatSpec, Matchers}

class Day11Test extends FlatSpec with Matchers {

  val inputs: Seq[Array[Char]] = Seq(
    "L.LL.LL.LL",
    "LLLLLLL.LL",
    "L.L.L..L..",
    "LLLL.LL.LL",
    "L.LL.LL.LL",
    "L.LLLLL.LL",
    "..L.L.....",
    "LLLLLLLLLL",
    "L.LLLLLL.L",
    "L.LLLLL.LL").map(_.toCharArray)

  it should "generate day 11 part 1" in {
    assert(Day11.part1(inputs).equals(37))
  }

  it should "generate day 11 part 2" in {
    assert(Day11.part2(inputs).equals(26))
  }

}
