package news.ahead.cod.myapplication.main

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import news.ahead.cod.myapplication.adapter.NewsAdapter
import news.ahead.cod.myapplication.extensions.setVisibility
import news.ahead.cod.myapplication.helpers.NewsScrollListener
import news.ahead.cod.myapplication.model.Article


class MainActivity : AppCompatActivity(), MainContract.View {

    private val presenter: MainContract.Presenter = Presenter(this, NewsInteractor())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(news.ahead.cod.myapplication.R.layout.activity_main)

        presenter.requestData()
        mainActivity_refreshControl.setOnRefreshListener { presenter.onRefresh() }

        mainActivity_recyclerView.addOnScrollListener(NewsScrollListener(
                isRefreshing = { mainActivity_refreshControl.isRefreshing },
                nextPageLoadHandler = { presenter.loadNextPage() }))
    }

    override fun toggleLoadingProgress(isLoading: Boolean) {
        mainActivity_loadingProgressBarLayout.setVisibility(isLoading)
    }

    override fun toggleRefreshProgress(isRefreshing: Boolean) {
        mainActivity_refreshControl.isRefreshing = isRefreshing
    }

    override fun updateList(articles: List<Article>) {
        mainActivity_recyclerView.adapter = NewsAdapter(articles.toMutableList()) {}
    }

    override fun onError(error: Throwable) {
        Toast.makeText(this, error.localizedMessage, Toast.LENGTH_SHORT).show()
    }

    override fun appendItems(articles: List<Article>) {
        (mainActivity_recyclerView.adapter as? NewsAdapter)?.appendItems(articles)
    }

    override fun onDestroy() {
        presenter.onDestroy()
        super.onDestroy()
    }
}
