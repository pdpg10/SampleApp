package com.example.sampleapp

import android.os.Bundle
import android.view.View
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.fragment_verify.*

class VerifyEmailFragment : BaseFragment(R.layout.fragment_verify) {
    private lateinit var auth: FirebaseAuth

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        auth = FirebaseAuth.getInstance()
        bt_enter.setOnClickListener {
            auth.currentUser
                ?.sendEmailVerification()
                ?.addOnSuccessListener {
                    navController.popBackStack(R.id.signInFragment,false)
                }
        }
    }
}