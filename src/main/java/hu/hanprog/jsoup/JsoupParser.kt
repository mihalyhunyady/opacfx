package hu.hanprog.jsoup

import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import java.net.URL
import java.util.*

class JsoupParser(val dictionary: Map<String, String>?) {
    val resultMap = HashMap<String, String>()
    fun start(id: String) {
        val url = "http://opac.elte.hu/F/FISVTNCKVJFQC7V289IXX48S87CNDV9JR4FBYRTXHSY4NU24UC-41452?func=find-c&ccl_term=sys+%3D+$id&x=58&y=6"
        val doc = Jsoup.parse(URL(url).openStream(), "UTF-8", url)


        /*      val doc = Jsoup.connect(url).get()

*/
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
                searchForResult(id, res.split("(")[0].trimStart().trimEnd())
            }
        } else {
            resultMap.put(id, "Nem talalt ($result)")
            println("$id -> Nem talalt ($result)")
        }
        //searchForResult(id, result)
    }

    private fun searchForResult(id: String, result: String) {
        if (!result.isEmpty()) {
            println("ELEJE: $id -> $result")
            if (dictionary != null) {
                for (key in dictionary.keys) {
                    if (key.contains(result)) {
                        resultMap.put(id, dictionary[key].toString())
                        println("$id -> ${dictionary[key].toString()}")
                        return
                    }
                }
                resultMap.put(id, "Nem talalt ($result)")
                println("$id -> Nem talalt ($result)")
            }
        }
    }
}
