package com.project0.models

import scala.collection.mutable.ArrayBuffer



object CSV {
  
  case class CSV(headers: Array[String], columns: Array[Array[String]])
  
  /**
    * gets lines from file source
    * creates a new headersArrayBuffer
    * checks if has headers is true (if so run getColumnHeadersMethod and sets to headersToArray variable and adds to headersArrayBuffer)
    * flattens headersArrayBuffer to get a single Array of Strings
    * gets columns of csv per line by running getColumns method
    * creates CSV object from variables headers and columns.
    * @param fileName
    * @param hasHeaders
    * @return
    */
  def writeCSV(fileName: String, hasHeaders: Boolean): CSV={
    val lines = scala.io.Source.fromFile(fileName).getLines()
    val headersArrayBuffer = new ArrayBuffer[Array[String]]()
    if (hasHeaders){ 
      val headersToArray = getColumnHeaders(lines)
      headersArrayBuffer += headersToArray
    }
    val headers = headersArrayBuffer.flatten.toArray
    val columns = getColumns(lines)
    val csv = new CSV(headers,columns)
    csv
  }

  /**
    * Checks if file has headerNames. If so gets headerNames.
    * Takes each line in lines and seperates each by commas.
    * Gets columns by length of line Array
    * gets each column and set it to the name of the corrosponding header columnName.
    * 
    * @param lines
    */
  def getColumns(lines: Iterator[String]): Array[Array[String]]={
    val columns = new ArrayBuffer[Array[String]]()
    for (line <- lines){
      val lineSplit = line.split(",")
      columns += lineSplit
    }
    columns.toArray
  }

  /**
    * Splits first line by commas to get headerNames
    * Reduces counter to 0 to stop interation
    * @param lines
    */
  def getColumnHeaders(lines: Iterator[String]): Array[String]={
    var headers =  new ArrayBuffer[Array[String]]()
    var counter = 1
    if (counter > 0){
      for (line <- lines){
        var headersArray = line.split(",")
        headers += headersArray
        counter = counter - 1 
      }
    }
    headers.toArray.flatten
  }

}
