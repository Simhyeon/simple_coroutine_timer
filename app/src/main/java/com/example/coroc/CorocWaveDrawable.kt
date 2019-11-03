package com.example.coroc

import android.content.Context
import android.graphics.Canvas
import android.graphics.ColorFilter
import android.graphics.ColorMatrix
import android.graphics.drawable.Drawable
import android.os.Build

class CorocWaveDrawable: WaveDrawable {
    constructor(context: Context, imgRes: Int) : super(context, imgRes) {}
    constructor(drawable: Drawable) : super(drawable) {}
    constructor(context: Context, imgRes: Int, colorFilter: ColorFilter) : super(context, imgRes) {
        setBgFilter(colorFilter)
    }
}