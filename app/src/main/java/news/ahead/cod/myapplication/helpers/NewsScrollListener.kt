package news.ahead.cod.myapplication.helpers

import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import news.ahead.cod.myapplication.main.PagingInfo

class NewsScrollListener(val isRefreshing: () -> Boolean,
                         val nextPageLoadHandler: () -> Unit) : RecyclerView.OnScrollListener() {
    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)
        if (isRefreshing()) return

        val visibleItemCount = recyclerView.layoutManager.childCount
        val totalItemCount = recyclerView.layoutManager.itemCount
        val firstVisibleItemPosition = (recyclerView.layoutManager as LinearLayoutManager).findFirstVisibleItemPosition()

        if (visibleItemCount + firstVisibleItemPosition >= totalItemCount - PagingInfo.loadPageMargin
                && firstVisibleItemPosition >= 0
                && totalItemCount >= PagingInfo.pageSize) {
            nextPageLoadHandler()
        }
    }
}
