package com.moanes.wisysttask.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.textview.MaterialTextView
import com.moanes.wisysttask.R

class FilterAdapter(
    private val list: List<String>,
    val click: (position: Int) -> Unit
) :
    RecyclerView.Adapter<FilterAdapter.ViewHolder>() {
    private var selectedItem = 0
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.filter_item, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.title.text = list[position]
        holder.itemView.setOnClickListener {
            selectedItem = position
            notifyDataSetChanged()
            click(position)
        }
        if (selectedItem == position) {
            holder.itemView.setBackgroundResource(R.drawable.border_red)

        } else {
            holder.itemView.setBackgroundResource(R.drawable.border)
        }
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val title = view.findViewById<MaterialTextView>(R.id.title)
    }
}