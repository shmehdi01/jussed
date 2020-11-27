package com.ads.juused.ui.auth.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.transition.TransitionInflater
import com.ads.juused.R
import com.ads.juused.base.BaseFragment
import com.ads.juused.databinding.FragmentLoginBinding
import com.ads.juused.utility.changeWidth
import com.ads.juused.utility.goneWithFade
import com.ads.juused.utility.toDp
import com.ads.juused.utility.visbleWithFade
import solo.android.ui.base.BaseViewModel

class LoginFragment : BaseFragment<BaseViewModel,FragmentLoginBinding>() {

    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedElementEnterTransition = TransitionInflater.from(context).inflateTransition(android.R.transition.move)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navController = Navigation.findNavController(view)
        bindClicks()
    }

    override fun getViewModel(): Class<BaseViewModel> = BaseViewModel::class.java

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentLoginBinding = FragmentLoginBinding.inflate(inflater,container,false)

    private fun View.expandView(expand: Boolean, tv: TextView) {
        if(expand) {
            changeWidth(230f.toDp(requireContext())) {
                tv.visbleWithFade()
            }
        }
        else {
            changeWidth(140f.toInt()) {
                tv.goneWithFade()
            }
        }
    }

    override fun enableBackPress(): Boolean = true

    private fun bindClicks() {
        binding.llFacebook.setOnClickListener {
            binding.llFacebook.expandView(true, binding.tvFbLogin)
            binding.llGoogle.expandView(false, binding.tvGoogleSignIn)
        }

        binding.llGoogle.setOnClickListener {
            binding.llFacebook.expandView(false, binding.tvFbLogin)
            binding.llGoogle.expandView(true, binding.tvGoogleSignIn)
        }

        binding.tvCreateAccount.setOnClickListener {
            val extras = FragmentNavigatorExtras(
                binding.llGoogle to "llGoogle"
            )

            navController.navigate(R.id.action_loginFragment_to_languageFragment,null,null, extras)
        }
    }
}