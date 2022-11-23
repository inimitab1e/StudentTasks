package com.example.student_tasks.ui.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.student_tasks.R
import com.example.student_tasks.databinding.FragmentLoginBinding
import com.example.student_tasks.viewmodel.LoginViewModel
import com.example.student_tasks.viewmodel.RegisterViewModel

class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding
    private val loginViewModel by viewModels<LoginViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            textCreateAccLink.setOnClickListener {
                findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
            }

            btnLogin.setOnClickListener {
                doLogin()
                findNavController().navigate(R.id.action_loginFragment_to_studentsListFragment)
            }
        }
    }

    private fun doLogin() {
        val email = binding.emailEditLog.text.toString()
        val password = binding.passwordEditLog.text.toString()

        loginViewModel.loginUser(email, password)
    }
}