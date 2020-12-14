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
import com.ads.juused.utility.isMobileNumber
import com.ads.juused.utility.trimText
import com.ads.juused.base.BaseViewModel


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

    override fun validate(showToast: Boolean, call: () -> Unit): Boolean {
        val message: String
        var validated = true

        binding.tlMobile.error = null

        when {
            binding.etMobile.trimText().isEmpty() -> {
                message = getString(R.string.mobile_is_empty)
                validated = false
                binding.tlMobile.error = message
                binding.etMobile.requestFocus()
            }

            !binding.etMobile.trimText().isMobileNumber() -> {
                message = getString(R.string.mobile_is_not_valid)
                validated = false
                binding.tlMobile.error = message
                binding.etMobile.requestFocus()
            }
        }

        if(validated) {
            call.invoke()
        }

        return validated
    }

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
            validate {
                navController.navigate(R.id.action_mobileFragment_to_otpFragment)
            }
        }
    }
}