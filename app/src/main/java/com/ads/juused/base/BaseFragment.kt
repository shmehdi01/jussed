package com.ads.juused.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.viewbinding.ViewBinding
import com.ads.juused.R
import com.ads.juused.utility.invisible
import com.ads.juused.utility.showSnack
import com.ads.juused.utility.visible

abstract class BaseFragment<VM:BaseViewModel,B:ViewBinding>: Fragment() {

    lateinit var binding: B
    lateinit var viewModel: VM

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = getViewBinding(inflater,container)
        //viewModel = ViewModelProvider(this)[getViewModel()]
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if(enableBackPress()) {
            view.findViewById<ImageView>(R.id.iv_back)?.let {
                it.setOnClickListener { requireActivity().onBackPressed() }
            }
        }

        observeLiveData()
    }

    abstract fun getViewModel() : Class<VM>

    abstract fun getViewBinding(inflater: LayoutInflater, container: ViewGroup?) : B

    //abstract fun getViewModelFactory(): ViewModelProviderFactory

    fun setToolbar(title: String, enableBack: Boolean = true, tailIcon: Int = -1, tailIconClick: (() -> Unit)? = null) {
        view?.findViewById<TextView>(R.id.tv_title)?.let { it.text = title }
        view?.findViewById<ImageView>(R.id.iv_back)?.let {
            if (enableBack) {
               it.setOnClickListener { requireActivity().onBackPressed() }
            }
            else it.invisible()
        }

        if (tailIcon != -1) {
           view?.findViewById<ImageView>(R.id.iv_tail)?.let {
               it.visible()
               it.setImageResource(tailIcon)
               it.setOnClickListener { tailIconClick?.invoke() }
           }
        }
    }

    open fun enableBackPress(): Boolean {
        return false
    }

    open fun validate(showToast: Boolean = false, call: () -> Unit): Boolean {
        return true.also { call() }
    }

    /**
     *  TO OBSERVE LIVE DATA OVERRIDE THIS METHOD IN YOUR FRAGMENT
     *  By default it will observe error from BaseViewModel.
     *  Make sure your view model inherit BaseViewModel.
     */
    open fun observeLiveData() {
        if (this::viewModel.isInitialized) {
            viewModel.error.observe(viewLifecycleOwner, Observer {
                view?.showSnack(it.errorMessage)
            })
        }
    }
}