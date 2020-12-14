package com.ads.juused.ui.auth.login

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.TranslateAnimation
import android.widget.TextView
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.transition.TransitionInflater
import com.ads.juused.R
import com.ads.juused.base.BaseFragment
import com.ads.juused.databinding.FragmentLoginBinding
import com.ads.juused.ui.home.HomeActivity
import com.ads.juused.utility.*
import com.ads.juused.base.BaseViewModel

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
        startAnim()
    }

    override fun getViewModel(): Class<BaseViewModel> = BaseViewModel::class.java

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentLoginBinding = FragmentLoginBinding.inflate(inflater,container,false)

    override fun enableBackPress(): Boolean = true

    override fun validate(showToast: Boolean, call: () -> Unit): Boolean {
        val message: String
        var validated = true

        binding.tlUsername.error = null
        binding.tlPassword.error = null

        when {
            binding.etUsername.trimText().isEmpty() -> {
                message = getString(R.string.email_is_empty)
                validated = false
                binding.tlUsername.error = message
                binding.etUsername.requestFocus()
            }

            binding.etPassword.trimText().isEmpty() -> {
                message = getString(R.string.password_is_empty)
                validated = false
                binding.tlPassword.error = message
                binding.etPassword.requestFocus()
            }
        }

        if(validated) {
            call.invoke()
        }

        return validated
    }

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

        binding.btnLogin.setOnClickListener {
            validate {
                //TODO LOGIN API
            }

            startNewActivity(HomeActivity::class.java)
        }
    }

    private fun startAnim() {
        Handler(Looper.getMainLooper()).postDelayed({
            binding.ivLoginImage.visibility = View.VISIBLE
            val animate = TranslateAnimation(
                0f,  // fromXDelta
                (binding.ivLoginImage.width.toFloat())/100*15,  // toXDelta
                0f,  // fromYDelta
                0f
            ) // toYDelta

            animate.duration = 300
            animate.fillAfter = true
            binding.ivLoginImage.startAnimation(animate)

        }, 10)
    }


}