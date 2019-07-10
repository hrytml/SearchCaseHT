package com.example.searchcase.common

import android.content.Context

import android.util.DisplayMetrics

import android.view.WindowManager



class DeviceUtils {

    companion object {

        fun getDeviceWidth(context: Context): Int {

            val displayMetrics = DisplayMetrics()

            val windowManager = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager

            windowManager.defaultDisplay.getMetrics(displayMetrics)

            return displayMetrics.widthPixels

        }

    }

}