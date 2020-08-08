package com.moanes.wisysttask.ui.specialization

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.textview.MaterialTextView
import com.moanes.wisysttask.R
import com.moanes.wisysttask.data.model.specifications.Specification
import android.widget.Filter
import android.widget.Filterable

class SpecializationAdapter(
    private val list: ArrayList<Specification>,
    private val click: (id: Int) -> Unit
) : RecyclerView.Adapter<SpecializationAdapter.ViewHolder>(), Filterable {
    private var requestListFiltered: ArrayList<Specification> = list

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.specialization_iem, parent, false)
        )
    }

    override fun getItemCount(): Int {
//        return list.size
        return requestListFiltered.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.name.text = requestListFiltered[position].name
        holder.itemView.setOnClickListener {
            click(requestListFiltered[position].id)
        }
    }

    override fun getFilter(): Filter? {
        return object : Filter() {
            override fun performFiltering(charSequence: CharSequence): FilterResults {
                val charString = charSequence.toString()
                requestListFiltered = if (charString.isEmpty()) {
                    list
                } else {
                    val filteredList: ArrayList<Specification> = ArrayList()
                    for (row in list) { // name match condition. this might differ depending on your requirement
                        // here we are looking for name or phone number match
                        if (charString in row.name) {
                            filteredList.add(row)
                        }
                    }
                    filteredList
                }
                val filterResults = FilterResults()
                filterResults.values = requestListFiltered
                return filterResults
            }

            override fun publishResults(
                charSequence: CharSequence,
                filterResults: FilterResults
            ) {
                requestListFiltered = filterResults.values as ArrayList<Specification>
                notifyDataSetChanged()
            }
        }
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val name = view.findViewById<MaterialTextView>(R.id.name)
    }
}