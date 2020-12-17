package com.ads.juused.ui.customized.fragments.cricket

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.ads.juused.R
import com.ads.juused.databinding.FragmentCreateCricketStep1Binding
import com.ads.juused.ui.customized.adapters.ContestSizeAdapter
import com.ads.juused.ui.customized.adapters.SelectedContestTypeAdapter
import com.ads.juused.ui.dialogs.DialogCustomContestSize
import com.ads.juused.ui.jackpot.adapters.TeamsAdapter
import com.ads.juused.utility.*

class CreateCricketContestLeagueFragment : Fragment() {

    private lateinit var binding: FragmentCreateCricketStep1Binding
    private lateinit var contestSizeAdapter: ContestSizeAdapter

    private var leagueSelectedPos = 0
    private var contestSize = 0

    var isContestSelected = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCreateCricketStep1Binding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.etAmount.doAfterTextChanged {
            binding.clGameSelection.animationVisibility((it?.isEmpty() == false))
        }

        setGameSpinner()
        setContestTypeRecycler()

        binding.tvCustom.setOnClickListener {
            DialogCustomContestSize(
                context = requireContext(),
                selectedSize = contestSize,
                onCustomValueEnter = {

                    if (contestSizeAdapter.containsValue(it)) {
                        clearCustomSelection()
                        contestSizeAdapter.setSelection(it)
                    }
                    else {
                        contestSizeAdapter.clearSelection()
                        binding.tvCustom.text = "$it"
                        binding.tvCustom.setBackgroundResource(R.drawable.bg_red)
                    }

                    contestSize = it
                    showHideContinueButton(true)

                }).show()
        }
    }

    fun showSelectedContest() {
        setSelectedContestTypeRecycler()
        setContentSizeRecycler()

        binding.rcvContestType.gone()
        binding.tvMatches.visible()
        binding.rcvSelectedContestType.visible()
        binding.clContestSize.visible()

        showHideContinueButton(false)
    }

    fun goToFinal() {
        binding.clCreate.goneWithFade()
        binding.flFinal.visbleWithFade()
    }

    private fun setGameSpinner() {

        val array = arrayOf(
            "--Select League--",
            "Vivo IPL League",
            "Bangladesh Premiere League",
            "Vivo IPL League"
        )

        binding.spinnerGame.adapter =
            ArrayAdapter(requireContext(), R.layout.item_spinner_text, R.id.text, array)

        binding.spinnerGame.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {

            override fun onNothingSelected(p0: AdapterView<*>?) {

            }

            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                leagueSelectedPos = p2
                binding.groupContestType.animationVisibility(leagueSelectedPos > 0)

                if (p2 > 0) view?.hideKeyboard()
            }
        }
    }

    private fun setContestTypeRecycler() {
        binding.rcvContestType.apply {
            layoutManager = GridLayoutManager(context, 3)
            adapter = TeamsAdapter() {
                isContestSelected = it

                showHideContinueButton(show = isContestSelected)
            }
        }
    }

    private fun setSelectedContestTypeRecycler() {
        binding.rcvSelectedContestType.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = SelectedContestTypeAdapter()
        }
    }

    private fun setContentSizeRecycler() {
        binding.rcvContestSize.apply {
            layoutManager = LinearLayoutManager(context).also {
                it.orientation = LinearLayoutManager.HORIZONTAL
            }
            adapter = ContestSizeAdapter() {
                contestSize = it

                showHideContinueButton(true)
                clearCustomSelection()

            }.also {
                contestSizeAdapter = it
            }
        }
    }

    private fun View.animationVisibility(show: Boolean) {
        if (show) {
            if (!isVisible())
                visbleWithFade()
        } else {
            if (isVisible()) return
            goneWithFade()
        }
    }

    private fun showHideContinueButton(show: Boolean) {
        parentFragment?.parentFragment?.let { fragment ->
            if (fragment is CreateCricketContestFragment) {
                fragment.enableButton(show)
            }
        }
    }

    private fun clearCustomSelection() {
        binding.tvCustom.text = "Custom"
        binding.tvCustom.setBackgroundResource(R.drawable.bg_outline_white)
    }

    val isCompleted: Boolean
        get() = leagueSelectedPos > 0 && isContestSelected && contestSize > 0

    val canGoToNext: Boolean
        get() = binding.flFinal.isVisible()
}