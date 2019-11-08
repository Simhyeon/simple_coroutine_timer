package com.example.coroc

import android.content.Context
import android.graphics.PorterDuff
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import kotlinx.coroutines.*

class WaveTimerView(context: Context, rootViewGroup: ViewGroup, var delayMilliSeconds: Int, var durationS: Int) : ImageView(context) {

    private var waveDrawable: CorocWaveDrawable? = null
    private var heightLevel= 0.0
    private var levelVariation = 0.0
    private var onAnimation = false
    private var onStopProgress = false
    private var jobId: Job? = null

    init {
        this.layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
        if (delayMilliSeconds < 1) {
            Toast.makeText(context, "DelayMilliSeconds should be natural numbers", Toast.LENGTH_SHORT).show()
            delayMilliSeconds = 33
        }
        if (durationS < 1) {
            Toast.makeText(context, "Duration should be natural numbers", Toast.LENGTH_SHORT).show()
            durationS = 60
        }
        this.bringToFront()
        this.levelVariation = CorocUtil.getLevelVariation(durationS, this.delayMilliSeconds)
        rootViewGroup.addView(this)
    }

    fun setWaveDrawable(colorRes: Int) : CorocWaveDrawable? {
        waveDrawable = CorocWaveDrawable(context, colorRes)
        super.setImageDrawable(waveDrawable)
        waveDrawable!!.level = 0
        return waveDrawable
    }

    fun setWaveDrawable(colorRes: Int, bgColorFilter: Int, filterMode: PorterDuff.Mode = PorterDuff.Mode.SRC) : CorocWaveDrawable? {
        waveDrawable = CorocWaveDrawable(context, colorRes, bgColorFilter, filterMode)
        super.setImageDrawable(waveDrawable)
        waveDrawable!!.level = 0
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
            while ((heightLevel in 0f..10000f) && onAnimation) {
                heightLevel += levelVariation
                waveDrawable!!.level = heightLevel.toInt()
                delay(delayMilliSeconds.toLong())
            }
            onAnimation = false
            onStopProgress = false
            if (heightLevel >= 10000) {
                waveDrawable!!.level = 10000
            }
        }
    }

    fun endTimer() {
        jobId!!.cancel()
        onAnimation = false
        onStopProgress = false
        heightLevel = 0.0
        waveDrawable!!.level = 0
    }

    fun restartTimer() {
        endTimer()
        toggleTimer()
    }
}