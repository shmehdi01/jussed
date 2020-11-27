package com.ads.juused.ui.auth.register

import android.os.Bundle
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
import com.ads.juused.databinding.FragmentRegisterBinding
import com.ads.juused.utility.disableView
import com.ads.juused.utility.showSnack
import com.ads.juused.utility.trimText
import solo.android.ui.base.BaseViewModel


class RegisterFragment :  BaseFragment<BaseViewModel, FragmentRegisterBinding>() {

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
    ): FragmentRegisterBinding = FragmentRegisterBinding.inflate(inflater,container,false)

    override fun enableBackPress(): Boolean = true

    override fun validate(showToast: Boolean, call: () -> Unit): Boolean {
        var message = ""
        var validated = true

        binding.tlFirstName.error = message
        binding.tlLastName.error = message
        binding.tlUserName.error = message

        when {
            binding.etFirstName.trimText().isEmpty() -> {
                message = getString(R.string.firstname_empty_err)
                validated = false
                binding.etFirstName.requestFocus()
                binding.tlFirstName.error = message
            }

            binding.etLastName.trimText().isEmpty() -> {
                message = getString(R.string.last_name_empty_err)
                validated = false
                binding.etLastName.requestFocus()
                binding.tlLastName.error = message
            }

            binding.etUsername.trimText().isEmpty() -> {
                message = getString(R.string.username_is_empty)
                validated = false
                binding.etUsername.requestFocus()
                binding.tlUserName.error = message
            }
        }

        if(validated) {
            call.invoke()
        }

        return validated
    }

    private fun init() {
        binding.etLastName.validateContinueButton()
        binding.etFirstName.validateContinueButton()
    }

    private fun bindClicks() {
        binding.btnContinue.setOnClickListener {
            validate {
                //Todo next
            }
        }
    }

    private fun EditText.validateContinueButton() {
        this.doAfterTextChanged {
            val shouldDisabled = it?.trim().isNullOrEmpty()
            binding.btnContinue.disableView(
                disable = shouldDisabled,
                disableColor = ContextCompat.getColor(requireContext(),R.color.colorGreyDark),
                enableColor = ContextCompat.getColor(requireContext(),R.color.colorRedAccent)
            )
        }
    }
}