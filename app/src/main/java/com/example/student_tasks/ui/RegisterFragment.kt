package com.example.student_tasks.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.student_tasks.R
import com.example.student_tasks.databinding.FragmentRegisterBinding
import com.example.student_tasks.utils.StringConstants
import com.example.student_tasks.viewmodel.RegisterViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegisterFragment : Fragment() {

    private var binding: FragmentRegisterBinding? = null
    private val registerViewModel by viewModels<RegisterViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRegisterBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.apply {
            textLoginLink.setOnClickListener {
                findNavController().navigate(R.id.action_registerFragment_to_loginFragment)
            }

            btnCreateAccount.setOnClickListener {
                doRegister()
            }
        }

        registerViewModel.state.observe(viewLifecycleOwner) {
            if (it == StringConstants.onSuccessLoggedIn) {
                findNavController().navigate(R.id.action_registerFragment_to_studentsListFragment)
            } else {
                onErrorLoggedUp(it)
            }
        }
    }

    private fun doRegister() {
        val username = binding?.profileNameEdit?.text.toString()
        val email = binding?.emailEditReg?.text.toString()
        val password = binding?.passwordEditReg?.text.toString()

        registerViewModel.registerUser(username, email, password)
    }

    private fun onErrorLoggedUp(message: String) {
        Toast.makeText(activity, message, Toast.LENGTH_SHORT).show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

}