package com.example.coroc

import android.graphics.Point
import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.util.Log.d
import android.view.Window
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.image_progress.*
import kotlinx.coroutines.*

class ColorProgress : AppCompatActivity() {

    var isStopped: Boolean = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)//Remove title bar
        this.requestWindowFeature(Window.FEATURE_NO_TITLE)
        this.window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)

        setContentView(R.layout.image_progress)

        val display = windowManager.defaultDisplay
        val size = Point()
        display.getSize(size)
        val width = size.x
        val height = size.y
//        var rWidth: Float = width.toFloat()
        var rHeight: Float = height.toFloat()
        var counter = textView3.text.toString().toFloat() * 10 //per 100 milliseconds -> second * 1000 / 100
//        val rwUnit = width / counter
        var rhUnit = height / counter
        background_view.layoutParams.width = width
        background_view.layoutParams.height = height
        foregound_view.layoutParams.width = width
        foregound_view.layoutParams.height = height
//        Toast.makeText(applicationContext, "${imageView2.layoutParams.width} ${imageView2.layoutParams.height}", Toast.LENGTH_LONG).show()

        editText.addTextChangedListener(object: TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                if (!TextUtils.isEmpty(editText.text)){
                    counter = editText.text.toString().toFloat() * 10f
                    rhUnit = height / counter
                }
            }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })

        textView3.text = isStopped.toString()
        foregound_view.setOnClickListener{
            if(isStopped) return@setOnClickListener
            CoroutineScope(Dispatchers.Main).launch {
                Toast.makeText(applicationContext, "Clicked", Toast.LENGTH_SHORT).show()
                while ( rHeight > 0) {
                    delay(100)
                    if (isStopped) {
                        break
                    }
//                    rWidth -= rwUnit
                    rHeight -= rhUnit
                    textView2.text = rHeight.toString()
//                    imageView2.layoutParams.width = rWidth.toInt()
                    foregound_view.layoutParams.height = rHeight.toInt()
                }
                isStopped = true
                textView3.text = isStopped.toString()
                foregound_view.layoutParams.height = 0
                Toast.makeText(applicationContext, "After while on scope", Toast.LENGTH_SHORT).show()
            }
            Toast.makeText(applicationContext, "After scope while listener", Toast.LENGTH_SHORT).show()
        }
    }
}