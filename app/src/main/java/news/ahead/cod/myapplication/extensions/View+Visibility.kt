package news.ahead.cod.myapplication.extensions

import android.view.View

fun View.setVisibility(shouldShow: Boolean) {
    visibility = if (shouldShow) View.VISIBLE else View.GONE
}
