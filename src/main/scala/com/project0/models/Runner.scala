package com.project0.models

import com.project0.models.CSVParser.parseCSVByFileName
import com.project0.models.CsvWriter.writeCSVFile
import com.project0.models.RuleCatagory.getRuleCatagories

object Runner extends App{

  val fileName = writeCSVFile(getRuleCatagories())
  parseCSVByFileName(fileName: String)
}
