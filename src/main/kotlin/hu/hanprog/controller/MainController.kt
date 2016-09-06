package hu.hanprog.controller

import hu.hanprog.controller.ExcelReader
import hu.hanprog.controller.JsoupParser
import javafx.fxml.FXML
import javafx.fxml.Initializable
import javafx.scene.control.Button
import javafx.scene.web.HTMLEditor
import javafx.stage.DirectoryChooser
import javafx.stage.FileChooser
import javafx.stage.Stage
import tornadofx.Controller
import tornadofx.FX
import java.io.File

import java.net.URL
import java.nio.file.Files
import java.nio.file.Paths

import java.util.ResourceBundle

class MainController : Controller() {

    val excelReader: ExcelReader by inject()
    val parser : JsoupParser by inject()

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
        for (id in sys) {
            try {
                result += parser.parse(dictionary, id.split(".")[0])
            } catch (e: Exception) {
                e.printStackTrace()
            }
            Thread.sleep(300)//FIXME Thread.sleep in FX Main Thread? o.o
        }
        for ((key, value) in result) {
            println("$key , $value")
        }
        if (file != null) {
            excelReader.writeToExcel(file, workbook, result)
        }
    }

    private fun openFile(): File? {
        if (excelPath != null) {
            return File(excelPath)
        } else {
            return null
        }
    }
}
