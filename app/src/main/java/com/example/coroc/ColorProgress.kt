package com.example.coroc

import android.graphics.drawable.ClipDrawable
import android.os.Bundle
import android.util.Log
import android.view.Window
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.image_progress.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class ColorProgress : AppCompatActivity() {

    private var onAnimation: Boolean = false
    private var onStopProgress: Boolean = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)//Remove title bar
        this.requestWindowFeature(Window.FEATURE_NO_TITLE)
        this.window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)

        setContentView(R.layout.image_progress)

        foreground_view.setImageDrawable(
            ClipDrawable(
                getDrawable(R.color.white), 50, 2
            )
        )
        val clipDrawable = foreground_view.drawable
        clipDrawable.level = 10000

        var heightLevel = 10000.0 // Initial value is 10000
        var delayMilliSeconds = 33 // Delay for while loop
        var totalSeconds = 15 // Initial value is 15
        var levelVariation = CorocUtil.getLevelVariation(totalSeconds, delayMilliSeconds)

        foreground_view.setOnClickListener{
            if(onAnimation && !onStopProgress) {
                onAnimation = false
                onStopProgress = true
                return@setOnClickListener
            } else if (onStopProgress) {
                return@setOnClickListener
            }
            onAnimation = true
            CoroutineScope(Dispatchers.Main).launch {
                Toast.makeText(applicationContext, "Clicked ${clipDrawable.level}", Toast.LENGTH_SHORT).show()
                while ( (heightLevel in 0f..10000f) && onAnimation) {
                    Log.d("Progress", "${clipDrawable.level}")
                    delay(delayMilliSeconds.toLong())
                    if (!onAnimation) {
                        break
                    } // Or you can put delay to last part of this while delay if you want to remove redundant
                      // if break phrases however that would make time progression little bit awkward
                    heightLevel -= levelVariation
//                    imageView2.layoutParams.width = rWidth.toInt()
                    clipDrawable.level = heightLevel.toInt()
                }
                onAnimation = false
                onStopProgress = false
                if (heightLevel <= 0f ) {
                    clipDrawable.level = 0
                }
            }
        }
    }
}