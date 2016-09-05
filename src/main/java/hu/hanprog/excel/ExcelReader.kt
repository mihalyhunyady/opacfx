package hu.hanprog.excel

import org.apache.poi.xssf.usermodel.XSSFWorkbook

import java.io.File
import java.io.FileInputStream
import java.io.FileNotFoundException
import java.io.IOException

class ExcelReader {
    @Throws(IOException::class)
    fun openWorkBook(file: File?): XSSFWorkbook {
        val fIP = FileInputStream(file)
        val workbook = XSSFWorkbook(fIP)
        if (file != null) {
            if (file.isFile && file.exists()) {
                println("openworkbook.xlsx file open successfully.")
            } else {
                println("Error to open openworkbook.xlsx file.")
            }
        }
        return workbook
    }

    fun readIDs(workbook: XSSFWorkbook?): Array<String> {
        return arrayOf("541", "25261", "1271")
    }
}
