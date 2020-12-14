package com.ads.juused.ui.team.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.ads.juused.R
import com.ads.juused.base.BaseFragment
import com.ads.juused.base.BaseViewModel
import com.ads.juused.databinding.FragmentTeamLeagueBinding
import com.ads.juused.ui.player.PlayerPickupActivity
import com.ads.juused.ui.team.TeamActivity
import com.ads.juused.ui.team.adapter.TeamLeagueAdapter

/**
 *  TeamLeagueFragment: It shows the list of leagues.
 *  -------------------------------------------------
 *  This fragment is open from:
 *  ---------------------------
 *  1.TeamActivity and
 *  2.PlayerPickUpActivity
 */
class TeamLeagueFragment : BaseFragment<BaseViewModel, FragmentTeamLeagueBinding>() {

    private lateinit var navController: NavController

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navController = Navigation.findNavController(view)

        setToolbar(title = getToolbarTitle())
        setUpRecycler()
    }

    override fun getViewModel(): Class<BaseViewModel> = BaseViewModel::class.java

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentTeamLeagueBinding = FragmentTeamLeagueBinding.inflate(inflater, container, false)

    private fun setUpRecycler() {
        binding.rcvTeam.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = TeamLeagueAdapter() {

                when (requireActivity()) {
                    is TeamActivity -> navController.navigate(R.id.action_teamLeagueFragment_to_teamContestFragment)
                    is PlayerPickupActivity -> navController.navigate(R.id.action_teamLeagueFragment_to_leagueOptionFragment)
                }

            }.also {
                it.hideDateAndTime(requireActivity() is PlayerPickupActivity)
            }
        }
    }

    private fun getToolbarTitle() = when (requireActivity()) {
        is TeamActivity -> getString(R.string.team_leagues)
        is PlayerPickupActivity -> getString(R.string.leagues)
        else -> ""
    }
}