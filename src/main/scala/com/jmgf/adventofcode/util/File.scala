package com.jmgf.adventofcode.util

import scala.io.Source

object File {

  /**
    * Read the contents of a resource file given its relative path into a sequence of lines
    */
  def readResource(resource: String): Seq[String] =
    Source.fromInputStream(getClass.getClassLoader.getResourceAsStream(resource)).getLines().toSeq
}