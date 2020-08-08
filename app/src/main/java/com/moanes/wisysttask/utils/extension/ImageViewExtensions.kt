package com.moanes.wisysttask.utils.extension

import androidx.appcompat.widget.AppCompatImageView
import com.squareup.picasso.Picasso
import de.hdodenhof.circleimageview.CircleImageView

fun AppCompatImageView.setImageURL(url: String) {
    Picasso.get().load(url).fit().into(this)
}
fun CircleImageView.setImageURL(url: String) {
    Picasso.get().load(url).fit().into(this)
}