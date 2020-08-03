package com.moanes.wisysttask.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatRatingBar
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.moanes.wisysttask.R
import com.moanes.wisysttask.data.model.providers.DataItem
import com.moanes.wisysttask.utils.extension.setImageURL

class ProvidersAdapter(val list: List<DataItem>) :
    RecyclerView.Adapter<ProvidersAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.provider_item, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val provider = list[position]
        holder.title.text = provider.provider.name
        holder.branch.text = provider.name
        holder.image.setImageURL(provider.logo)
        holder.rate.rating = provider.rate.toFloat()
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val title: AppCompatTextView = view.findViewById(R.id.title)
        val branch: AppCompatTextView = view.findViewById(R.id.branch)
        val image: AppCompatImageView = view.findViewById(R.id.image)
        val rate: AppCompatRatingBar = view.findViewById(R.id.ratingBar)
    }
}