package com.ads.juused.ui.auth.register

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.ads.juused.base.BaseFragment
import com.ads.juused.databinding.FragmentRegisterBinding
import solo.android.ui.base.BaseViewModel


class RegisterFragment :  BaseFragment<BaseViewModel, FragmentRegisterBinding>() {

    private lateinit var navController: NavController

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navController = Navigation.findNavController(view)
        bindClicks()
    }

    override fun getViewModel(): Class<BaseViewModel> = BaseViewModel::class.java

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentRegisterBinding = FragmentRegisterBinding.inflate(inflater,container,false)

    private fun bindClicks() {

    }
}