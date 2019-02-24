package news.ahead.cod.myapplication.main

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import news.ahead.cod.myapplication.R
import news.ahead.cod.myapplication.adapter.NewsAdapter
import news.ahead.cod.myapplication.extensions.setVisibility
import news.ahead.cod.myapplication.model.Article

class MainActivity : AppCompatActivity(), MainContract.View {

    private val presenter: MainContract.Presenter = Presenter(this, NewsInteractor())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        presenter.requestData()
        mainActivity_refreshControl.setOnRefreshListener { presenter.onRefresh() }
    }

    override fun toggleProgress(shouldShowProgress: Boolean) {
        mainActivity_progressBar.setVisibility(shouldShowProgress)
        mainActivity_refreshControl.isRefreshing = shouldShowProgress
    }

    override fun updateList(articles: List<Article>) {
        mainActivity_recyclerView.adapter = NewsAdapter(articles) {}
    }

    override fun onError(error: Throwable) {
        Toast.makeText(this, error.localizedMessage, Toast.LENGTH_SHORT).show()
    }

    override fun onDestroy() {
        presenter.onDestroy()
        super.onDestroy()
    }
}
