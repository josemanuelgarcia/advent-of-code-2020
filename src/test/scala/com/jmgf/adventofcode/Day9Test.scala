package com.jmgf.adventofcode

import com.jmgf.adventofcode.puzzle.Day9
import org.scalatest.{FlatSpec, Matchers}

class Day9Test extends FlatSpec with Matchers {

  val inputs: Seq[Int] = Seq(35, 20, 15,25, 47, 40, 62, 55, 65, 95, 102, 117, 150, 182, 127, 219, 299, 277, 309, 576)

  it should "generate day 9 part 1" in {
    assert(Day9.part1(inputs).equals(127)) // Change the interval to 5 in part 1
  }

  it should "generate day 9 part 2" in {
    assert(Day9.part2(inputs).equals(62)) // change the goal number to 127
  }

}
