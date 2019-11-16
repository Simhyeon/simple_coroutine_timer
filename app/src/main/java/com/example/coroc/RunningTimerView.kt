package com.example.coroc

import android.content.Context
import android.graphics.Color
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat.getColor
import kotlinx.coroutines.*

class RunningTimerView(val context: Context,val runningView: ImageView,val timerView: TextView, val imageArray : Array<Int>, var animationDelay: Int, val durationS: Int ,val startColorRes : Int, val endColorRes: Int){

    lateinit var runningJob: Job
    lateinit var timerJob: Job
    var isRunning = false
    var duration = durationS
    init {
        when{
            animationDelay < 0 -> {
                throw IllegalArgumentException("animationDelay should be positive integer")
            }
            durationS < 0 -> {
                throw IllegalArgumentException("Duration should be positive integer")
            }
            imageArray.isEmpty() -> {
                throw IllegalArgumentException("imageArray's length should be positive")
            }
        }
    }

    fun toggleTimer() {
        if (isRunning) {
            endTimer()
            return
        }

        isRunning = true
        runningJob = CoroutineScope(Dispatchers.Main).launch {
            var counter = 0
            while (duration > 0) {
                runningView.setImageResource(imageArray[counter])
                //imageNumber.text = counter.toString() + ":00"
                counter += 1
                if (counter > imageArray.size - 1) {
                    counter = 0
                }
                delay(animationDelay.toLong())
            }
        }
        timerJob = CoroutineScope(Dispatchers.Main).launch {
            var timeLeft = duration
            var blendedColor: Int
            while(timeLeft > 0) {
                delay(1000) // Wait for 1 second
                timeLeft -=1
                timerView.text = CorocUtil.timeToMSFormat(timeLeft)

                blendedColor = CorocUtil.getBlendedColor(
                    Color.valueOf(getColor(context, startColorRes)),
                    Color.valueOf(getColor(context, endColorRes)),
                    timeLeft.toFloat() / duration
                )

                runningView.setColorFilter(blendedColor)
                timerView.setTextColor(blendedColor)
            }
        }
    }

    fun endTimer() {
        runningJob.cancel()
        timerJob.cancel()
    }

    fun restartTimer() {
        endTimer()
        isRunning = false
        duration = durationS
        toggleTimer()
    }
}