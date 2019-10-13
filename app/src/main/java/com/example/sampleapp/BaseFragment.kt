package com.example.sampleapp

import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation

open class BaseFragment(@LayoutRes id:Int) : Fragment(id) {
    val navController by lazy(LazyThreadSafetyMode.NONE) {
        Navigation.findNavController(activity!!, R.id.root_nav_host)
    }
}