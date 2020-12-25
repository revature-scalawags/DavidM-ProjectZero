package com.project0.models

import java.io.PrintWriter

object CsvWriter {
    def writeCSVFile(apiRepsponseArray: Array[String]): String = {
        val writer = new PrintWriter(apiRepsponseArray(1))
        writer.print(apiRepsponseArray(0))
        writer.close()
        val fileName = apiRepsponseArray(1)
        fileName
    }
}
