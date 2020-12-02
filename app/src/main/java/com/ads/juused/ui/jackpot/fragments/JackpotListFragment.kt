package com.ads.juused.ui.jackpot.fragments

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
import com.ads.juused.databinding.FragmentJackpotDetailBinding
import com.ads.juused.databinding.FragmentJackpotListBinding
import com.ads.juused.ui.jackpot.adapters.JackpotAdapter
import solo.android.ui.base.BaseViewModel

class JackpotListFragment : BaseFragment<BaseViewModel, FragmentJackpotListBinding>() {

    private lateinit var navController: NavController

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setToolbar(title = getString(R.string.jackpot))
        navController = Navigation.findNavController(view)
        setUpRecycler()
    }

    override fun getViewModel(): Class<BaseViewModel> = BaseViewModel::class.java

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentJackpotListBinding = FragmentJackpotListBinding.inflate(inflater,container,false)

    private fun setUpRecycler() {
        binding.rcvJackpot.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = JackpotAdapter() {
                navController.navigate(R.id.action_jackpotListFragment_to_jackpotDetailFragment)
            }
        }
    }
}