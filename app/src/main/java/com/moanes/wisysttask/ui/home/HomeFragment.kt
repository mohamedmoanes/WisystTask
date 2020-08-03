package com.moanes.wisysttask.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.androidbuts.multispinnerfilter.KeyPairBoolData
import com.moanes.wisysttask.R
import com.moanes.wisysttask.data.model.providers.DataItem
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
        getSpecialization()
        handleFilterSpinner()
        viewModel.getProviders()

        mapBTN.setOnClickListener {
            findNavController().navigate(R.id.mapsFragment)
        }

    }

    private fun providersHandler() {
        viewModel.providersLiveData.observe(viewLifecycleOwner, Observer {
            it?.let { list ->
                providersList.clear()
                providersRV.adapter?.notifyDataSetChanged()

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

    private fun getSpecialization() {
        viewModel.getSpecification().observe(viewLifecycleOwner, Observer {
            it?.let { list ->
                initSpecialization(list)
            }
        })
    }

    private fun initSpecialization(list: List<KeyPairBoolData>) {
        specialization.setItems(list, -1) {
            for (item: KeyPairBoolData in it.iterator()) {
                if (item.isSelected) {
                    viewModel.specificationID = item.id.toInt()
                    viewModel.filter(true)
                }
            }
        }
    }

    private fun handleFilterSpinner() {
        filter.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(p0: AdapterView<*>?) {
                //TODO("Not yet implemented")
            }

            override fun onItemSelected(p0: AdapterView<*>?, v: View?, position: Int, id: Long) {
                when (position) {
                    0 -> {
                        viewModel.homeServices = 0
                        viewModel.insurance = 0
                        viewModel.offers = 0
                    }
                    1 -> {
                        viewModel.insurance = 1
                    }
                    2 -> {
                        viewModel.offers = 1
                    }
                    3 -> {
                        viewModel.homeServices = 1
                    }
                }
                viewModel.filter(true)
            }
        }
    }
}