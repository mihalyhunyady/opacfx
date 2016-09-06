package hu.hanprog.view

import hu.hanprog.controller.MainController
import javafx.scene.control.Button
import javafx.scene.layout.AnchorPane
import tornadofx.View

class MainView : View("OpacFX") {
    override val root: AnchorPane by fxml()
    val excelButton: Button by fxid()
    val workButton: Button by fxid()

    val controller: MainController by inject()

    init {
        excelButton.setOnAction {controller.openExcelDialog()}
        workButton.setOnAction { controller.getHTML() }
    }
}