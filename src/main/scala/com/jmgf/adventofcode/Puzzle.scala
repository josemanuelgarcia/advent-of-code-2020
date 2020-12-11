package com.jmgf.adventofcode

trait Puzzle[I, P1, P2] {

  def parse(resource: String): I

  def part1(inputs: I): P1
  def part2(inputs: I): P2

  def solve(resource: String) = {
    val result1 = part1(parse(resource))
    val result2 = part2(parse(resource))

    println(s"Part 1 result => $result1")
    println(s"Part 2 result => $result2")

  }

}
