package news.ahead.cod.myapplication.article_detail

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_article_details.*
import news.ahead.cod.myapplication.extensions.loadImage
import news.ahead.cod.myapplication.model.Article


class ArticleDetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(news.ahead.cod.myapplication.R.layout.activity_article_details)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val article = intent.getParcelableExtra<Article?>("article") ?: return

        articleDetails_image.loadImage(article.urlToImage)
        articleDetails_title.text = article.title
        supportActionBar?.title =article.title
        articleDetails_description.text = article.description
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}
