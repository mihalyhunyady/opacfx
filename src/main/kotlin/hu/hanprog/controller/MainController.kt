package hu.hanprog.controller


import javafx.stage.FileChooser
import tornadofx.Controller
import tornadofx.FX
import tornadofx.success
import tornadofx.task
import java.io.File

class MainController : Controller() {

    val excelReader: ExcelReader by inject()
    val parser: JsoupParser by inject()

    var excelPath: String? = null

    fun openExcelDialog() {
        val directoryChooser = FileChooser()
        val selectedDirectory = directoryChooser.showOpenDialog(FX.primaryStage)
        if (selectedDirectory != null) {
            excelPath = selectedDirectory.absolutePath
        }
    }

    fun getHTML() {
        val file = openFile()
        val workbook = excelReader.openWorkBook(file)
        val sys = excelReader.readIDs(workbook)
        val dictionary = excelReader.readDictionary(workbook)
        val result = mutableMapOf<String, String>()
        task {
            for (id in sys) {
                try {
                    searchForLibrary(dictionary, id, result)
                } catch (e: Exception) {
                    searchForLibrary(dictionary, id, result)
                    e.printStackTrace()
                }
                Thread.sleep(300)
            }
        } success  {
            for ((key, value) in result) {
                println("$key , $value")
            }
            if (file != null) {
                excelReader.writeToExcel(file, workbook, result)
            }
        }
    }

    private fun searchForLibrary(dictionary: Map<String, String>?, id: String, result: MutableMap<String, String>) {
        result += parser.parse(dictionary, id.split(".")[0])
    }

    private fun openFile(): File? {
        if (excelPath != null) {
            return File(excelPath)
        } else {
            return null
        }
    }
}
