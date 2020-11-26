package com.ads.juused.ui.auth.register

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.widget.doAfterTextChanged
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.ads.juused.R
import com.ads.juused.base.BaseFragment
import com.ads.juused.databinding.FragmentMobileBinding
import com.ads.juused.utility.disableView
import solo.android.ui.base.BaseViewModel


class MobileFragment : BaseFragment<BaseViewModel, FragmentMobileBinding>() {

    private lateinit var navController: NavController

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navController = Navigation.findNavController(view)

        init()
        bindClicks()
    }

    override fun getViewModel(): Class<BaseViewModel> = BaseViewModel::class.java

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentMobileBinding = FragmentMobileBinding.inflate(inflater,container,false)

    override fun enableBackPress(): Boolean = true

    private fun init() {
        binding.btnContinue.disableView(
            disable = true,
            disableColor = ContextCompat.getColor(requireContext(),R.color.colorGreyDark),
            enableColor = ContextCompat.getColor(requireContext(),R.color.colorRedAccent)
        )

        binding.etMobile.doAfterTextChanged {
            val isEmpty = it?.toString()?.isEmpty()  == true

            binding.btnContinue.disableView(
                disable = isEmpty,
                disableColor = ContextCompat.getColor(requireContext(),R.color.colorGreyDark),
                enableColor = ContextCompat.getColor(requireContext(),R.color.colorRedAccent)
            )
        }
    }

    private fun bindClicks() {
        binding.btnContinue.setOnClickListener {
            navController.navigate(R.id.action_mobileFragment_to_otpFragment)
        }
    }
}