package com.example.coroc

import android.content.Context
import android.graphics.ColorMatrixColorFilter
import android.util.Log
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import kotlinx.coroutines.*

class WaveTimerView(context: Context, rootViewGroup: ViewGroup, delayMilliSeconds: Int, durationS: Int) : ImageView(context) {

    var waveDrawable: CorocWaveDrawable? = null
    var heightLevel= 0f
    var delayMilliSeconds = 0
    var levelVariation = 0f
    private var onAnimation = false
    private var onStopProgress = false
    private var jobId: Job? = null

    init {
        this.layoutParams = ViewGroup.LayoutParams(-1, -1)
        this.bringToFront()
        this.levelVariation = CorocUtil.getLevelVariation(durationS, this.delayMilliSeconds)
        rootViewGroup.addView(this)
    }

    fun setWaveDrawable(colorRes: Int) : CorocWaveDrawable? {
        waveDrawable = CorocWaveDrawable(context, colorRes)
        super.setImageDrawable(waveDrawable)
        waveDrawable!!.level = 10000
        return waveDrawable
    }

    fun setWaveDrawable(colorRes: Int, bgColorFilter: ColorMatrixColorFilter) : CorocWaveDrawable? {
        waveDrawable = CorocWaveDrawable(context, colorRes, bgColorFilter)
        super.setImageDrawable(waveDrawable)
        waveDrawable!!.level = 10000
        return waveDrawable
    }

    fun toggleTimer() {
        if (onAnimation && !onStopProgress) {
            onAnimation = false
            onStopProgress = true
            return
        } else if (onStopProgress) {
            return
        }
        onAnimation = true
        jobId =CoroutineScope(Dispatchers.Main).launch {
            Toast.makeText(context, "Clicked", Toast.LENGTH_SHORT).show()
            while ((heightLevel in 0f..10000f) && onAnimation) {
//                Log.d("WaveProgress", "heightLevel : $heightLevel ${waveDrawable.level}")
                delay(delayMilliSeconds.toLong())
                if (!onAnimation) {
                    break
                } // Or you can put delay to last part of this while delay if you want to remove redundant
                // if break phrases however that would make time progression little bit awkward
                heightLevel += levelVariation
//                    imageView2.layoutParams.width = rWidth.toInt()
                waveDrawable!!.level = heightLevel.toInt()
            }
            onAnimation = false
            onStopProgress = false
            if (heightLevel >= 10000) {
                waveDrawable!!.level = 10000
            }
        }
    }

    fun restartTimer() {
        jobId!!.cancel()
        onAnimation = false
        onStopProgress = false
        heightLevel = 0f
        waveDrawable!!.level = 0
        toggleTimer()
    }
}