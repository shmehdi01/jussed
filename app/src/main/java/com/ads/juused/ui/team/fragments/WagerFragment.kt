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
import com.ads.juused.utility.show
import solo.android.ui.base.BaseViewModel


class WagerFragment: BaseFragment<BaseViewModel, FragmentWagerBinding>() {

    private lateinit var navController: NavController

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navController = Navigation.findNavController(view)

        setToolbar(title = getString(R.string.wager), tailIcon = R.drawable.ic_help)
        setUpRecycler()
        bindClicks()

        binding.etAmount.setOnFocusChangeListener { _, isFocus ->
            binding.cardNotMatchable.show(isFocus)
        }
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
            navController.navigate(R.id.action_wagerFragment_to_confrimWagerFragment)
        }
    }

}