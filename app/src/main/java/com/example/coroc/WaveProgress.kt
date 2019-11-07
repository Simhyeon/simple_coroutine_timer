package com.example.coroc

import android.graphics.ColorMatrixColorFilter
import android.graphics.Matrix
import android.graphics.RectF
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RelativeLayout.LayoutParams
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.wave_progress.*
import kotlinx.android.synthetic.main.wave_progress.view.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class WaveProgress : AppCompatActivity() {

    private var onAnimation = false
    private var onStopProgress = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.requestWindowFeature(Window.FEATURE_NO_TITLE)
        this.window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
//            window.decorView.systemUiVisibility = (
//            View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or
//                    View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
//            )
        setContentView(R.layout.wave_progress)

        // Currently just pure white
        val bgFilter = ColorMatrixColorFilter(
            floatArrayOf(
                0f,     0f,     0f,     0f, 0f,
                0f,     0f,     0f,     0f, 0f,
                0f,     0f,     0f,     0f, 0f,
                0f,     0f,     0f,     0f, 0f
            ))

        val waveTimerView = WaveTimerView(this, waveRoot, 33, 60)
        waveTimerView.setWaveDrawable(R.color.colorAccent, bgFilter)
        waveTimerView.requestLayout()

        waveTimerView.setOnClickListener {
            Toast.makeText(this, "Toggled", Toast.LENGTH_SHORT).show()
            waveTimerView.toggleTimer()
        }

//        val waveDrawable = CorocWaveDrawable(this, R.color.colorAccent, bgFilter, 60)
//        val waveForeground = ImageView(this)
//        val layoutParams = LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT)
//        waveForeground.layoutParams = layoutParams
//
////        waveForeground.scaleType = ImageView.ScaleType.FIT_CENTER
////        waveForeground.adjustViewBounds = false
//
//        waveForeground.setImageDrawable(waveDrawable)
//        waveRoot.addView(waveForeground)
//
////        waveDrawable.setOptions(50, 50000,21)
//        waveDrawable.level = 0 // Litterly nothing to show
//
//        var heightLevel = 0f // Initial value is 0
//        val delayMilliSeconds = 33 // Delay for while loop
//        val totalSeconds = 40 // Initial value is 60
//        val levelVariation = CorocUtil.getLevelVariation(totalSeconds, delayMilliSeconds)
//
//        waveForeground.setOnClickListener {
//            if(onAnimation && !onStopProgress) {
//                onAnimation = false
//                onStopProgress = true
//                return@setOnClickListener
//            } else if (onStopProgress) {
//                return@setOnClickListener
//            }
//            onAnimation = true
//            CoroutineScope(Dispatchers.Main).launch {
//                Toast.makeText(applicationContext, "Clicked", Toast.LENGTH_SHORT).show()
//                while ( (heightLevel in 0f..10000f) && onAnimation) {
//                    Log.d("WaveProgress", "heightLevel : $heightLevel ${waveDrawable.level}")
//                    delay(delayMilliSeconds.toLong())
//                    if (!onAnimation) {
//                        break
//                    } // Or you can put delay to last part of this while delay if you want to remove redundant
//                    // if break phrases however that would make time progression little bit awkward
//                    heightLevel += levelVariation
////                    imageView2.layoutParams.width = rWidth.toInt()
//                    waveDrawable.level = heightLevel.toInt()
//                }
//                onAnimation = false
//                onStopProgress = false
//                if (heightLevel >= 10000) {
//                    waveDrawable.level = 10000
//                }
//            }
//        }
    }
}