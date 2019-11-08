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
        /*
        * 딜레이밀리세컨드 마다 어느정도의 level을 감소시켜야 하는가를 리턴함.
        * */
        fun getLevelVariation(totalSeconds: Int, delayMilliSeconds: Int): Double {
            return (10000.0 / totalSeconds.toDouble()) / 1000.0 * delayMilliSeconds.toDouble()
        }
    }
}