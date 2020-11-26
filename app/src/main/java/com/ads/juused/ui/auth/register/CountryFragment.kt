package com.ads.juused.ui.auth.register

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.transition.TransitionInflater
import com.ads.juused.R
import com.ads.juused.base.BaseFragment
import com.ads.juused.databinding.FragmentCountryBinding
import com.ads.juused.utility.showToast
import solo.android.ui.base.BaseViewModel


class CountryFragment : BaseFragment<BaseViewModel, FragmentCountryBinding>() {

    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedElementEnterTransition = TransitionInflater.from(context).inflateTransition(android.R.transition.move)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navController = Navigation.findNavController(view)
        bindClicks()

        val values= arrayOf("English","Hindi", "Suomi")
        binding.np.apply {
            minValue = 0
            maxValue = values.size-1
            displayedValues = values
            wrapSelectorWheel = true
            setOnValueChangedListener { _, _, newVal ->
                showToast(values[newVal])
            }
        }

    }

    override fun getViewModel(): Class<BaseViewModel> = BaseViewModel::class.java

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentCountryBinding = FragmentCountryBinding.inflate(inflater,container,false)

    override fun enableBackPress(): Boolean = true

    private fun bindClicks() {
        binding.btnContinue.setOnClickListener {
            navController.navigate(R.id.action_languageFragment_to_emailFragment)
        }
    }
}