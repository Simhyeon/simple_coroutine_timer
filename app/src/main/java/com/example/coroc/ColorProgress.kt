package com.example.coroc

import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.view.Window
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.image_progress.*
import kotlinx.coroutines.*

class ColorProgress : AppCompatActivity() {

    private var onAnimation: Boolean = false
    private var onStopProgress: Boolean = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)//Remove title bar
        this.requestWindowFeature(Window.FEATURE_NO_TITLE)
        this.window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)

        setContentView(R.layout.image_progress)

        val deviceWidth = CorocUtil.getDevicePoint(windowManager).first
        val deviceHeight = CorocUtil.getDevicePoint(windowManager).second
        var rHeight: Float = deviceHeight.toFloat()
        var counter = 600f //per 100 milliseconds -> second * 1000 / 100
        var rhUnit = deviceHeight / counter
        background_view.layoutParams.width = deviceWidth
        background_view.layoutParams.height = deviceHeight
        foregound_view.layoutParams.width = deviceWidth
        foregound_view.layoutParams.height = deviceHeight
//        Toast.makeText(applicationContext, "${imageView2.layoutParams.width} ${imageView2.layoutParams.height}", Toast.LENGTH_LONG).show()

        editText.addTextChangedListener(object: TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                if (!TextUtils.isEmpty(editText.text)){
                    counter = editText.text.toString().toFloat() * 10f
                    rhUnit = deviceHeight / counter
                }
            }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })

        textView3.text = onAnimation.toString()
        foregound_view.setOnClickListener{
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
                    } // Or you can put delay to last part of this while delay if you want to remove redundant
                      // if break phrases however that would make time progression little bit awkward
                    rHeight -= rhUnit
                    textView2.text = rHeight.toString()
//                    imageView2.layoutParams.width = rWidth.toInt()
                    foregound_view.layoutParams.height = rHeight.toInt()
                }
                onAnimation = false
                onStopProgress = false
                textView3.text = onAnimation.toString()
                foregound_view.layoutParams.height = 0
            }
        }
    }
}