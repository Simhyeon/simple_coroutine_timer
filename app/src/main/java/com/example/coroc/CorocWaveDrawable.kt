package com.example.coroc

import android.content.Context
import android.graphics.Canvas
import android.graphics.ColorFilter
import android.graphics.ColorMatrix
import android.graphics.drawable.Drawable
import android.os.Build
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.ImageView
import android.widget.RelativeLayout
import androidx.core.view.MarginLayoutParamsCompat

class CorocWaveDrawable: WaveDrawable {
    constructor(context: Context, drawable: Drawable?) : super(drawable) {
    }
    constructor(context: Context, imgRes: Int) : super(context, imgRes) {
    }
    constructor(context: Context, imgRes: Int, colorFilter: ColorFilter): super(context, imgRes) {
        bgFilter = colorFilter
    }

    fun setOptions(amplitude: Int, waveLength: Int, waveSpeed: Int) {
        super.setWaveAmplitude(amplitude)
        super.setWaveLength(waveLength)
        super.setWaveSpeed(waveSpeed)
    }
}