package gov.nasa.apod.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import gov.nasa.apod.R

class ImageBindingAdapter {
    companion object {

        @JvmStatic
        @BindingAdapter("imageUrl")
        fun loadImage(view: ImageView, url: String?) {
            if (url != null) {
                Glide.with(view.context)
                    .load(url)
                    .placeholder(R.drawable.ic_baseline_cloud_download_24)
                    .error(R.drawable.ic_baseline_error_24)
                    .skipMemoryCache(true)
                    .diskCacheStrategy(DiskCacheStrategy.DATA)
                    .into(view);
            }
        }
    }
}