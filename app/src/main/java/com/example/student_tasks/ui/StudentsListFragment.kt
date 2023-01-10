package com.example.student_tasks.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.student_tasks.R
import com.example.student_tasks.adapters.StudentsListAdapter
import com.example.student_tasks.databinding.FragmentStudentsListBinding
import com.example.student_tasks.ui.viewmodel.StudentListViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView
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
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val bottomNavigation =
            activity?.findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        bottomNavigation?.isVisible = true

        studentListViewModel.updateList()

        studentListViewModel.userList.observe(viewLifecycleOwner) {
            with(usersAdapter) {
                setUsers(it)
                notifyDataSetChanged()
            }

            binding!!.apply {
                refreshLayout.setOnRefreshListener {
                    refreshLayout.isRefreshing = false
                    studentListViewModel.updateList()
                    with(usersAdapter) {
                        setUsers(it)
                        notifyDataSetChanged()
                    }
                }
            }
        }

        binding!!.apply {
            rwStudentsList.apply {
                layoutManager = LinearLayoutManager(activity)
                adapter = usersAdapter
            }

            btnLogout.setOnClickListener {
                bottomNavigation?.isGone = true
                studentListViewModel.logout()
                findNavController().navigate(R.id.action_studentsListFragment_to_loginFragment)
            }
<<<<<<< Updated upstream
=======

            refreshLayout.setOnRefreshListener {
                studentListViewModel.updateList()
                usersAdapter.notifyDataSetChanged()
            }
>>>>>>> Stashed changes
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}