package com.example.coroc

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.running_timer.*
import kotlinx.coroutines.*

class RunningTimer : AppCompatActivity() {
    private var isRunning = false
    lateinit var runningJob: Job
    lateinit var timerJob: Job
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

        changeImage.setOnClickListener {
            if(isRunning){
                runningJob.cancel()
                Toast.makeText(this, "Cancelled", Toast.LENGTH_SHORT).show()
                isRunning = false
                return@setOnClickListener
            } else {
                Toast.makeText(this, "Clicked", Toast.LENGTH_SHORT).show()
            }

            isRunning = true
            runningJob = CoroutineScope(Dispatchers.Main).launch {
                var counter = 0
                while (isRunning) {
                    runningView.setImageResource(imageArray[counter])
                    imageNumber.text = counter.toString() + ":00"
                    counter +=1
                    if (counter > imageArray.size - 1) {
                        counter = 0
                    }
                    delay(100)
                }
            }
            timerJob = CoroutineScope(Dispatchers.Main).launch {
                while(isRunning) {

                }
            }
        }
    }
}