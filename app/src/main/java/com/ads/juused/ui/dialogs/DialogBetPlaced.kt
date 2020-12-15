package com.ads.juused.ui.dialogs

import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.graphics.drawable.BitmapDrawable
import android.view.Gravity
import com.ads.juused.R
import com.ads.juused.utility.justifyWidth
import com.ads.juused.utility.takeScreenshot
import com.ads.juused.utility.tryBlur
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class DialogBetPlaced(context: Context): Dialog(context) {

    init {
        setContentView(R.layout.dialog_bet_placed)
        justifyWidth(dimmedAmount = 0.9f)

        GlobalScope.launch {
            delay(2000)
            dismiss()
        }
       // tryToBlurBackground(context)
    }

    private fun tryToBlurBackground(context: Context) {
        if(context is Activity) {
            GlobalScope.launch(Dispatchers.Main) {
                val bmp = context.takeScreenshot()
                val blurBmp = tryBlur(bmp,10)
                val draw = BitmapDrawable(context.resources, blurBmp)
                window?.setBackgroundDrawable(draw)
                window?.setGravity(Gravity.CENTER)
                justifyWidth()
            }
        }
    }
}