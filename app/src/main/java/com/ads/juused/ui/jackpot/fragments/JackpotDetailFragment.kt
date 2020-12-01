package com.ads.juused.ui.jackpot.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.ads.juused.R
import com.ads.juused.base.BaseFragment
import com.ads.juused.databinding.FragmentJackpotDetailBinding
import com.ads.juused.ui.jackpot.adapters.TeamsAdapter
import solo.android.ui.base.BaseViewModel

class JackpotDetailFragment : BaseFragment<BaseViewModel, FragmentJackpotDetailBinding>() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setToolbar(title = getString(R.string.details), tailIcon = R.drawable.ic_purse)
        setUpRecycler()
    }

    override fun getViewModel(): Class<BaseViewModel> = BaseViewModel::class.java

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentJackpotDetailBinding = FragmentJackpotDetailBinding.inflate(inflater,container,false)

    private fun setUpRecycler() {
        binding.rcvTeam.apply {
            layoutManager = GridLayoutManager(context,3)
            adapter = TeamsAdapter()
        }
    }
}