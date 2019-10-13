package com.example.sampleapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth

class FirstFragment : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val navController = findNavController()
        val user = FirebaseAuth.getInstance().currentUser
        if (user != null && user.isEmailVerified) {
            navController.navigate(R.id.action_firstFragment_to_mainFragment)
        } else {
            navController.navigate(R.id.action_firstFragment_to_signInFragment)
        }

    }
}