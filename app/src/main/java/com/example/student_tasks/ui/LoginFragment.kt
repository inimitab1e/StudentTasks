package com.example.student_tasks.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.student_tasks.MainActivity
import com.example.student_tasks.R
import com.example.student_tasks.databinding.FragmentLoginBinding
import com.example.student_tasks.utils.StringConstants
import com.example.student_tasks.viewmodel.LoginViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment() : Fragment() {

    private var binding: FragmentLoginBinding? = null
    private val loginViewModel by viewModels<LoginViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.apply {
            textCreateAccLink.setOnClickListener {
                findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
            }

            btnLogin.setOnClickListener {
                doLogin()
            }
        }

        loginViewModel.responseState.observe(viewLifecycleOwner) {
            if (it == StringConstants.onSuccessLoggedIn) {
                findNavController().navigate(R.id.action_loginFragment_to_studentsListFragment)
            } else {
                onErrorLoggedIn(it)
            }
        }
    }

    private fun doLogin() {
        val email = binding?.emailEditLog?.text.toString()
        val password = binding?.passwordEditLog?.text.toString()

        loginViewModel.loginUser(email, password)
    }

    private fun onErrorLoggedIn(message: String) {
        Toast.makeText(activity, message, Toast.LENGTH_SHORT).show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}