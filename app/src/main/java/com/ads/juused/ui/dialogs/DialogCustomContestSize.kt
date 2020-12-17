package com.ads.juused.ui.dialogs

import android.app.Dialog
import android.content.Context
import android.view.Gravity
import android.view.KeyEvent
import android.view.ViewGroup
import android.view.WindowManager
import android.view.inputmethod.EditorInfo
import androidx.core.widget.doAfterTextChanged
import androidx.recyclerview.widget.LinearLayoutManager
import com.ads.juused.databinding.DialogCustomContestSizeBinding
import com.ads.juused.ui.customized.adapters.ContestSizeAdapter
import com.ads.juused.utility.hideKeyboard
import com.ads.juused.utility.toSafeInt

class DialogCustomContestSize(context: Context,private val selectedSize: Int, private val onCustomValueEnter: (Int) -> Unit) : Dialog(context) {

    init {
        val binding = DialogCustomContestSizeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        window?.setGravity(Gravity.BOTTOM)
        window?.setDimAmount(0.9f)
        window?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE );


        val contestSizeAdapter = ContestSizeAdapter {
            binding.etContentSize.setText("$it")
        }

        if (selectedSize > 0) {

            if (contestSizeAdapter.containsValue(selectedSize)) {
                contestSizeAdapter.setSelection(selectedSize)
            }

            binding.etContentSize.setText("$selectedSize")
        }


        binding.rcvContestSize.apply {
            layoutManager = LinearLayoutManager(context).also {
                it.orientation = LinearLayoutManager.HORIZONTAL
            }
            adapter = contestSizeAdapter
        }

        binding.etContentSize.requestFocus()
        binding.etContentSize.setOnEditorActionListener { _, actionId, event ->
            if ((event != null && (event.keyCode == KeyEvent.KEYCODE_ENTER)) || (actionId == EditorInfo.IME_ACTION_DONE)) {
                onCustomValueEnter.invoke(binding.etContentSize.text.toString().toInt())
                binding.etContentSize.hideKeyboard()
                dismiss()
            }
            return@setOnEditorActionListener false
        }

        binding.etContentSize.doAfterTextChanged {
           it?.let {
               val value = it.toString().toSafeInt()
               if (!contestSizeAdapter.containsValue(value)) {
                   contestSizeAdapter.clearSelection()
               }
               else {
                   contestSizeAdapter.setSelection(value)
               }
           }
        }
    }
}