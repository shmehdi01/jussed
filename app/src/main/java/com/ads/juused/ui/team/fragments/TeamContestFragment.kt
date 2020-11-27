package com.ads.juused.ui.team.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.ads.juused.R
import com.ads.juused.base.BaseFragment
import com.ads.juused.databinding.FragmentTeamContestsBinding
import com.ads.juused.databinding.FragmentTeamLeagueBinding
import com.ads.juused.ui.team.adapter.TeamContestAdapter
import com.ads.juused.ui.team.adapter.TeamLeagueAdapter
import solo.android.ui.base.BaseViewModel


class TeamContestFragment : BaseFragment<BaseViewModel,FragmentTeamContestsBinding>() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setToolbar(title = "Team Contests")
        setUpRecycler()
    }

    override fun getViewModel(): Class<BaseViewModel> = BaseViewModel::class.java

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentTeamContestsBinding = FragmentTeamContestsBinding.inflate(inflater,container,false)

    private fun setUpRecycler() {
        binding.rcvTeam.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = TeamContestAdapter()
        }
    }
}