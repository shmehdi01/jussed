package com.ads.juused.ui.auth.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.viewpager.widget.ViewPager
import com.ads.juused.adapter.AuthSliderAdapter
import com.ads.juused.base.BaseFragment
import com.ads.juused.databinding.FragmentAuthBinding
import com.ads.juused.utility.*
import solo.android.ui.base.BaseViewModel


class AuthFragment : BaseFragment<BaseViewModel, FragmentAuthBinding>() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setSlider()

        binding.llFacebook.setOnClickListener {
            binding.llFacebook.expandView(true, binding.tvFbLogin)
            binding.llGoogle.expandView(false, binding.tvGoogleSignIn)
        }

        binding.llGoogle.setOnClickListener {
            binding.llFacebook.expandView(false, binding.tvFbLogin)
            binding.llGoogle.expandView(true, binding.tvGoogleSignIn)
        }

    }

    override fun getViewModel(): Class<BaseViewModel> = BaseViewModel::class.java

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentAuthBinding = FragmentAuthBinding.inflate(inflater,container,false)


    private fun setSlider() {
        val adapter = AuthSliderAdapter(requireContext(), childFragmentManager) //AuthPagerAdapter(requireContext())
        binding.viewPager.adapter = adapter
        binding.viewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {

            }

            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {

            }

            override fun onPageSelected(position: Int) {
                binding.ivLogo.show(position == 0)
                binding.tvDescription.text = adapter.descriptions[position]
            }

        })
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
}