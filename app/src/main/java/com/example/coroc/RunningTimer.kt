package com.example.coroc

import android.content.res.Resources
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.running_timer.*
import kotlinx.coroutines.*

class RunningTimer : AppCompatActivity() {

    private var isRunning = false
    lateinit var runningJob: Job
    lateinit var timerJob: Job

    var blendedColor : Int = 0
    val givenTime: Int = 10
    var timeLeft: Int = givenTime

    private val imageArray : Array<Int> = arrayOf(
        R.drawable.ic_running_horse_1,R.drawable.ic_running_horse_2,R.drawable.ic_running_horse_3,
        R.drawable.ic_running_horse_4,R.drawable.ic_running_horse_5,R.drawable.ic_running_horse_6,
        R.drawable.ic_running_horse_7,R.drawable.ic_running_horse_8,R.drawable.ic_running_horse_9,
        R.drawable.ic_running_horse_10,R.drawable.ic_running_horse_11,R.drawable.ic_running_horse_12
    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.requestWindowFeature(Window.FEATURE_NO_TITLE)
        this.window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.running_timer)

        imageNumber.text = CorocUtil.timeToMSFormat(givenTime)
        changeImage.setOnClickListener {
            if(isRunning){
                runningJob.cancel()
                timerJob.cancel()
                Toast.makeText(this, "Cancelled", Toast.LENGTH_SHORT).show()
                isRunning = false
                return@setOnClickListener
            } else {
                Toast.makeText(this, "Clicked", Toast.LENGTH_SHORT).show()
            }

            isRunning = true
            runningJob = CoroutineScope(Dispatchers.Main).launch {
                var counter = 0
                while (isRunning && timeLeft > 0) {
                    runningView.setImageResource(imageArray[counter])
                    //imageNumber.text = counter.toString() + ":00"
                    counter +=1
                    if (counter > imageArray.size - 1) {
                        counter = 0
                    }
                    delay(150) // 1000 / 12 하면 83.33333 이다. 1초 안에 12프레임이 모두 구현되지만 결국 오차가 생긴다.
                }
            }
            timerJob = CoroutineScope(Dispatchers.Main).launch {
                while(isRunning && timeLeft > 0) {
                    delay(1000) // Wait for 1 second
                    timeLeft -=1
                    imageNumber.text = CorocUtil.timeToMSFormat(timeLeft)

                    blendedColor = CorocUtil.getBlendedColor(
                            Color.valueOf(getColor(R.color.neonGreen)),
                            Color.valueOf(getColor(R.color.neonRed)),
                            timeLeft.toFloat() / givenTime
                        )

                    runningView.setColorFilter(blendedColor)
                    imageNumber.setTextColor(blendedColor)
                }
            }
        }
    }
}