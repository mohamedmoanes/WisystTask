package com.developnetwork.wisysttask.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.developnetwork.wisysttask.R
import com.developnetwork.wisysttask.data.model.providers.DataItem
import kotlinx.android.synthetic.main.fragment_home.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : Fragment() {
    private val viewModel: HomeViewModel by viewModel()
    private val providersList = ArrayList<DataItem>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initProvidersAdapter()
        providersHandler()
        handlePagination()

        viewModel.getProviders()
    }

    private fun providersHandler() {
        viewModel.providersLiveData.observe(viewLifecycleOwner, Observer {
            it?.let { list ->
                providersList.addAll(list)
                providersRV.adapter?.notifyDataSetChanged()
            }
        })
    }

    private fun initProvidersAdapter() {
        providersRV.layoutManager = LinearLayoutManager(requireContext())
        providersRV.adapter = ProvidersAdapter(providersList)
    }

    private fun handlePagination() {
        providersRV.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val visibleItemCount = recyclerView.layoutManager!!.childCount
                val totalItemCount = recyclerView.layoutManager!!.itemCount
                val firstVisibleItemPosition =
                    (recyclerView.layoutManager as LinearLayoutManager?)!!.findFirstVisibleItemPosition()

                if (visibleItemCount + firstVisibleItemPosition >= totalItemCount
                    && firstVisibleItemPosition >= 0
                ) {
                    viewModel.loadNextPage()
                }
            }
        })
    }

}