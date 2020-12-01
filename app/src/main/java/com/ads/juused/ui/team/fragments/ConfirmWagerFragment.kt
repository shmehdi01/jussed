package com.ads.juused.ui.team.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.ads.juused.R
import com.ads.juused.base.BaseFragment
import com.ads.juused.databinding.FragmentConfrimWagerBinding
import com.ads.juused.ui.team.adapter.PlayerAvatarAdapter
import com.ads.juused.ui.team.adapter.WagerMatchableAdapter
import solo.android.ui.base.BaseViewModel


class ConfirmWagerFragment : BaseFragment<BaseViewModel, FragmentConfrimWagerBinding>() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setToolbar(title = getString(R.string.wager), tailIcon = R.drawable.ic_help)
        setUpRecycler()
    }

    override fun getViewModel(): Class<BaseViewModel> = BaseViewModel::class.java

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentConfrimWagerBinding = FragmentConfrimWagerBinding.inflate(inflater,container,false)

    private fun setUpRecycler() {
        binding.rcvPlayerAvatar.apply {
            layoutManager = GridLayoutManager(context, 4)
            adapter = PlayerAvatarAdapter()
        }
    }
}