package news.ahead.cod.myapplication.main

import news.ahead.cod.myapplication.model.Article

class Presenter(var view: MainContract.View?, var interactor: MainContract.Interactor?) :
        MainContract.Presenter, MainContract.Interactor.ResponseCallback {

    override fun onRefresh() {
        view?.toggleProgress(true)
        interactor?.latest(this)
    }

    override fun requestData() {
        interactor?.latest(this)
    }

    override fun onDestroy() {
        view = null
    }

    override fun onResponse(articles: List<Article>) {
        val view = view ?: return

        view.updateList(articles)
        view.toggleProgress(false)
    }

    override fun onError(error: Throwable) {
        val view = view ?: return

        view.onError(error)
        view.toggleProgress(false)
    }
}
