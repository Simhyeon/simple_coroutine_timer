package com.example.coroc

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        toColorProgress.setOnClickListener {
            val intent = Intent(this, ColorProgress::class.java)
            startActivity(intent)
        }
        toWaveProgress.setOnClickListener {
            val intent = Intent(this, WaveProgress::class.java)
            startActivity(intent)
        }
    }
}



