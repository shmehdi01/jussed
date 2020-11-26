package com.ads.juused.ui.auth.register

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.NumberPicker
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.transition.TransitionInflater
import com.ads.juused.R
import com.ads.juused.base.BaseFragment
import com.ads.juused.databinding.FragmentCountryBinding
import com.ads.juused.utility.showToast
import com.ads.juused.utility.toDp
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

        val values= arrayOf("Afghanistan","Albania", "Algeria", "India", "United Kingdom")
        binding.np.apply {
            minValue = 0
            maxValue = values.size-1
            displayedValues = values
            wrapSelectorWheel = true
            setOnValueChangedListener { _, _, newVal ->
                //showToast(values[newVal])
                npEditText()
            }
            npEditText()
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

    private fun NumberPicker.npEditText() {
       for (i in 0 until childCount) {
           val child = getChildAt(i)
           if(child is EditText) {
               try {
                   child.layoutParams.width = 300f.toDp(requireContext())
                   child.layoutParams.height = 50f.toDp(requireContext())
                   child.setBackgroundResource(R.drawable.bg_cyan_rounder)
                   invalidate()
               } catch (ex: java.lang.Exception) {
                   // Ignore
               }
           }
       }
    }
}