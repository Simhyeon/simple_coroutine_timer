package com.example.coroc

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.running_timer.*
import kotlinx.coroutines.*

class RunningTimer : AppCompatActivity() {
    private var isRunning = false
    lateinit var runningJob: Job
    private val imageArray : Array<Int> = arrayOf(R.drawable.ic_running3,R.drawable.ic_running6)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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
                    imageNumber.text = counter.toString()
                    counter +=1
                    if (counter > imageArray.size - 1) {
                        counter = 0
                    }
                    delay(350)
                }
            }
        }
    }
}