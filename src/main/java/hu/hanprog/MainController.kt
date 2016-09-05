package hu.hanprog

import hu.hanprog.excel.ExcelReader
import hu.hanprog.jsoup.JsoupParser
import javafx.fxml.FXML
import javafx.fxml.Initializable
import javafx.scene.control.Button
import javafx.scene.web.HTMLEditor
import javafx.stage.DirectoryChooser
import javafx.stage.FileChooser
import javafx.stage.Stage
import java.io.File

import java.net.URL
import java.nio.file.Files
import java.nio.file.Paths

import java.util.ResourceBundle

class MainController : Initializable {

    @FXML
    lateinit var htmlEditor: HTMLEditor
    private var stage: Stage? = null

    @FXML
    lateinit var openExcel: Button

    var excelPath: String? = null
    override fun initialize(location: URL?, resources: ResourceBundle?) {

    }

    @FXML
    private fun openExcelDialog() {
        getStage()
        val directoryChooser = FileChooser()
        val selectedDirectory = directoryChooser.showOpenDialog(stage)
        if (selectedDirectory != null) {
            excelPath = selectedDirectory.absolutePath
        }
    }

    @FXML
    fun getHTML() {
        val excelReader = ExcelReader()
        val file = openFile()
        val workbook = excelReader.openWorkBook(file)
        val sys = excelReader.readIDs(workbook)
        val dictionary = excelReader.readDictionary(workbook)
        val parser = JsoupParser(dictionary)
        for (id in sys) {
            try {
                parser.start(id.split(".")[0])
            } catch (e: Exception) {
                e.printStackTrace()
            }
            Thread.sleep(300)
        }
        val result = parser.resultMap
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


    private fun getStage() {
        stage = openExcel.scene.window as Stage
    }

}
