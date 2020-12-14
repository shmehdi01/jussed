package com.ads.juused.ui.jackpot.fragments

import android.os.Bundle
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.ads.juused.R
import com.ads.juused.base.BaseFragment
import com.ads.juused.databinding.FragmentPlayerSelectionBinding
import com.ads.juused.ui.jackpot.adapters.ChoosePlayerAdapter
import com.ads.juused.utility.tint
import com.ads.juused.base.BaseViewModel


class PlayerSelectionFragment : BaseFragment<BaseViewModel,FragmentPlayerSelectionBinding>(),
    View.OnClickListener {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setToolbar(title = "7d 20h 35m 12s left", tailIcon = R.drawable.ic_help)
        view.findViewById<TextView>(R.id.tv_title).apply {
            setTextColor(ContextCompat.getColor(requireContext(),R.color.colorWhite))
            setTextSize(TypedValue.COMPLEX_UNIT_SP,16f)
        }

        binding.seekBar.isEnabled = false
        setUpRecycler()
        bindClick()
    }


    override fun onClick(p0: View?) {
        when(p0) {
            binding.llWk -> {
                handleTabSelectionColor(
                    icon = binding.ivWk,
                    label = binding.tvWk
                )
            }
            binding.llBat -> {
                handleTabSelectionColor(
                    icon = binding.ivBat,
                    label = binding.tvBat
                )
            }
            binding.llAr -> {
                handleTabSelectionColor(
                    icon = binding.ivAr,
                    label = binding.tvAr
                )
            }
            binding.llBowl -> {
                handleTabSelectionColor(
                    icon = binding.ivBowl,
                    label = binding.tvBowl
                )
            }
            binding.llFlex -> {
                handleTabSelectionColor(
                    icon = binding.ivFlex,
                    label = binding.tvFlex
                )
            }
            binding.llUberFlex -> {
                handleTabSelectionColor(
                    icon = binding.ivUberFlex,
                    label = binding.tvUberFlex
                )
            }
        }
    }

    override fun getViewModel(): Class<BaseViewModel> = BaseViewModel::class.java

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentPlayerSelectionBinding = FragmentPlayerSelectionBinding.inflate(inflater,container,false)

    private fun setUpRecycler() {
        binding.rcvPlayers.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = ChoosePlayerAdapter()
        }
    }

    private fun bindClick() {
        binding.llWk.setOnClickListener(this)
        binding.llBat.setOnClickListener(this)
        binding.llAr.setOnClickListener(this)
        binding.llBowl.setOnClickListener(this)
        binding.llFlex.setOnClickListener(this)
        binding.llUberFlex.setOnClickListener(this)
    }

    private fun handleTabSelectionColor(icon: ImageView, label:TextView) {

        val disableColor = ContextCompat.getColor(requireContext(), R.color.colorHomeOption)
        val enableColor = ContextCompat.getColor(requireContext(), R.color.colorRedAccent)

        binding.ivWk.tint(disableColor)
        binding.tvWk.setTextColor(disableColor)

        binding.ivBat.tint(disableColor)
        binding.tvBat.setTextColor(disableColor)

        binding.ivAr.tint(disableColor)
        binding.tvAr.setTextColor(disableColor)

        binding.ivBowl.tint(disableColor)
        binding.tvBowl.setTextColor(disableColor)

        binding.ivFlex.tint(disableColor)
        binding.tvFlex.setTextColor(disableColor)

        binding.ivUberFlex.tint(disableColor)
        binding.tvUberFlex.setTextColor(disableColor)

        icon.tint(enableColor)
        label.setTextColor(enableColor)
    }

}