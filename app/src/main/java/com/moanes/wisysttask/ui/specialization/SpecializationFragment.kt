package com.moanes.wisysttask.ui.specialization

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.moanes.wisysttask.R
import com.moanes.wisysttask.data.model.specifications.Specification
import kotlinx.android.synthetic.main.fragment_specialization.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class SpecializationFragment : Fragment() {
    private val viewModel: SpecializationViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_specialization, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        specializationHandler()
        handleSearch()
        viewModel.getSpecification()

        backBtn.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun specializationHandler() {
        viewModel.specificationsLiveData.observe(viewLifecycleOwner, Observer {
            it?.let { list ->
                initProvidersAdapter(list)
            }
        })
    }

    private fun initProvidersAdapter(list: List<Specification>) {
        specializationsRV.layoutManager = LinearLayoutManager(requireContext())
        specializationsRV.adapter = SpecializationAdapter(ArrayList(list), ::onClick)
        val dividerItemDecoration = DividerItemDecoration(
            specializationsRV.context,
            (specializationsRV.layoutManager as LinearLayoutManager).orientation
        )
        specializationsRV.addItemDecoration(dividerItemDecoration)
    }

    private fun handleSearch() {
        searchInput.setOnEditorActionListener { v, actionId, _ ->
            if (actionId === EditorInfo.IME_ACTION_SEARCH) {

                (specializationsRV.adapter as SpecializationAdapter).filter?.filter(v.text.toString())
                return@setOnEditorActionListener true
            }
            false
        }
    }

    private fun onClick(id: Int) {
        findNavController().previousBackStackEntry?.savedStateHandle?.set("specificationID", id)
        findNavController().popBackStack()
    }
}