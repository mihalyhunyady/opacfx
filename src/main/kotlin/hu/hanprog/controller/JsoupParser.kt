package hu.hanprog.controller

import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import tornadofx.Controller
import java.net.URL
import java.util.*

class JsoupParser : Controller() {
    fun parse(dictionary: Map<String, String>?, id: String): HashMap<String, String> {
        val resultMap = HashMap<String, String>()
        val url = "http://opac.elte.hu/F/FISVTNCKVJFQC7V289IXX48S87CNDV9JR4FBYRTXHSY4NU24UC-41452?func=find-c&ccl_term=sys+%3D+$id&x=58&y=6"
        val doc = Jsoup.parse(URL(url).openStream(), "UTF-8", url)
        var result = doc.select("#results_table > tbody > tr:nth-child(2) > td:nth-child(7) > a").text()
        if (result.isNullOrEmpty()) {
            result = doc.select("#results_table > tbody > tr:nth-child(2) > td:nth-child(7)").text()
        }
        if (result.isNullOrEmpty()) {
            result = ""
        }
        if (!result.isEmpty()) {
            val results = result.trimEnd().split(")")

            for (res in results) {
                searchForResult(dictionary, resultMap, id, res.split("(")[0].trimStart().trimEnd())
            }
        } else {
            resultMap.put(id, "Üres")
           // println("$id -> Üres")
        }
        return resultMap
    }

    private fun searchForResult(dictionary: Map<String, String>?, resultMap: HashMap<String, String>, id: String, result: String) {
        if (!result.isEmpty()) {
            if (dictionary != null) {
                for (key in dictionary.keys) {
                    if (key.contains(result) || result.contains(key)) {
                        resultMap.put(id, dictionary[key].toString())
                      //  println("$id -> ${dictionary[key].toString()}")
                        return
                    }
                }
                resultMap.put(id, "Nem azonosithato ($result)")
              //  println("$id -> Nem azonosithato ($result)")
            }
        }
    }
}
