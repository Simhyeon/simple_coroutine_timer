package com.example.coroc

import android.graphics.Color
import android.graphics.Point
import android.util.Log
import android.view.WindowManager
import kotlin.IllegalArgumentException

class CorocUtil {
    companion object {
        // 디바이스의 픽셀크기를 Pair로 반환 first가 x값 second가 y값임.
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

        // 시간초를 받아서 디지털 시계 시간 문자열로 반환하는 함수 00:00의 형태로 반환.
        fun timeToMSFormat(givenSeconds: Int) : String {
            if (givenSeconds >= 216000) {
                throw IllegalArgumentException("Hour string is not supported")
            }

            var minute = (givenSeconds / 60).toString()
            var second = (givenSeconds % 60).toString()

            for (i in minute.length .. 1) {
                minute = "0$minute"
            }
            for (i in second.length .. 1) {
                second = "0$second"
            }

            return "$minute:$second"
        }

        // 시작 색상과 끝 색상 사이에서 비율에 따라서 블렌드된 색상값 (정수)를 반환
        fun getBlendedColor(startColor: Color, endColor: Color, ratio: Float) : Int {
            if (ratio > 1 || ratio < 0) {
                throw IllegalArgumentException("Ratio should be between 0 and 1")
            }
            val revRatio = 1 - ratio

            if (revRatio == 0f) {
                return startColor.toArgb()
            } else if (revRatio == 1f) { // Should not be reachable
                return endColor.toArgb()
            }

            val blendedColor = Color.valueOf(
                (endColor.red() - startColor.red()) * revRatio + startColor.red(),
                (endColor.green() - startColor.green()) * revRatio + startColor.green(),
                (endColor.blue() - startColor.blue()) * revRatio + startColor.blue()
            )
            return blendedColor.toArgb()
        }
    }
}