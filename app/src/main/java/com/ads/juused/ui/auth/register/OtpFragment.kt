package com.ads.juused.ui.auth.register

import android.os.Bundle
import android.text.Editable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.core.content.ContextCompat
import androidx.core.widget.doAfterTextChanged
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.ads.juused.R
import com.ads.juused.base.BaseFragment
import com.ads.juused.databinding.FragmentOtpBinding
import com.ads.juused.utility.disableView
import com.ads.juused.utility.trimText
import solo.android.ui.base.BaseViewModel


class OtpFragment :  BaseFragment<BaseViewModel, FragmentOtpBinding>() {

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
    ): FragmentOtpBinding = FragmentOtpBinding.inflate(inflater,container,false)

    override fun enableBackPress(): Boolean = true

    private fun init() {
        binding.btnContinue.disableView(
            disable = true,
            disableColor = ContextCompat.getColor(requireContext(),R.color.colorGreyDark),
            enableColor = ContextCompat.getColor(requireContext(),R.color.colorRedAccent)
        )

        binding.etOtp1.validateEmpty()
        binding.etOtp2.validateEmpty()
        binding.etOtp3.validateEmpty()
        binding.etOtp4.validateEmpty()

    }

    private fun bindClicks() {
        binding.btnContinue.setOnClickListener {
            navController.navigate(R.id.action_otpFragment_to_registerFragment)
        }
    }

    private fun EditText.validateEmpty() {
        this.doAfterTextChanged {
            val isEmpty = getOtp().isEmpty()

            binding.btnContinue.disableView(
                disable = isEmpty,
                disableColor = ContextCompat.getColor(requireContext(),R.color.colorGreyDark),
                enableColor = ContextCompat.getColor(requireContext(),R.color.colorRedAccent)
            )
        }
    }

    private fun getOtp() =
        binding.etOtp1.trimText() +
                binding.etOtp1.trimText() +
                binding.etOtp1.trimText() +
                binding.etOtp1.trimText()
}