package com.ads.juused.ui.team.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.animation.doOnEnd
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.ads.juused.R
import com.ads.juused.base.BaseFragment
import com.ads.juused.databinding.FragmentTeamSelectionBinding
import com.ads.juused.ui.team.adapter.TeamSelectionAdapter
import com.ads.juused.utility.setBgColorAnim
import com.ads.juused.base.BaseViewModel


class TeamSelectionFragment : BaseFragment<BaseViewModel, FragmentTeamSelectionBinding>() {

    private lateinit var navController: NavController

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navController = Navigation.findNavController(view)

        setToolbar(title = getString(R.string.team_selection), tailIcon = R.drawable.ic_help)
        setUpRecycler()
        bindClicks()
    }

    override fun getViewModel(): Class<BaseViewModel> = BaseViewModel::class.java

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentTeamSelectionBinding = FragmentTeamSelectionBinding.inflate(inflater,container,false)

    private fun setUpRecycler() {
        binding.rcvPlayers.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = TeamSelectionAdapter()
        }
    }

    private fun bindClicks() {

        binding.tvBowlersTeam.setOnClickListener {
            val animator = binding.tvBowlersTeam.setBgColorAnim(
                colorFrom = ContextCompat.getColor(requireContext(),R.color.colorGreyLightButton),
                colorTo = ContextCompat.getColor(requireContext(),R.color.colorRedAccent),
                duration = 500
            )
            animator.doOnEnd {
                binding.tvBowlersTeam.setTextColor(ContextCompat.getColor(requireContext(),R.color.colorWhite))
            }
        }


        binding.tvBatsmanTeam.setOnClickListener {
            val animator = binding.tvBatsmanTeam.setBgColorAnim(
                colorFrom = ContextCompat.getColor(requireContext(),R.color.colorGreyLightButton),
                colorTo = ContextCompat.getColor(requireContext(),R.color.colorCyanAccent),
                duration = 500
            )
            animator.doOnEnd {
                binding.tvBatsmanTeam.setTextColor(ContextCompat.getColor(requireContext(),R.color.colorWhite))
            }
        }

        binding.btnParticipate.setOnClickListener {
            navController.navigate(R.id.action_teamSelectionFragment_to_wagerFragment)
        }
    }

}