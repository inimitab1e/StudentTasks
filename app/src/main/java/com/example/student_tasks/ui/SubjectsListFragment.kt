package com.example.student_tasks.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.student_tasks.R
import com.example.student_tasks.databinding.FragmentSubjectsListBinding

class SubjectsListFragment : Fragment() {
    private var binding: FragmentSubjectsListBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSubjectsListBinding.inflate(inflater, container, false)
        return binding!!.root
    }

}