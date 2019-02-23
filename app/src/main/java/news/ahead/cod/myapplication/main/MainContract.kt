package news.ahead.cod.myapplication.main

import news.ahead.cod.myapplication.model.Article

interface MainContract {
    interface MainPresenter {
        fun onDestroy()
        fun onRefresh()
        fun requestData()
    }

    interface MainView {
        fun toggleProgress(shouldShowProgress: Boolean)
        fun updateList(articles: List<Article>)
        fun onError(error: Throwable)
    }

    interface NewsInteractor {
        fun latest(callback: ResponseCallback)

        interface ResponseCallback {
            fun onResponse(articles: List<Article>)
            fun onError(error: Throwable)
        }
    }
}
