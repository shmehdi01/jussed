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
import com.ads.juused.databinding.FragmentWagerBinding
import com.ads.juused.ui.team.adapter.WagerMatchableAdapter
import com.ads.juused.utility.*
import com.ads.juused.base.BaseViewModel
import com.ads.juused.ui.player.PlayerPickupActivity
import com.ads.juused.ui.team.TeamActivity

/**
 *  WagerFragment:
 *  -------------------------------------------------
 *  This fragment is open from:
 *  ---------------------------
 *  1.TeamActivity and
 *  2.PlayerPickUpActivity
 */
class WagerFragment: BaseFragment<BaseViewModel, FragmentWagerBinding>() {

    private lateinit var navController: NavController

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navController = Navigation.findNavController(view)

        setToolbar(title = getString(R.string.wager), tailIcon = R.drawable.ic_help)
        setUpRecycler()
        bindClicks()
        handleUiVisibility()
    }

    override fun getViewModel(): Class<BaseViewModel> = BaseViewModel::class.java

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentWagerBinding = FragmentWagerBinding.inflate(inflater,container,false)

    private fun setUpRecycler() {
        binding.rcvMatchable.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = WagerMatchableAdapter(WagerMatchableAdapter.MATCHABLE)
        }

        binding.rcvNotMatchable.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = WagerMatchableAdapter(WagerMatchableAdapter.NOT_MATCHABLE)
        }
    }

    private fun bindClicks() {
        binding.btnPutWager.setOnClickListener {
            when(requireActivity()) {
                is TeamActivity -> navController.navigate(R.id.action_wagerFragment_to_confrimWagerFragment)
                is PlayerPickupActivity -> navController.navigate(R.id.action_wagerFragment_to_confirmPlayerPickUpFragment)
            }
        }
    }

    private fun handleUiVisibility() {
        when(requireActivity()) {
            is TeamActivity -> {}
            is PlayerPickupActivity -> {
                binding.tvTeamName.gone()
                binding.tvPickWagerSize.gone()
            }
        }
    }

    @Deprecated("removed by client")
    private fun notAvailableWagerAnimator() {
        binding.etAmount.setOnFocusChangeListener { _, isFocus ->
            if(isFocus) {
                binding.cardNotMatchable.visbleWithFade(duration = 700)
                binding.cardMatchable.changeWidth(200f.toDp(requireContext()), duration = 300)
            }
            else {
                binding.cardNotMatchable.goneWithFade()
            }
        }
    }

}