package com.jmgf.adventofcode

import com.jmgf.adventofcode.puzzle.Day7
import org.scalatest.{FlatSpec, Matchers}

class Day7Test extends FlatSpec with Matchers {

  val inputs: Seq[String] = Seq(
    "light red bags contain 1 bright white bag, 2 muted yellow bags.",
    "dark orange bags contain 3 bright white bags, 4 muted yellow bags.",
    "bright white bags contain 1 shiny gold bag.",
    "muted yellow bags contain 2 shiny gold bags, 9 faded blue bags.",
    "shiny gold bags contain 1 dark olive bag, 2 vibrant plum bags.",
    "dark olive bags contain 3 faded blue bags, 4 dotted black bags.",
    "vibrant plum bags contain 5 faded blue bags, 6 dotted black bags.",
    "faded blue bags contain no other bags.",
    "dotted black bags contain no other bags."
  )
  val inputsPart2: Seq[String] = Seq(
    "shiny gold bags contain 2 dark red bags.",
    "dark red bags contain 2 dark orange bags.",
    "dark orange bags contain 2 dark yellow bags.",
    "dark yellow bags contain 2 dark green bags.",
    "dark green bags contain 2 dark blue bags.",
    "dark blue bags contain 2 dark violet bags.",
    "dark violet bags contain no other bags."
  )

  it should "generate day 7 part 1" in {
    assert(Day7.part1(inputs).equals(4))
  }

  it should "generate day 7 part 2" in {
    assert(Day7.part2(inputsPart2).equals(126))
  }


}
