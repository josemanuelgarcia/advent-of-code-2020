package com.jmgf.adventofcode

import com.jmgf.adventofcode.puzzle._

object Runner extends App {

  def resource(day: Int): String = s"day${"%02d".format(day)}.txt"

  val puzzles: Map[Int, Puzzle[_, _, _]] = Map(
    1 -> Day1,
    2 -> Day2,
    3 -> Day3,
    4 -> Day4,
    5 -> Day5,
    6 -> Day6
  )


  def runAll(): Unit = {
    puzzles.foreach(pzz => {
      println(s"Solving puzzle for Day ${pzz._1}")
      val res = resource(pzz._1)
      pzz._2.solve(res)
    })
  }

  runAll()
}