package com.jmgf.adventofcode

import com.jmgf.adventofcode.puzzle.Day10
import org.scalatest.{FlatSpec, Matchers}

class Day10Test extends FlatSpec with Matchers {

  val inputs: Seq[Int] = Seq(28, 33, 18, 42, 31, 14, 46, 20, 48, 47, 24, 23, 49,
    45, 19, 38, 39, 11, 1, 32, 25, 35, 8, 17, 7, 9, 4, 2, 34, 10, 3)

  it should "generate day 10 part 1" in {
    assert(Day10.part1(inputs).equals(220))
  }

  it should "generate day 10 part 2" in {
    assert(Day10.part2(inputs).equals(0))
  }

}
