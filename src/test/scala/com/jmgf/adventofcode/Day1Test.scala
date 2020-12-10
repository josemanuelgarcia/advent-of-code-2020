package com.jmgf.adventofcode

import org.scalatest.{FlatSpec, Matchers}


class Day1Test extends FlatSpec with Matchers {

  val inputs: Seq[Int] = Seq(1721, 979, 366, 299, 675, 1456)

  it should "generate day 1 part 1" in {
    assert(Day1.part1(inputs).equals(514579))
  }
}
