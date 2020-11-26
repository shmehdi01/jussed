package com.ads.juused.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.transition.ChangeBounds
import androidx.viewbinding.ViewBinding

abstract class BaseFragment<VM:ViewModel,B:ViewBinding>: Fragment() {

    lateinit var binding: B
    lateinit var viewModel: VM

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


//        sharedElementEnterTransition = ChangeBounds().apply {
//            duration = 750
//        }
//        sharedElementReturnTransition= ChangeBounds().apply {
//            duration = 750
//        }

        binding = getViewBinding(inflater,container)
        //viewModel = ViewModelProvider(this)[getViewModel()]
        return binding.root
    }

    abstract fun getViewModel() : Class<VM>

    abstract fun getViewBinding(inflater: LayoutInflater, container: ViewGroup?) : B

    //abstract fun getViewModelFactory(): ViewModelProviderFactory
}