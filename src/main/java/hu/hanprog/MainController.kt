package hu.hanprog

import hu.hanprog.excel.ExcelReader
import hu.hanprog.jsoup.JsoupParser
import javafx.application.Platform
import javafx.fxml.FXML
import javafx.fxml.Initializable
import javafx.scene.web.HTMLEditor
import org.jsoup.Jsoup
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File

import java.net.URL
import java.util.ResourceBundle

class MainController : Initializable {

    @FXML
    lateinit var htmlEditor: HTMLEditor

    override fun initialize(location: URL?, resources: ResourceBundle?) {

    }

    @FXML
    fun getHTML() {
        val excelReader = ExcelReader()
        val file = openFile()
        excelReader.openWorkBook(file)
        val sys = excelReader.readIDs(null)
        for (id in sys) {
            val parser = JsoupParser()
            parser.start(id)
        }

    }

    private fun openFile(): File? {
        return null
    }

}
