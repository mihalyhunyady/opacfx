package hu.hanprog.jsoup

import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import java.net.URL

class JsoupParser {

    fun start(id: String) {
        val url = "http://opac.elte.hu/F/FISVTNCKVJFQC7V289IXX48S87CNDV9JR4FBYRTXHSY4NU24UC-41452?func=find-c&ccl_term=sys+%3D+$id&x=58&y=6"
        /*  val doc = Jsoup.parse(URL(url).openStream(), "ISO-8859-2", url)
          println("Talalat: " + doc.select("#results_table > tbody > tr:nth-child(2) > td:nth-child(7) > a").text())
          println(doc.charset())

  */

        val doc = Jsoup.connect(url).get()

        print(doc.charset())

    }
}
