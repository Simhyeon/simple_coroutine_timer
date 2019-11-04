package com.example.coroc

import android.graphics.Point
import android.util.Log
import android.view.WindowManager
import kotlinx.coroutines.delay

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
        fun getLevelVariation(totalSeconds: Int, delayMilliSeconds: Int): Float {
            Log.d("Util", "${(10000 / totalSeconds).toFloat()} ${(10000 / totalSeconds).toFloat() / 1000}" +
                    " ${((10000 / totalSeconds).toFloat() / 1000 * delayMilliSeconds).toInt()}")
            return (10000 / totalSeconds).toFloat() / 1000 * delayMilliSeconds
        }
    }

}