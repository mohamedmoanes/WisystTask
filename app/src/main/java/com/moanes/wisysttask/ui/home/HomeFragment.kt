package com.moanes.wisysttask.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.AdapterView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.androidbuts.multispinnerfilter.KeyPairBoolData
import com.google.android.material.snackbar.Snackbar
import com.moanes.wisysttask.R
import com.moanes.wisysttask.data.model.providers.DataItem
import com.moanes.wisysttask.utils.ProgressDialog
import kotlinx.android.synthetic.main.fragment_home.*
import org.koin.androidx.viewmodel.ext.android.viewModel


class HomeFragment : Fragment() {
    private val viewModel: HomeViewModel by viewModel()
    private val providersList = ArrayList<DataItem>()
    private var progressDialog: ProgressDialog? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        activity?.let {
            progressDialog = ProgressDialog(it)
        }
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initProvidersAdapter()
        providersHandler()
        handlePagination()
        handleFilterSpinner()
        handelSearch()
        handleMapBTN()
        handleProgress()
        handelError()
        handelSpecialization()

        viewModel.getProviders()


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
        val dividerItemDecoration = DividerItemDecoration(providersRV.context,
            (providersRV.layoutManager as LinearLayoutManager).orientation
        );
        providersRV.addItemDecoration(dividerItemDecoration)
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

    private fun handelSpecialization() {

        specialization.setOnClickListener {
            findNavController().navigate(R.id.specializationFragment)
        }

        findNavController().currentBackStackEntry?.savedStateHandle?.getLiveData<Int>("specificationID")?.observe(
            viewLifecycleOwner) { result ->
            // Do something with the result.
            viewModel.specificationID=result
            viewModel.filter(true)
        }
    }

    private fun handleFilterSpinner() {
        filter.layoutManager = LinearLayoutManager(requireContext(), RecyclerView.HORIZONTAL, false)
        filter.adapter = FilterAdapter(resources.getStringArray(R.array.filter).toList(), ::filter)
    }

    private fun filter(position: Int) {
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

    private fun handelSearch() {
        searchInput.setOnEditorActionListener { v, actionId, _ ->
            if (actionId === EditorInfo.IME_ACTION_SEARCH) {

                viewModel.key = v.text.toString()
                viewModel.filter(true)
                return@setOnEditorActionListener true
            }
            false
        }
    }

    private fun handleMapBTN() {
        mapBTN.setOnClickListener {
            findNavController().navigate(R.id.mapsFragment)
        }
    }

    private fun handleProgress() {
        viewModel.showLoading.observe(viewLifecycleOwner, Observer {
            if (it)
                progressDialog?.show()
            else
                progressDialog?.hide()
        })
    }

    private fun handelError() {
        viewModel.errorLiveData.observe(viewLifecycleOwner, Observer {
            view?.let { it1 -> Snackbar.make(it1, it, Snackbar.LENGTH_SHORT).show() }
        })
    }
}