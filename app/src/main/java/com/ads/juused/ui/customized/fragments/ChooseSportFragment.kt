package com.ads.juused.ui.customized.fragments

import android.animation.ValueAnimator
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.ads.juused.R
import com.ads.juused.base.BaseFragment
import com.ads.juused.base.BaseViewModel
import com.ads.juused.databinding.FragmentChooseSportBinding
import com.ads.juused.databinding.FragmentTeamContestsBinding
import com.ads.juused.ui.customized.CustomizedActivity
import com.ads.juused.ui.jackpot.JackpotActivity
import com.ads.juused.ui.player.PlayerPickupActivity
import com.ads.juused.ui.team.TeamActivity
import com.ads.juused.ui.team.adapter.TeamContestAdapter
import com.ads.juused.utility.setBgColorAnim
import com.ads.juused.utility.tint
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import jp.wasabeef.glide.transformations.BlurTransformation


class ChooseSportFragment : BaseFragment<BaseViewModel, FragmentChooseSportBinding>(),
    View.OnClickListener {

    private lateinit var navController: NavController

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navController = Navigation.findNavController(view)

        setToolbar(title = "Create Contest")
        makeBlueImages()
        bindClicks()
    }

    override fun onClick(p0: View?) {
        when(p0) {
            binding.llCricket -> {
                navController.navigate(R.id.action_chooseSportFragment_to_createCricketContestFragment)
            }
            binding.llFootball -> {

            }
            binding.llBasketBall -> {

            }
            binding.llNfl -> {

            }
            binding.llBaseball -> {

            }
        }
    }

    override fun getViewModel(): Class<BaseViewModel> = BaseViewModel::class.java

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentChooseSportBinding = FragmentChooseSportBinding.inflate(inflater,container,false)


    private fun makeBlueImages() {
        Glide.with(this).load(R.raw.entry_amount_create)
            .apply(RequestOptions.bitmapTransform(BlurTransformation(25, 3)))
            .into(binding.ivEnterAmount)

        Glide.with(this).load(R.raw.content_size)
            .apply(RequestOptions.bitmapTransform(BlurTransformation(25, 3)))
            .into(binding.ivContentSize)

        Glide.with(this).load(R.raw.choose_league)
            .apply(RequestOptions.bitmapTransform(BlurTransformation(25, 3)))
            .into(binding.ivLeague)
    }

    private fun bindClicks() {
        binding.llCricket.setOnClickListener(this)
        binding.llFootball.setOnClickListener(this)
        binding.llBasketBall.setOnClickListener(this)
        binding.llNfl.setOnClickListener(this)
        binding.llBaseball.setOnClickListener(this)
    }

}