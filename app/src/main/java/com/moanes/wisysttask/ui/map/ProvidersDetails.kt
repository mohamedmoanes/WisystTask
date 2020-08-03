package com.moanes.wisysttask.ui.map

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.moanes.wisysttask.R
import com.moanes.wisysttask.data.model.providers.DataItem
import com.moanes.wisysttask.utils.extension.setImageURL
import kotlinx.android.synthetic.main.provider_item.*

class ProvidersDetails(val data: DataItem) : BottomSheetDialogFragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.provider_item, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setData(data)
    }

    private fun setData(provider: DataItem) {
        title.text = provider.provider.name
        branch.text = provider.name
        image.setImageURL(provider.logo)
        ratingBar.rating = provider.rate.toFloat()
    }
}