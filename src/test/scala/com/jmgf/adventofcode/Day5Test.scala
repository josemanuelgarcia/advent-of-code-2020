package com.jmgf.adventofcode

import com.jmgf.adventofcode.puzzle.Day5
import org.scalatest.{FlatSpec, Matchers}

class Day5Test extends FlatSpec with Matchers {

  val inputs: Seq[Array[Char]] = Seq("BFFFBBFRRR", "FFFBBBFRRR", "BBFFBBFRLL").map(_.toCharArray)

  it should "generate day 5 part 1" in {
    assert(Day5.part1(inputs).equals(820))
  }

  it should "generate day 5 part 2" in {
    assert(Day5.part2(inputs).equals(0))
  }


}
