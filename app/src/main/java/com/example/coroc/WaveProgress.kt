package com.example.coroc

import android.graphics.ColorMatrixColorFilter
import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.image_progress.*
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
                1f,     0f,     0f,     0f, 0f,
                0f,     1f,     0f,     0f, 0f,
                0f,     0f,     1f,     0f, 0f,
                0f,     0f,     0f,     1f, 0f
            ))

        val waveDrawable = CorocWaveDrawable(this, R.drawable.blur, bgFilter)
        wave_foreground.setImageDrawable(waveDrawable)
        waveDrawable.setOptions(43, 4,24)
        waveDrawable.level = 0 // Litterly nothing to show

        val deviceWidth = CorocUtil.getDevicePoint(windowManager).first
        val deviceHeight = CorocUtil.getDevicePoint(windowManager).second
        var rHeight: Float = deviceHeight.toFloat()
        var counter = 600f //per 100 milliseconds -> second * 1000 / 100
        var rhUnit = deviceHeight / counter

        wave_background.layoutParams.width = deviceWidth
        wave_background.layoutParams.height = deviceHeight
        wave_foreground.layoutParams.width = deviceWidth
        wave_foreground.layoutParams.height = deviceHeight

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
                while ( rHeight > 0 && onAnimation) {
                    delay(100)
                    if (!onAnimation) {
                        break
                    }
                    foregound_view.layoutParams.height = rHeight.toInt()
                }
                onAnimation = false
                onStopProgress = false
                foregound_view.layoutParams.height = 0
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