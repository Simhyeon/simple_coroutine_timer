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

    private val givenTime: Int = 30
    lateinit var runningTimerView: RunningTimerView
    // 순차적으로 순환할 리소스(정수) 배열
    private val imageArray : Array<Int> = arrayOf(
        R.drawable.ic_licensefreehorse
    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.requestWindowFeature(Window.FEATURE_NO_TITLE)
        this.window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN) // 윗줄과 해서 상태창을 없애는 코드
        setContentView(R.layout.running_timer)

        imageNumber.text = CorocUtil.timeToMSFormat(givenTime)
        runningTimerView = RunningTimerView(this, runningView, imageNumber, imageArray, 150, givenTime, R.color.neonGreen, R.color.neonRed)

        changeImage.setOnClickListener {
            Toast.makeText(this, "Toggled", Toast.LENGTH_SHORT).show()
            runningTimerView.toggleTimer()
        }
    }
}