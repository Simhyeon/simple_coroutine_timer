package com.example.coroc

import android.content.Context
import android.graphics.Color
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat.getColor
import kotlinx.coroutines.*

class RunningTimerView(context: Context, rootViewGroup: ViewGroup, var animationDelay: Int, var durationS: Int) : ImageView(context) {
    companion object {
        fun startTimer(context: Context, runningView: ImageView, imageArray : Array<Int>, timerView: TextView, duration : Int, animationDelay: Int, startColorRes : Int, endColorRes: Int) : Pair<Job, Job> {
            //imageNumber.text = counter.toString() + ":00"
            when{
                animationDelay < 0 -> {
                    throw IllegalArgumentException("animationDelay should be positive integer")
                }
                duration < 0 -> {
                    throw IllegalArgumentException("Duration should be positive integer")
                }
                imageArray.isEmpty() -> {
                    throw IllegalArgumentException("imageArray's length should be positive")
                }
                else ->{
                    val runningJob = CoroutineScope(Dispatchers.Main).launch {
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
                    val timerJob = CoroutineScope(Dispatchers.Main).launch {
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
                    return Pair(runningJob, timerJob)
                }
            }
        }

        fun endTimer(jobPair : Pair<Job, Job>) {
            jobPair.first.cancel()
            jobPair.second.cancel()
        }
    }
}