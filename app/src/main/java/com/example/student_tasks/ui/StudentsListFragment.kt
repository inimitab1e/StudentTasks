package com.example.student_tasks.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.student_tasks.R
import com.example.student_tasks.adapters.StudentsListAdapter
import com.example.student_tasks.databinding.FragmentStudentsListBinding
import com.example.student_tasks.viewmodel.StudentListViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class StudentsListFragment : Fragment() {

    private var binding: FragmentStudentsListBinding? = null
    private val studentListViewModel by viewModels<StudentListViewModel>()
    private val usersAdapter: StudentsListAdapter by lazy {
        StudentsListAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentStudentsListBinding.inflate(inflater, container, false)
        studentListViewModel.updateList()
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.rwStudentsList?.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = usersAdapter
        }

        binding?.btnLogout?.setOnClickListener {
            studentListViewModel.logout()
            findNavController().navigate(R.id.action_studentsListFragment_to_loginFragment)
        }

        studentListViewModel.userList.observe(viewLifecycleOwner) { it ->
            with(usersAdapter) {
                setUsers(it)
                notifyDataSetChanged()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}