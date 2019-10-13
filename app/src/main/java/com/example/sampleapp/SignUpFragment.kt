package com.example.sampleapp

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthInvalidUserException
import kotlinx.android.synthetic.main.fragment_sign_up.*

class SignUpFragment : BaseFragment(R.layout.fragment_sign_up) {
    private lateinit var auth: FirebaseAuth

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        auth = FirebaseAuth.getInstance()
        bt_enter.setOnClickListener { onSignUpUser() }
    }

    private fun onSignUpUser() {
        val email = et_login.text.toString()
        val pass = et_pass.text.toString()
        bt_enter.hideKeyboard()
        auth.createUserWithEmailAndPassword(email, pass)
            .addOnSuccessListener {
                Log.d("SignUpFragment", "onSuccess $it")
                navController.navigate(R.id.action_signUpFragment_to_verifyEmailFragment)
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
            }
    }
}