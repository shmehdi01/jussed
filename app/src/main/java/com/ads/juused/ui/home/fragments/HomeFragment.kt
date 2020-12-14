package com.ads.juused.ui.home.fragments

import android.animation.ValueAnimator
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.ads.juused.R
import com.ads.juused.base.BaseFragment
import com.ads.juused.databinding.FragmentHomeBinding
import com.ads.juused.ui.jackpot.JackpotActivity
import com.ads.juused.ui.team.TeamActivity
import com.ads.juused.utility.setBgColorAnim
import com.ads.juused.utility.tint
import com.ads.juused.base.BaseViewModel

class HomeFragment : BaseFragment<BaseViewModel,FragmentHomeBinding>(), View.OnClickListener {

    private var optionIndicatorAnimator: ValueAnimator? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bindClicks()
    }

    override fun onClick(p0: View?) {
        when(p0) {
            binding.llCricket -> {
                handleOptionSelectionColor(
                    icon = binding.ivCricket,
                    label = binding.tvCricket,
                    indicator = binding.viewCricketIndicator
                )
            }
            binding.llFootball -> {
                handleOptionSelectionColor(
                    icon = binding.ivFootball,
                    label = binding.tvFootball,
                    indicator = binding.viewFootballIndicator
                )
            }
            binding.llBasketBall -> {
                handleOptionSelectionColor(
                    icon = binding.ivBasketball,
                    label = binding.tvBasketball,
                    indicator = binding.viewBasketballIndicator
                )
            }
            binding.llNfl -> {
                handleOptionSelectionColor(
                    icon = binding.ivNfl,
                    label = binding.tvNfl,
                    indicator = binding.viewNflIndicator
                )
            }
            binding.llBaseball -> {
                handleOptionSelectionColor(
                    icon = binding.ivBaseball,
                    label = binding.tvBaseball,
                    indicator = binding.viewBaseballIndicator
                )
            }
        }
    }

    override fun getViewModel(): Class<BaseViewModel> = BaseViewModel::class.java

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentHomeBinding = FragmentHomeBinding.inflate(inflater,container,false)

    private fun bindClicks() {
        binding.llCricket.setOnClickListener(this)
        binding.llFootball.setOnClickListener(this)
        binding.llBasketBall.setOnClickListener(this)
        binding.llNfl.setOnClickListener(this)
        binding.llBaseball.setOnClickListener(this)

        binding.ivSearch.setOnClickListener {

        }

        binding.ivNotification.setOnClickListener {

        }

        binding.ivMenu.setOnClickListener {

        }

        binding.flTeam.setOnClickListener {
            startActivity(Intent(requireActivity(), TeamActivity::class.java))
        }

        binding.flJackpot.setOnClickListener {
            startActivity(Intent(requireActivity(), JackpotActivity::class.java))
        }
    }

    private fun handleOptionSelectionColor(icon:ImageView, label:TextView, indicator:View) {

        val disableColor = ContextCompat.getColor(requireContext(), R.color.colorHomeOption)
        val disableIndicatorColor = ContextCompat.getColor(requireContext(), R.color.colorBlackCard)
        val enableColor = ContextCompat.getColor(requireContext(), R.color.colorRedAccent)

        binding.ivCricket.tint(disableColor)
        binding.tvCricket.setTextColor(disableColor)
        binding.viewCricketIndicator.setBackgroundColor(disableIndicatorColor)

        binding.ivFootball.tint(disableColor)
        binding.tvFootball.setTextColor(disableColor)
        binding.viewFootballIndicator.setBackgroundColor(disableIndicatorColor)

        binding.ivBasketball.tint(disableColor)
        binding.tvBasketball.setTextColor(disableColor)
        binding.viewBasketballIndicator.setBackgroundColor(disableIndicatorColor)

        binding.ivNfl.tint(disableColor)
        binding.tvNfl.setTextColor(disableColor)
        binding.viewNflIndicator.setBackgroundColor(disableIndicatorColor)

        binding.ivBaseball.tint(disableColor)
        binding.tvBaseball.setTextColor(disableColor)
        binding.viewBaseballIndicator.setBackgroundColor(disableIndicatorColor)


        icon.tint(enableColor)
        label.setTextColor(enableColor)

        optionIndicatorAnimator?.cancel()
        optionIndicatorAnimator = indicator.setBgColorAnim(disableIndicatorColor,enableColor)
    }
}