package com.example.coroc

import android.graphics.ColorMatrixColorFilter
import android.os.Bundle
import android.util.Log
import android.view.Window
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.wave_progress.*
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

        setContentView(R.layout.wave_progress)

        // Currently just pure white
        val bgFilter = ColorMatrixColorFilter(
            floatArrayOf(
                0f,     0f,     0f,     0f, 0f,
                0f,     0f,     0f,     0f, 0f,
                0f,     0f,     0f,     0f, 0f,
                0f,     0f,     0f,     0f, 0f
            ))

        val waveDrawable = CorocWaveDrawable(this, R.drawable.blur, bgFilter)
        wave_foreground.setImageDrawable(waveDrawable)
        waveDrawable.setOptions(50, 10000,21)
        waveDrawable.level = 0 // Litterly nothing to show

        var heightLevel = 0f // Initial value is 0
        var delayMilliSeconds = 33 // Delay for while loop
        var totalSeconds = 40 // Initial value is 60
        var levelVariation = CorocUtil.getLevelVariation(totalSeconds, delayMilliSeconds)

        wave_foreground.setOnClickListener {
            if(onAnimation && !onStopProgress) {
                onAnimation = false
                onStopProgress = true
                return@setOnClickListener
            } else if (onStopProgress) {
                return@setOnClickListener
            }
            onAnimation = true
            CoroutineScope(Dispatchers.Main).launch {
                Toast.makeText(applicationContext, "Clicked", Toast.LENGTH_SHORT).show()
                while ( (heightLevel in 0f..10000f) && onAnimation) {
                    Log.d("WaveProgress", "heightLevel : $heightLevel ${waveDrawable.level}")
                    delay(delayMilliSeconds.toLong())
                    if (!onAnimation) {
                        break
                    } // Or you can put delay to last part of this while delay if you want to remove redundant
                    // if break phrases however that would make time progression little bit awkward
                    heightLevel += levelVariation
//                    imageView2.layoutParams.width = rWidth.toInt()
                    waveDrawable.level = heightLevel.toInt()
                }
                onAnimation = false
                onStopProgress = false
                if (heightLevel >= 10000) {
                    waveDrawable.level = 10000
                }
            }
        }
//        seekBar.setOnSeekBarChangeListener( object : SeekBar.OnSeekBarChangeListener {
//            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
//                waveDrawable.setWaveAmplitude(progress)
//                Log.d("WaveAmplitude", "$progress")
//            }
//
//            override fun onStartTrackingTouch(seekBar: SeekBar?) {}
//
//            override fun onStopTrackingTouch(seekBar: SeekBar?) {
//
//            }
//        })
//
//        seekBar2.setOnSeekBarChangeListener( object : SeekBar.OnSeekBarChangeListener {
//            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
//                waveDrawable.setWaveLength(progress)
//            }
//
//            override fun onStartTrackingTouch(seekBar: SeekBar?) {}
//
//            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
//        })
//
//        seekBar3.setOnSeekBarChangeListener( object : SeekBar.OnSeekBarChangeListener {
//            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
//                waveDrawable.setWaveSpeed(progress)
//                Log.d("WaveSpeed", "$progress")
//            }
//
//            override fun onStartTrackingTouch(seekBar: SeekBar?) {
////                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
//            }
//
//            override fun onStopTrackingTouch(seekBar: SeekBar?) {
////                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
//            }
//        })
//
//        seekBar4.setOnSeekBarChangeListener( object : SeekBar.OnSeekBarChangeListener {
//            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
//                waveDrawable.setLevel(progress * 100)
//            }
//
//            override fun onStartTrackingTouch(seekBar: SeekBar?) {
////                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
//            }
//
//            override fun onStopTrackingTouch(seekBar: SeekBar?) {
////                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
//            }
//        })
    }
}