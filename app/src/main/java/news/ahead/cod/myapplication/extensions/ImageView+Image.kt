package news.ahead.cod.myapplication.extensions

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import jp.wasabeef.glide.transformations.RoundedCornersTransformation
import news.ahead.cod.myapplication.R

fun ImageView.loadImageRoundedCorners(url: String?) {
    Glide.with(this)
            .load(url)
            .transform(CenterCrop(), RoundedCornersTransformation(12, 0))
            .placeholder(R.drawable.ic_launcher_background)
            .into(this)
}
fun ImageView.loadImage(url: String?) {
    Glide.with(this)
            .load(url)
            .placeholder(R.drawable.ic_launcher_background)
            .into(this)
}

