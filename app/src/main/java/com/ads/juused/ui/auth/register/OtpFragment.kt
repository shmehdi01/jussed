package com.ads.juused.ui.auth.register

import android.os.Bundle
import android.view.KeyEvent
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
import com.ads.juused.base.BaseViewModel


class OtpFragment : BaseFragment<BaseViewModel, FragmentOtpBinding>() {

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
    ): FragmentOtpBinding = FragmentOtpBinding.inflate(inflater, container, false)

    override fun enableBackPress(): Boolean = true

    override fun validate(showToast: Boolean, call: () -> Unit): Boolean = when {
        getOtp().length != 4 -> false
        else -> {
            call.invoke()
            true
        }
    }


    private fun init() {
        binding.btnContinue.disableView(
            disable = true,
            disableColor = ContextCompat.getColor(requireContext(), R.color.colorGreyDark),
            enableColor = ContextCompat.getColor(requireContext(), R.color.colorRedAccent)
        )

        binding.etOtp1.setOtpTextChange(prevOtpField = null, nextOtpField = binding.etOtp2)
        binding.etOtp2.setOtpTextChange(
            prevOtpField = binding.etOtp1,
            nextOtpField = binding.etOtp3
        )
        binding.etOtp3.setOtpTextChange(
            prevOtpField = binding.etOtp2,
            nextOtpField = binding.etOtp4
        )
        binding.etOtp4.setOtpTextChange(prevOtpField = binding.etOtp3, nextOtpField = null)

    }

    private fun bindClicks() {
        binding.btnContinue.setOnClickListener {
            validate {
                navController.navigate(R.id.action_otpFragment_to_registerFragment)
            }
        }
    }

    private fun getOtp() =
        binding.etOtp1.trimText() +
                binding.etOtp2.trimText() +
                binding.etOtp3.trimText() +
                binding.etOtp4.trimText()


    private fun EditText.setOtpTextChange(nextOtpField: EditText?, prevOtpField: EditText?) {

        setOnKeyListener { _, keyCode, _ ->
            if (keyCode == KeyEvent.KEYCODE_DEL) {
                nextOtpField?.isEnabled = false
                prevOtpField?.isEnabled = true
                prevOtpField?.requestFocus()
            }
            false
        }


        if (prevOtpField != null) {
            isEnabled = false
        }

        this.doAfterTextChanged {

            if (text.toString().isNotEmpty()) {
                nextOtpField?.isEnabled = true
                prevOtpField?.isEnabled = false
                nextOtpField?.requestFocus()
                nextOtpField?.setText("")

            }
            binding.btnContinue.disableView(
                disable = getOtp().length != 4,
                disableColor = ContextCompat.getColor(requireContext(), R.color.colorGreyDark),
                enableColor = ContextCompat.getColor(requireContext(), R.color.colorRedAccent)
            )
        }
    }
}