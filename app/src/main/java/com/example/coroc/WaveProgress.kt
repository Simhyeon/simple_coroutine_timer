package com.example.coroc

import android.graphics.Color
import android.graphics.PorterDuff
import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.wave_progress.*


class WaveProgress : AppCompatActivity() {

    var waveTimerView: WaveTimerView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.requestWindowFeature(Window.FEATURE_NO_TITLE)
        this.window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.wave_progress)

        waveTimerView = WaveTimerView(this, waveRoot, 33, 120)
        waveTimerView!!.setWaveDrawable(R.drawable.gradient_red_salvation, Color.argb(100,255,255,255), PorterDuff.Mode.SCREEN)
        //waveTimerView.setWaveDrawable(R.drawable.gradient_morpheus_den, Color.argb(0,255,255,255), PorterDuff.Mode.SRC)
        waveTimerView!!.requestLayout()

        waveTimerView!!.setOnClickListener {
            Toast.makeText(this, "Toggled", Toast.LENGTH_SHORT).show()
            waveTimerView!!.toggleTimer()
        }
    }

    override fun onPause() {
        super.onPause()
        waveTimerView!!.endTimer()
    }
}