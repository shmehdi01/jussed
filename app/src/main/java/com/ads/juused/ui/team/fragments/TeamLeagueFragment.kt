package com.ads.juused.ui.team.fragments

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
import com.ads.juused.databinding.FragmentTeamLeagueBinding
import com.ads.juused.ui.team.adapter.TeamLeagueAdapter
import solo.android.ui.base.BaseViewModel


class TeamLeagueFragment : BaseFragment<BaseViewModel,FragmentTeamLeagueBinding>() {

    private lateinit var navController: NavController

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navController = Navigation.findNavController(view)

        setToolbar(title = "Team Leagues")
        setUpRecycler()
    }

    override fun getViewModel(): Class<BaseViewModel> = BaseViewModel::class.java

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentTeamLeagueBinding = FragmentTeamLeagueBinding.inflate(inflater,container,false)

    private fun setUpRecycler() {
        binding.rcvTeam.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = TeamLeagueAdapter() {
                navController.navigate(R.id.action_teamLeagueFragment_to_teamContestFragment)
            }
        }
    }
}