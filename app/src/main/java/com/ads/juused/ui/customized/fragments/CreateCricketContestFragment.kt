package com.ads.juused.ui.customized.fragments

import android.animation.ObjectAnimator
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.ads.juused.R
import com.ads.juused.base.BaseFragment
import com.ads.juused.base.BaseViewModel
import com.ads.juused.databinding.FragmentCreateCricketContestBinding


class CreateCricketContestFragment :
    BaseFragment<BaseViewModel, FragmentCreateCricketContestBinding>() {

    private lateinit var navController: NavController
    var progress = arrayOf(5, 40, 65, 100)
    var currentStep = 0 //to 3

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navController = Navigation.findNavController(view)

        setToolbar(title = "Create Cricket Contest")
        setClicks()
        setGameSpinner()
    }

    override fun getViewModel(): Class<BaseViewModel> = BaseViewModel::class.java

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentCreateCricketContestBinding =
        FragmentCreateCricketContestBinding.inflate(inflater, container, false)

    private fun setClicks() {
        binding.btnContinue.setOnClickListener {
            changeProgressToNextStep()
        }
    }

    private fun changeProgressToNextStep() {
        if (++currentStep < progress.size) {
            ObjectAnimator.ofInt(binding.progressBar, "progress", progress[currentStep]).apply {
                duration = 500
            }.start()
        }
    }

    private fun setGameSpinner() {
        val array = arrayOf("Vivo IPL League", "Bangladesh Premiere League", "Vivo IPL League")
        binding.spinnerGame.adapter = ArrayAdapter(requireContext(), R.layout.item_spinner_text, R.id.text, array)
    }

}