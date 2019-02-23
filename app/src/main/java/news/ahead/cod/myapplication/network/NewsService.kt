package news.ahead.cod.myapplication.network

import news.ahead.cod.myapplication.model.ResponseModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsService {
    @GET("top-headlines")
    fun latestNews(@Query("country") country: String = "us",
                   @Query("category") category: String = "business",
                   @Query("apiKey") apiKey: String = "332900cd194a4d998acf71f4d85aa830"):
            Call<ResponseModel>
}
