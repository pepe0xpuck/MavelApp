package br.com.douglasqueiroz.mavelapp.helper

import android.widget.ImageView
import com.squareup.picasso.Picasso

class ImageHelper {

    companion object {

        fun loadImage(url: String, imageView: ImageView) {

            Picasso.get()
                .load(url)
                .into(imageView)
        }
    }
}