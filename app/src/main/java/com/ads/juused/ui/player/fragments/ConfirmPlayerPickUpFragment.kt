package com.ads.juused.ui.player.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.ads.juused.R
import com.ads.juused.base.BaseFragment
import com.ads.juused.base.BaseViewModel
import com.ads.juused.databinding.FragmentConfirmPlayerPickupBinding
import com.ads.juused.ui.dialogs.DialogBetPlaced
import com.ads.juused.ui.home.HomeActivity

class ConfirmPlayerPickUpFragment:  BaseFragment<BaseViewModel, FragmentConfirmPlayerPickupBinding>() {

    private lateinit var navController: NavController

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setToolbar("Confirmation")

        navController = Navigation.findNavController(view)

        binding.btnContinueGame.setOnClickListener {

            DialogBetPlaced(requireContext()).apply {
                show()
                setOnDismissListener {
                    navController.navigate(R.id.action_confirmPlayerPickUpFragment_to_playerSelectionFragment2)
                }
            }
        }
    }

    override fun getViewModel(): Class<BaseViewModel> = BaseViewModel::class.java

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentConfirmPlayerPickupBinding = FragmentConfirmPlayerPickupBinding.inflate(inflater, container, false)

}