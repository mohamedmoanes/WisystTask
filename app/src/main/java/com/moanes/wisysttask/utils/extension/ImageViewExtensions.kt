package com.moanes.wisysttask.utils.extension

import androidx.appcompat.widget.AppCompatImageView
import com.squareup.picasso.Picasso

fun AppCompatImageView.setImageURL(url: String) {
    Picasso.get().load(url).fit().into(this)
}