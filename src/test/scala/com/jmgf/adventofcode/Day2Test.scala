package com.jmgf.adventofcode

import org.scalatest.{FlatSpec, Matchers}

class Day2Test extends FlatSpec with Matchers {

  val inputs: Seq[String] = Seq("1-3 a: abcde", "1-3 b: cdefg", "2-9 c: ccccccccc")

  it should "generate day 2 part 1" in {
    println(Day2.part1(inputs))
    assert(Day2.part1(inputs).equals(2))
  }

  it should "generate day 2 part 2" in {
    assert(Day2.part2(inputs).equals(1))
  }

}