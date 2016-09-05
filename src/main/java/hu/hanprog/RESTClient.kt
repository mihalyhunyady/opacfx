package hu.hanprog

import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface RESTClient {
    @GET("/F/FISVTNCKVJFQC7V289IXX48S87CNDV9JR4FBYRTXHSY4NU24UC-26190")
    fun loadWebPage(
            @Query("ccl_term") ccl_term: String,
            @Query("func") func: String = "find-c",
            @Query("adjacent") adjacent: String = "N",
            @Query("x") x: String = "45",
            @Query("y") y: String = "11",
            @Query("filter_code_1") filter_code_1: String = "WLN",
            @Query("filter_request_1") filter_request_1: String = "",
            @Query("filter_code_2") filter_code_2: String = "WYR",
            @Query("filter_code_3") filter_code_3: String = "WYR",
            @Query("filter_request_2") filter_request_2: String = "",
            @Query("filter_request_3") filter_request_3: String = "",
            @Query("filter_code_4") filter_code_4: String = "WFM",
            @Query("filter_request_4") filter_request_4: String = "",
            @Query("filter_code_5") filter_code_5: String = "WSB",
            @Query("filter_request_5") filter_request_5: String = ""
    ): Call<String>
}
