package com.ads.juused.ui.auth.register

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.ads.juused.R
import com.ads.juused.base.BaseFragment
import com.ads.juused.databinding.FragmentOtpBinding
import solo.android.ui.base.BaseViewModel


class OtpFragment :  BaseFragment<BaseViewModel, FragmentOtpBinding>() {

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
    ): FragmentOtpBinding = FragmentOtpBinding.inflate(inflater,container,false)

    override fun enableBackPress(): Boolean = true

    private fun bindClicks() {
        binding.btnContinue.setOnClickListener {
            navController.navigate(R.id.action_otpFragment_to_registerFragment)
        }

    }
}