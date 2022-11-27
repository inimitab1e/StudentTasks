package com.example.student_tasks.ui.content

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.student_tasks.adapters.StudentsListAdapter
import com.example.student_tasks.data.room.UserRepository
import com.example.student_tasks.data.room.Users
import com.example.student_tasks.databinding.FragmentStudentsListBinding
import com.example.student_tasks.viewmodel.StudentListViewModel

class StudentsListFragment : Fragment() {

    private lateinit var binding: FragmentStudentsListBinding
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
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.rwStudentsList.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = StudentsListAdapter()
        }
        studentListViewModel.userList.observe(viewLifecycleOwner) {
            usersAdapter.setUsers(studentListViewModel.userList.value!!)
            usersAdapter.notifyDataSetChanged()
        }
    }

//    private fun fetchUsers() {
//        val allUsers = roomRepo.getAllUsers()
//        usersAdapter.setUsers(allUsers)
//    }
}