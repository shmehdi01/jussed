package com.ads.juused.ui.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.view.animation.Animation
import android.view.animation.TranslateAnimation
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityOptionsCompat
import com.ads.juused.databinding.ActivitySplashBinding
import com.ads.juused.ui.auth.AuthActivity


class SplashScreen : AppCompatActivity() {

    private lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)
        startAnim()
    }

    private fun startAnim() {
        Handler(Looper.getMainLooper()).postDelayed({
            binding.ivBackground.visibility = View.VISIBLE
            val animate = TranslateAnimation(
                0f,  // fromXDelta
                0f,  // toXDelta
                0f,  // fromYDelta
                (binding.ivBackground.height.toFloat()/2)*-1
            ) // toYDelta

            animate.duration = 3000
            animate.fillAfter = true
            binding.ivBackground.startAnimation(animate)

            animate.setAnimationListener(object : Animation.AnimationListener {
                override fun onAnimationRepeat(p0: Animation?) {

                }

                override fun onAnimationEnd(p0: Animation?) {
                    goToNextPage()
                }

                override fun onAnimationStart(p0: Animation?) {
                }

            })
        }, 1000)
    }

    private fun goToNextPage() {
        val activityOptionsCompat =
            ActivityOptionsCompat.makeSceneTransitionAnimation(this, binding.ivLogo, "logo")
        val intent = Intent(this, AuthActivity::class.java)

        startActivity(intent, activityOptionsCompat.toBundle())
        //overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
        finish()
    }

}