package hu.hanprog

import hu.hanprog.view.MainView
import javafx.application.Application
import javafx.event.EventHandler
import javafx.stage.Stage
import tornadofx.App
import tornadofx.FX

fun main(args: Array<String>) {
    Application.launch(OpacFxApp::class.java)
}

class OpacFxApp : App(MainView::class) {

    init {

    }

    override fun start(stage: Stage) {
        super.start(stage)
        val cssResource = javaClass
                .getResource("theme.css")
        val css = cssResource.toExternalForm()
        FX.primaryStage.scene.stylesheets.clear()
        FX.primaryStage.scene.stylesheets.add(css)

        FX.primaryStage.onCloseRequest = EventHandler {
            println("Exit")
            System.exit(0)
        }
    }
}
