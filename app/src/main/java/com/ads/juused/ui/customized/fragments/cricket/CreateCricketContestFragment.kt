package com.ads.juused.ui.customized.fragments.cricket

import android.animation.ObjectAnimator
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import com.ads.juused.R
import com.ads.juused.base.BaseFragment
import com.ads.juused.base.BaseViewModel
import com.ads.juused.databinding.FragmentCreateCricketContestBinding
import com.ads.juused.utility.disableView
import com.ads.juused.utility.showToast

/**
 *  CreateCricketContestFragment: To create a contest of cricket
 *  ------------------------------------------------------------
 *  This fragment contains 4 fragments:
 *  ------------------------------------------------------------
 *  1. CreateCricketContestLeagueFragment (Step 1)
 *  2. CreateCricketContestScoringFragment (Step 2)
 *  3. CreateCricketContestRostersFragment (Step 3)
 *  4. CreateCricketContestFriendsFragment (Step 4)
 *
 */
class CreateCricketContestFragment :
    BaseFragment<BaseViewModel, FragmentCreateCricketContestBinding>() {

    private lateinit var navController: NavController
    var progress = arrayOf(5, 40, 65, 100)
    var currentStep = 0 //to 3

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navController = Navigation.findNavController(view)

        enableButton(false)
        setToolbar(title = "Create Cricket Contest")
        setClicks()

    }

    override fun getViewModel(): Class<BaseViewModel> = BaseViewModel::class.java

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentCreateCricketContestBinding =
        FragmentCreateCricketContestBinding.inflate(inflater, container, false)

    fun enableButton(enable: Boolean) {
        binding.btnContinue.disableView(
            disable = !enable,
            disableColor = ContextCompat.getColor(requireContext(), R.color.colorGreyDark),
            enableColor = ContextCompat.getColor(requireContext(), R.color.colorRedAccent)
        )
    }

    private fun setClicks() {
        val host = childFragmentManager.findFragmentById(R.id.fragment_container) as NavHostFragment
        val navController = host.navController

        binding.btnContinue.setOnClickListener {

            when (val fragment =
                childFragmentManager.fragments[0].childFragmentManager.fragments[0]) {
                is CreateCricketContestLeagueFragment -> {

                    if (!fragment.canGoToNext) {
                        if (fragment.isCompleted) {
                            fragment.goToFinal()
                        } else if (fragment.isContestSelected) {
                            fragment.showSelectedContest()
                        }
                        return@setOnClickListener
                    }
                    navController.navigate(R.id.action_createCricketContestLeagueFragment_to_createCricketContestScoringFragment)
                    changeProgressToNextStep(1)
                }

                is CreateCricketContestScoringFragment -> {
                    navController.navigate(R.id.action_createCricketContestScoringFragment_to_createCricketContestRostersFragment)
                    changeProgressToNextStep(2)
                }

                is CreateCricketContestRostersFragment -> {
                    navController.navigate(R.id.action_createCricketContestRostersFragment_to_createCricketContestFriendsFragment)
                    changeProgressToNextStep(3)
                }

                is CreateCricketContestFriendsFragment -> {
                    showToast("Last Step", debug = true)
                }
            }
        }

        childFragmentManager.fragments[0].childFragmentManager.addOnBackStackChangedListener {
            when (childFragmentManager.fragments[0].childFragmentManager.fragments[0]) {
                is CreateCricketContestLeagueFragment -> {
                    changeProgressToNextStep(0)
                }

                is CreateCricketContestScoringFragment -> {
                    changeProgressToNextStep(1)
                }

                is CreateCricketContestRostersFragment -> {
                    changeProgressToNextStep(2)
                }

                is CreateCricketContestFriendsFragment -> {

                }
            }
        }
    }

    private fun changeProgressToNextStep(step: Int) {
        if (step < progress.size) {
            currentStep = step
            ObjectAnimator.ofInt(binding.progressBar, "progress", progress[currentStep]).apply {
                duration = 500
            }.start()
        }
    }
}