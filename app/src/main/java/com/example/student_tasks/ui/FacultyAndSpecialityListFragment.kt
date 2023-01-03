package com.example.student_tasks.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.student_tasks.adapters.FacultyAndSpecialityListAdapter
import com.example.student_tasks.data.model.ExpandableFacAndSpecModel
import com.example.student_tasks.databinding.FragmentSubjectsListBinding
import com.example.student_tasks.ui.viewmodel.FacultyAndSpecialityListViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FacultyAndSpecialityListFragment : Fragment() {
    private var binding: FragmentSubjectsListBinding? = null
    private val facAndSpecListViewModel by viewModels<FacultyAndSpecialityListViewModel>()
    var facAndSpecListAdapter: FacultyAndSpecialityListAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSubjectsListBinding.inflate(inflater, container, false)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        facAndSpecListViewModel.obtainFacAndSpec(requireContext())
        observeViewModelResponse()
    }

    private fun observeViewModelResponse() {
        facAndSpecListViewModel.facAndSpecResponse.observe(viewLifecycleOwner, Observer {
            val facAndSpecInfo = facAndSpecListViewModel.prepareDataForExpandableAdapter(it)
            populateAdapterWithInfo(facAndSpecInfo)
        })
    }


    private fun populateAdapterWithInfo(expandableFacAndSpecList: MutableList<ExpandableFacAndSpecModel>) {
        facAndSpecListAdapter = FacultyAndSpecialityListAdapter(expandableFacAndSpecList)
        facAndSpecListAdapter?.let {
            binding!!.apply {
                val layoutManager = LinearLayoutManager(context)
                rwSubjectsList.layoutManager = layoutManager
                rwSubjectsList.adapter = it
                rwSubjectsList.addItemDecoration(
                    DividerItemDecoration(
                        activity,
                        DividerItemDecoration.VERTICAL
                    )
                )
                it.notifyDataSetChanged()
            }

        }
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}