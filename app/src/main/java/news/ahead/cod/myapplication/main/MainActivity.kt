package news.ahead.cod.myapplication.main

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import news.ahead.cod.myapplication.adapter.NewsAdapter
import news.ahead.cod.myapplication.extensions.setVisibility
import news.ahead.cod.myapplication.model.Article


class MainActivity : AppCompatActivity(), MainContract.View {

    private val presenter: MainContract.Presenter = Presenter(this, NewsInteractor())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(news.ahead.cod.myapplication.R.layout.activity_main)

        presenter.requestData()
        mainActivity_refreshControl.setOnRefreshListener { presenter.onRefresh() }

        mainActivity_recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val visibleItemCount = recyclerView.layoutManager.childCount
                val totalItemCount = recyclerView.layoutManager.itemCount
                val firstVisibleItemPosition = (recyclerView.layoutManager as LinearLayoutManager).findFirstVisibleItemPosition()

                if (!mainActivity_refreshControl.isRefreshing) {
                    if (visibleItemCount + firstVisibleItemPosition >= totalItemCount - PagingInfo.loadPageMargin
                            && firstVisibleItemPosition >= 0
                            && totalItemCount >= PagingInfo.pageSize) {
                        presenter.loadNextPage()
                    }
                }
            }
        })
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
