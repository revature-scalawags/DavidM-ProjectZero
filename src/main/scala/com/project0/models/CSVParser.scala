package com.project0.models

object CSVParser {
  def parseCSVByFileName(fileName: String): Unit ={
    val lines = io.Source.fromFile(fileName).getLines()
    var numberOfCategorieObjectsInFile = 0
    for (line <- lines) {
      val splitLine = line.split(":")
      try {
        val finalValue = splitLine(1).replace(",","")
        println(s"My key: ${splitLine(0)} and My Value ${finalValue}")
        //setting the number of catagory instances to be created later on.
        if (splitLine0.contains("count"))
      } catch {
        case e: IndexOutOfBoundsException => println("Line only had one element to the array....skipping to next array.")
      }

    }
  }
}
