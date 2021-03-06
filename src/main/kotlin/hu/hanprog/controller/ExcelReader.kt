package hu.hanprog.controller

import org.apache.poi.xssf.usermodel.XSSFWorkbook
import tornadofx.Controller
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.IOException
import java.util.*

class ExcelReader : Controller() {
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
        fIP.close()
        return workbook
    }

    fun readIDs(workbook: XSSFWorkbook?): MutableList<String> {
        val sysIdArray: MutableList<String> = ArrayList()
        if (workbook != null) {
            val sheet = workbook.getSheetAt(0)
            val rowIterator = sheet.iterator()
            while (rowIterator.hasNext()) {
                val row = rowIterator.next()
                val cell = row.getCell(0)
                try {
                    if (cell.numericCellValue != 0.0) {
                        sysIdArray.add("${cell.numericCellValue}")
                        println("${cell.numericCellValue}")
                    }
                } catch (e: IllegalStateException) {
                    //nothing to do here
                }
            }
            return sysIdArray
        }
        return mutableListOf("541", "25261", "1271")
    }

    fun readDictionary(workbook: XSSFWorkbook?): Map<String, String>? {
        val dictionary: MutableMap<String, String> = HashMap()
        if (workbook != null) {
            val sheet = workbook.getSheetAt(1)
            val rowIterator = sheet.iterator()
            while (rowIterator.hasNext()) {
                val row = rowIterator.next()
                val value = row.getCell(0)
                val key = row.getCell(1)
                try {
                    dictionary.put(convertToNonHungarianLetters(key.stringCellValue), value.stringCellValue)
                } catch (e: IllegalStateException) {
                    //nothing to do here
                }
            }
            return dictionary
        }
        return null
    }

    fun writeToExcel(file: File, workbook: XSSFWorkbook, resultMap: Map<String, String>) {
        val newRows = resultMap.keys
        val sheet = workbook.getSheetAt(2)
        var rowNum = sheet.lastRowNum

        for (key in newRows) {
            val row = sheet.createRow(rowNum++)
            var cellNum = 0
            val cell = row.createCell(cellNum++)

            cell.setCellValue(key)
            val cell2 = row.createCell(cellNum)
            cell2.setCellValue(resultMap[key])
        }
        val os = FileOutputStream(file)
        workbook.write(os)
        os.close()
        System.out.println("Writing on XLSX file Finished ...");

    }

    private fun convertToNonHungarianLetters(str: String): String {
        /*  val nStr = str.replace("á", "??")
                  .replace("é", "?è")
                  .replace("ű", "??")
                  .replace("ú", "??")
                  .replace("ő", "??")
                  .replace("ó", "??")
                  .replace("ü", "??")
                  .replace("ö", "??")
                  .replace("í", "??")
                  .replace("Á", "??")
                  .replace("É", "??")
                  .replace("Ű", "??")
                  .replace("Ú", "??")
                  .replace("Ő", "??")
                  .replace("Ó", "??")
                  .replace("Ü", "??")
                  .replace("Ö", "??")
                  .replace("Í", "??")*/
        return str
    }
}
