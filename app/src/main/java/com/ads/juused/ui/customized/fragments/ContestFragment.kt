package com.ads.juused.ui.customized.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.ads.juused.R
import com.ads.juused.base.BaseFragment
import com.ads.juused.base.BaseViewModel
import com.ads.juused.databinding.FragmentContestBinding
import com.ads.juused.databinding.FragmentTeamContestsBinding
import com.ads.juused.ui.team.adapter.TeamContestAdapter


class ContestFragment : BaseFragment<BaseViewModel, FragmentContestBinding>() {

    private lateinit var navController: NavController

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navController = Navigation.findNavController(view)

        setToolbar(title = "Contest")
        setClicks()
    }

    override fun getViewModel(): Class<BaseViewModel> = BaseViewModel::class.java

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentContestBinding = FragmentContestBinding.inflate(inflater,container,false)


    private fun setClicks() {
        binding.clCreateContest.setOnClickListener {
            navController.navigate(R.id.action_contestFragment_to_chooseSportFragment)
        }
    }
}