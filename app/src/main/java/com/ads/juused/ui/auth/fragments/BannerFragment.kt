package com.ads.juused.ui.auth.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.ads.juused.databinding.AuthViewSliderBinding
import com.ads.juused.utility.gone

class BannerFragment private constructor(): Fragment() {

    private lateinit var binding: AuthViewSliderBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
         binding = AuthViewSliderBinding.inflate(LayoutInflater.from(context),container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

         arguments?.let {
             val title = it.getString("title")
             val title2 = it.getString("title2")
             val image = it.getInt("image")

             if(image != -1) {
                 binding.image.setImageResource(image)
             }
             else {
                 binding.image.gone()
             }
             binding.text1.text = title
             binding.text2.text = title2
        }

    }

    companion object {

        fun newInstance(image: Int, title: String, title2: String) = BannerFragment()
            .apply {
                arguments = Bundle().also {
                    it.putString("title", title)
                    it.putString("title2", title2)
                    it.putInt("image", image)
                }
            }
    }
}