package com.example.sampleapp

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthInvalidUserException
import kotlinx.android.synthetic.main.fragment_sign_in.*

class SignInFragment : BaseFragment(R.layout.fragment_sign_in) {
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        auth = FirebaseAuth.getInstance()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bt_enter.setOnClickListener {
            signWithEmail()
        }

        tv_sign_up.setOnClickListener {
            navController.navigate(R.id.action_signInFragment_to_signUpFragment)
        }
    }

    private fun signWithEmail() {
        val email = et_login.text.toString()
        val pass = et_pass.text.toString()
        bt_enter.hideKeyboard()
        auth.signInWithEmailAndPassword(email, pass)
            .addOnSuccessListener {
                Log.d("SignInFragment", "signWithEmail $it")
                navController.navigate(R.id.action_signInFragment_to_mainFragment)
            }
            .addOnFailureListener {
                when (it) {
                    is FirebaseAuthInvalidCredentialsException -> {
                        Toast.makeText(
                            requireContext(),
                            "email badly formatted",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                    is FirebaseAuthInvalidUserException -> {
                        Toast.makeText(
                            requireContext(),
                            "user not found",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
                Log.d("SignInFragment", "signWithEmail fail $it")
            }
    }
}