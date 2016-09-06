package hu.hanprog.controller


import com.google.common.collect.Collections2
import hu.hanprog.view.MainView
import javafx.application.Platform
import javafx.collections.FXCollections
import javafx.collections.ObservableList
import javafx.stage.FileChooser
import tornadofx.Controller
import tornadofx.FX
import tornadofx.success
import tornadofx.task
import java.io.File
import java.util.*

class MainController : Controller() {

    val excelReader: ExcelReader by inject()
    val parser: JsoupParser by inject()
    val items: ObservableList<String> = FXCollections.observableArrayList()
    val view: MainView by inject()
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
                //   Thread.sleep(300)
            }
        } success {
            addToLogs(result)
            if (file != null) {
                excelReader.writeToExcel(file, workbook, result)
            }
        }
    }

    private fun addToLogs(result: MutableMap<String, String>) {
        Platform.runLater {
            items.clear()
            for ((key, value) in result) {
                println("$key , $value")

                items.add("$key , $value")
                view.logs.refresh()
            }
        }
    }

    private fun searchForLibrary(dictionary: Map<String, String>?, id: String, result: MutableMap<String, String>) {
        result += parser.parse(dictionary, id.split(".")[0])
        addToLogs(result)
    }

    private fun openFile(): File? {
        if (excelPath != null) {
            return File(excelPath)
        } else {
            return null
        }
    }
}
