package hu.hanprog

import hu.hanprog.view.MainView
import javafx.application.Application
import tornadofx.App
import tornadofx.Stylesheet

fun main(args: Array<String>) {
    Application.launch(OpacFxApp::class.java)
}

class OpacFxApp: App(MainView::class, Styles::class)

//Misi todo, look up typesafe css ;)
class Styles : Stylesheet() {
    companion object {
    }

    init {
    }
}