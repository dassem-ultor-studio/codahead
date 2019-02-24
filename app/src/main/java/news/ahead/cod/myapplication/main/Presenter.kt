package news.ahead.cod.myapplication.main

import news.ahead.cod.myapplication.model.Article

class Presenter(private var view: MainContract.View?, private var interactor: MainContract.Interactor?) :
        MainContract.Presenter, MainContract.Interactor.ResponseCallback {

    private var currentPage = 1

    override fun onRefresh() {
        currentPage = 1
        view?.toggleRefreshProgress(true)
        requestData()
    }

    override fun requestData() {
        view?.toggleRefreshProgress(true)
        interactor?.latest(false, currentPage, this)
    }

    override fun loadNextPage() {
        currentPage += 1
        view?.toggleLoadingProgress(true)
        interactor?.latest(true, currentPage, this)
    }

    override fun onResponse(append: Boolean, articles: List<Article>) {
        val view = view ?: return

        if (append) {
            view.appendItems(articles)
            view.toggleLoadingProgress(false)
        } else {
            view.updateList(articles)
            view.toggleRefreshProgress(false)
        }
    }

    override fun onError(error: Throwable) {
        val view = view ?: return

        view.onError(error)
        view.toggleLoadingProgress(false)
        view.toggleRefreshProgress(true)
    }

    override fun onDestroy() {
        view = null
    }
}
