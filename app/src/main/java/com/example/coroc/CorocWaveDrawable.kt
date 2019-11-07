package com.example.coroc

import android.content.Context
import android.graphics.*
import android.graphics.drawable.Drawable

class CorocWaveDrawable: WaveDrawable {
    constructor(drawable: Drawable?) : super(drawable)
    constructor(context: Context, imgRes: Int) : super(context, imgRes)
    constructor(context: Context, imgRes: Int, bgColorFilter: Int, filterMode: PorterDuff.Mode): super(context, imgRes) {
        val colorFilter = PorterDuffColorFilter(bgColorFilter, filterMode)
        bgFilter = colorFilter
    }
}