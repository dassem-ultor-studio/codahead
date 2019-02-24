package news.ahead.cod.myapplication.main

import news.ahead.cod.myapplication.model.Article

interface MainContract {
    interface Presenter {
        fun onDestroy()
        fun onRefresh()
        fun requestData()
        fun loadNextPage()
    }

    interface View {
        fun toggleLoadingProgress(isLoading: Boolean)
        fun toggleRefreshProgress(isRefreshing: Boolean)
        fun updateList(articles: List<Article>)
        fun onError(error: Throwable)
        fun appendItems(articles: List<Article>)
    }

    interface Interactor {
        fun latest(append: Boolean, page: Int, callback: ResponseCallback)

        interface ResponseCallback {
            fun onResponse(append: Boolean, articles: List<Article>)
            fun onError(error: Throwable)
        }
    }
}
