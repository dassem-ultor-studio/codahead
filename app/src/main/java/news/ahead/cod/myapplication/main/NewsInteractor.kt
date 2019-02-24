package news.ahead.cod.myapplication.main

import news.ahead.cod.myapplication.model.ResponseModel
import news.ahead.cod.myapplication.network.APIClient
import news.ahead.cod.myapplication.network.NewsService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NewsInteractor : MainContract.Interactor {

    override fun latest(append: Boolean, page: Int, callback: MainContract.Interactor.ResponseCallback) {
        val retrofit = APIClient.client() ?: return

        val service = retrofit.create(NewsService::class.java)

        service.latestNews(page = page).enqueue(object : Callback<ResponseModel> {
            override fun onResponse(call: Call<ResponseModel>, response: Response<ResponseModel>) {
                val articles = response.body()?.articles ?: return

                callback.onResponse(append, articles)
            }

            override fun onFailure(call: Call<ResponseModel>, error: Throwable) {
                callback.onError(error)
            }
        })
    }
}
