package com.ads.juused.ui.player.fragments

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
import com.ads.juused.databinding.FragmentPlayerLeagueOptionBinding
import com.ads.juused.ui.player.adapters.PlayerLeagueOptionAdapter


class PlayerLeagueOptionFragment : BaseFragment<BaseViewModel, FragmentPlayerLeagueOptionBinding>() {

    private lateinit var navController: NavController

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setToolbar(title = "Vivo IPL 2020")

        navController = Navigation.findNavController(view)
        setRecyclerData()
    }

    override fun getViewModel(): Class<BaseViewModel> = BaseViewModel::class.java

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentPlayerLeagueOptionBinding = FragmentPlayerLeagueOptionBinding.inflate(inflater, container, false)

    private fun setRecyclerData() {
          binding.rcvOption.apply {
              layoutManager = LinearLayoutManager(context)
              adapter = PlayerLeagueOptionAdapter().also {
                  it.setOnItemClickListener {
                      navController.navigate(R.id.action_leagueOptionFragment_to_matchesFragment)
                  }
              }
          }
    }
}