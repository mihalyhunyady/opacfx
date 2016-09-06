package hu.hanprog.view

import hu.hanprog.OpacFxApp
import hu.hanprog.controller.MainController
import javafx.scene.control.Button
import javafx.scene.control.ListView
import javafx.scene.layout.AnchorPane
import tornadofx.View
import tornadofx.importStylesheet

class MainView : View("OpacFX") {
    override val root: AnchorPane by fxml()
    val excelButton: Button by fxid()
    val workButton: Button by fxid()
    val logs: ListView<String> by fxid()
    val controller: MainController by inject()

    init {
        excelButton.setOnAction { controller.openExcelDialog() }
        workButton.setOnAction { controller.getHTML() }
        logs.items = controller.items
        /*   val css = javaClass.getResource("theme.css").toExternalForm()

           importStylesheet("./view/theme.css")
       */
    }
}