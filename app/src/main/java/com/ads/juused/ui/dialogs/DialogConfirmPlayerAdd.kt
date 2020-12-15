package com.ads.juused.ui.dialogs

import android.app.Dialog
import android.content.Context
import com.ads.juused.databinding.DialogConfirmAddPlayerBinding
import com.ads.juused.utility.PPHRankProgress
import com.ads.juused.utility.justifyWidth

class DialogConfirmPlayerAdd(context:Context): Dialog(context) {

    init {
        val binding = DialogConfirmAddPlayerBinding.inflate(layoutInflater)
        setContentView(binding.root)
        justifyWidth(dimmedAmount = 0.9f)

        PPHRankProgress(binding = binding.progressPph,progress = 100.0)
        PPHRankProgress(binding = binding.progressOppPph,progress = 100.0)

        binding.btnYes.setOnClickListener {
            dismiss()
        }

        binding.btnNo.setOnClickListener { dismiss() }
    }
}