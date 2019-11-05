package com.example.coroc

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.layout_test.*
import kotlinx.android.synthetic.main.wave_progress.*

class LayoutTest :AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val waveDrawable = CorocWaveDrawable(this, R.drawable.blur)

        // Show layout width
        testButton1.setOnClickListener {
            Toast.makeText(this,"${testView.layoutParams.width}", Toast.LENGTH_SHORT).show()
        }
        // Set to device width
        testButton2.setOnClickListener {
            testView.layoutParams.width = CorocUtil.getDevicePoint(windowManager).first
            Toast.makeText(this,"${testView.layoutParams.width}", Toast.LENGTH_SHORT).show()
        }
        // Increase by value
        testButton3.setOnClickListener {
            testView.layoutParams.width += 100
            Toast.makeText(this,"${testView.layoutParams.width}", Toast.LENGTH_SHORT).show()
        }
        // Decrease by value
        testButton4.setOnClickListener {
            testView.layoutParams.width -= 100
            Toast.makeText(this,"${testView.layoutParams.width}", Toast.LENGTH_SHORT).show()
        }

        // Set to wave drawable
        testButton5.setOnClickListener {
            Toast.makeText(this,"${testView.layoutParams.width}", Toast.LENGTH_SHORT).show()
            testView.setImageDrawable(waveDrawable)
        }
    }
}