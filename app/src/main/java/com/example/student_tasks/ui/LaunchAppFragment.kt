package com.example.student_tasks.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.student_tasks.R
import com.example.student_tasks.utils.StringConstants
import com.example.student_tasks.viewmodel.LaunchAppViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LaunchAppFragment : Fragment() {

    private val launchAppViewModel by viewModels<LaunchAppViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_launch_app, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        launchAppViewModel.checkIfUserValid()
        autoLoginCheck()
    }

    private fun autoLoginCheck() {
        launchAppViewModel.userExists.observe(viewLifecycleOwner) {
            if (it == StringConstants.userNotExists) {
                findNavController().navigate(R.id.action_launchAppFragment_to_registerFragment)
            }
        }

        launchAppViewModel.isTokenValid.observe(viewLifecycleOwner) {
            if (it == StringConstants.tokenValid) {
                findNavController().navigate(R.id.action_launchAppFragment_to_studentsListFragment)
            } else {
                findNavController().navigate(R.id.action_launchAppFragment_to_loginFragment)
            }
        }

        launchAppViewModel.isRefreshSuccess.observe(viewLifecycleOwner) {
            if (it == StringConstants.refreshSuccess) {
                findNavController().navigate(R.id.action_launchAppFragment_to_studentsListFragment)
            } else {
                findNavController().navigate(R.id.action_launchAppFragment_to_loginFragment)
            }
        }
    }
}