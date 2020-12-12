package com.jmgf.adventofcode

import org.scalatest.{FlatSpec, Matchers}

class Day4Test extends FlatSpec with Matchers {

  val inputs: Seq[String] = Seq(
    "ecl:gry pid:860033327 eyr:2020 hcl:#fffffd",
    "byr:1937 iyr:2017 cid:147 hgt:183cm",
    "",
    "iyr:2013 ecl:amb cid:350 eyr:2023 pid:028048884",
    "hcl:#cfa07d byr:1929",
    "",
    "hcl:#ae17e1 iyr:2013",
    "eyr:2024",
    "ecl:brn pid:760753108 byr:1931",
    "hgt:179cm",
    "",
    "hcl:#cfa07d eyr:2025 pid:166559648",
    "iyr:2011 ecl:brn hgt:59in")

  it should "generate day 4 part 1" in {
    assert(Day4.part1(inputs).equals(2))
  }

  it should "generate day 4 part 2" in {
    assert(Day4.part2(inputs).equals(2))
  }


}
