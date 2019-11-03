package com.example.coroc

import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.wave_progress.*


class WaveProgress : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.requestWindowFeature(Window.FEATURE_NO_TITLE)
        this.window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)

        setContentView(R.layout.wave_progress)

        val waveDrawable = WaveDrawable(this, R.drawable.blur)
        wave_foreground.setImageDrawable(waveDrawable)

        val deviceWidth = CorocUtil.getDevicePoint(windowManager).first
        val deviceHeight = CorocUtil.getDevicePoint(windowManager).second
        var rHeight: Float = deviceHeight.toFloat()
        var counter = 600f //per 100 milliseconds -> second * 1000 / 100
        var rhUnit = deviceHeight / counter
        wave_background.layoutParams.width = deviceWidth
        wave_background.layoutParams.height = deviceHeight
        wave_foreground.layoutParams.width = deviceWidth
        wave_foreground.layoutParams.height = deviceHeight
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