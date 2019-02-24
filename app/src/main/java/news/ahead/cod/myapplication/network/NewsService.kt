package news.ahead.cod.myapplication.network

import news.ahead.cod.myapplication.main.PagingInfo
import news.ahead.cod.myapplication.model.ResponseModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsService {
    @GET("everything")
    fun latestNews(@Query("q") category: String = "bitcoin",
                   @Query("pageSize") pageSize: Int = PagingInfo.pageSize,
                   @Query("page") page: Int = 1,
                   @Query("apiKey") apiKey: String = "332900cd194a4d998acf71f4d85aa830"):
            Call<ResponseModel>
}
