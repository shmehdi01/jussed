package com.ads.juused.ui.customized.fragments.cricket

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.ads.juused.databinding.FragmentCreateCricketStep2Binding

class CreateCricketContestScoringFragment : Fragment() {

    private lateinit var binding: FragmentCreateCricketStep2Binding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCreateCricketStep2Binding.inflate(inflater, container, false)
        return binding.root
    }
}