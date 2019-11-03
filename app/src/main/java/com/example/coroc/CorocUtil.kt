package com.example.coroc

import android.graphics.Point
import android.view.WindowManager

class CorocUtil {
    companion object {
        fun getDevicePoint(windowManager : WindowManager): Pair<Int, Int> {
            val display = windowManager.defaultDisplay
            val size = Point()
            display.getSize(size)
            val width = size.x
            val height = size.y
            return Pair(width, height)
        }
    }

}