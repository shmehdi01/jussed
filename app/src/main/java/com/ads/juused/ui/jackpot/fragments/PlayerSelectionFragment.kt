package com.ads.juused.ui.jackpot.fragments

import android.os.Bundle
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.animation.doOnEnd
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.ads.juused.R
import com.ads.juused.base.BaseFragment
import com.ads.juused.databinding.FragmentPlayerSelectionBinding
import com.ads.juused.ui.jackpot.adapters.ChoosePlayerAdapter
import com.ads.juused.base.BaseViewModel
import com.ads.juused.ui.dialogs.DialogConfirmPlayerAdd
import com.ads.juused.ui.jackpot.JackpotActivity
import com.ads.juused.ui.player.PlayerPickupActivity
import com.ads.juused.utility.*

/***
 *  PlayerSelectionFragment: To Select Players to start a game.
 *  Open from :
 *  1. JackpotActivity
 *  2. PlayerPickupActivity
 *
 *  Note: <b>Make sure handle your conditions according to Activities.</b>
 */
class PlayerSelectionFragment : BaseFragment<BaseViewModel,FragmentPlayerSelectionBinding>(),
    View.OnClickListener {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setToolbar(title = getToolbarTitle(), tailIcon = R.drawable.ic_help)
        view.findViewById<TextView>(R.id.tv_title).apply {
            setTextColor(ContextCompat.getColor(requireContext(),R.color.colorWhite))
            setTextSize(TypedValue.COMPLEX_UNIT_SP,16f)
        }

        binding.seekBar.isEnabled = false
        setUpRecycler()
        bindClick()
        handleUIVisibility()
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

            binding.llMyTeam -> {
                handleRosterSelectionColor(
                    icon = binding.ivMyTeam,
                    label = binding.tvMyTeam
                )
            }

            binding.llOpponent -> {
                handleRosterSelectionColor(
                    icon = binding.ivOpponent,
                    label = binding.tvOpponent
                )
            }

            binding.llResult -> {
                handleRosterSelectionColor(
                    icon = binding.ivResult,
                    label = binding.tvResult
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
            adapter = ChoosePlayerAdapter(onAdd = {
                when(requireActivity()) {
                    is JackpotActivity -> {

                    }

                    is PlayerPickupActivity -> {
                        DialogConfirmPlayerAdd(requireContext()).show()
                    }
                }
            }).also {
                it.showFavoriteIcon(requireActivity() is PlayerPickupActivity)
            }
        }
    }

    private fun bindClick() {

        //OPTION
        binding.llWk.setOnClickListener(this)
        binding.llBat.setOnClickListener(this)
        binding.llAr.setOnClickListener(this)
        binding.llBowl.setOnClickListener(this)
        binding.llFlex.setOnClickListener(this)
        binding.llUberFlex.setOnClickListener(this)

        //OPTION ROSTERS
        binding.llMyTeam.setOnClickListener(this)
        binding.llOpponent.setOnClickListener(this)
        binding.llResult.setOnClickListener(this)

        //AVAILABLE - FAVORITES - ROSTERS
        binding.tvAvailable.setOnClickListener {
            colorAnimationForAvailableFavoriteRoster(
                label = binding.tvAvailable,
                activeColor = R.color.colorRedAccent
            )

            binding.llOptionRosters.show(false)
            binding.llOption.show(true)
        }

        binding.tvFavorite.setOnClickListener {
            colorAnimationForAvailableFavoriteRoster(
                label = binding.tvFavorite,
                activeColor = R.color.colorBlack
            )

            binding.llOptionRosters.show(false)
            binding.llOption.show(true)
        }

        binding.tvRosters.setOnClickListener {
            colorAnimationForAvailableFavoriteRoster(
                label = binding.tvRosters,
                activeColor = R.color.colorCyanAccent
            )

            binding.llOptionRosters.show(true)
            binding.llOption.show(false)
        }
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

    private fun handleRosterSelectionColor(icon: ImageView, label:TextView) {

        val disableColor = ContextCompat.getColor(requireContext(), R.color.colorHomeOption)
        val enableColor = ContextCompat.getColor(requireContext(), R.color.colorRedAccent)

        binding.ivMyTeam.tint(disableColor)
        binding.tvMyTeam.setTextColor(disableColor)

        binding.ivOpponent.tint(disableColor)
        binding.tvOpponent.setTextColor(disableColor)

        binding.ivResult.tint(disableColor)
        binding.tvResult.setTextColor(disableColor)

        icon.tint(enableColor)
        label.setTextColor(enableColor)
    }

    private fun colorAnimationForAvailableFavoriteRoster(label: TextView, activeColor: Int) {

        binding.tvFavorite.setBackgroundColor(ContextCompat.getColor(requireContext(),R.color.colorGreyLightButton))
        binding.tvFavorite.setTextColor(ContextCompat.getColor(requireContext(),R.color.colorGreyText))

        binding.tvAvailable.setBackgroundColor(ContextCompat.getColor(requireContext(),R.color.colorGreyLightButton))
        binding.tvAvailable.setTextColor(ContextCompat.getColor(requireContext(),R.color.colorGreyText))

        binding.tvRosters.setBackgroundColor(ContextCompat.getColor(requireContext(),R.color.colorGreyLightButton))
        binding.tvRosters.setTextColor(ContextCompat.getColor(requireContext(),R.color.colorGreyText))

        val animator =label.setBgColorAnim(
            colorFrom = ContextCompat.getColor(requireContext(),R.color.colorGreyLightButton),
            colorTo = ContextCompat.getColor(requireContext(), activeColor),
            duration = 500
        )
        animator.doOnEnd {
            label.setTextColor(ContextCompat.getColor(requireContext(),R.color.colorWhite))
        }

    }

    private fun getToolbarTitle() = when (requireActivity()) {
        is JackpotActivity -> "7d 20h 35m 12s left"
        is PlayerPickupActivity -> getString(R.string.player_selection)
        else -> ""
    }

    private fun handleUIVisibility() {
        when(requireActivity()) {
            is JackpotActivity -> {
                binding.clHeaderJackpot.visible()
                binding.clHeaderPlayerPickup.gone()
            }
            is PlayerPickupActivity -> {
                binding.clHeaderJackpot.gone()
                binding.clHeaderPlayerPickup.visible()
            }
            else -> {

            }
        }
    }
}