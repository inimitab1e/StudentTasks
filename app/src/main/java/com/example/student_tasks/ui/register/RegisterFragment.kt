package com.example.student_tasks.ui.register

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.student_tasks.databinding.FragmentRegisterBinding
import com.example.student_tasks.interfaces.LoginResultCallBacks
import com.example.student_tasks.viewmodel.RegisterViewModel

class RegisterFragment : Fragment(), LoginResultCallBacks {

    private lateinit var binding: FragmentRegisterBinding
    private val registerViewModel by viewModels<RegisterViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRegisterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            textLoginLink.setOnClickListener {
                findNavController().navigateUp()
            }

            btnCreateAccount.setOnClickListener {
                doRegister()
            }
        }
    }

    private fun doRegister() {
        val username = binding.profileNameEdit.text.toString()
        val email = binding.emailEditReg.text.toString()
        val password = binding.passwordEditReg.text.toString()

        registerViewModel.registerUser(username, email, password)
    }

    override fun onSuccess(message: String) =
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()

    override fun onError(message: String) =
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
}