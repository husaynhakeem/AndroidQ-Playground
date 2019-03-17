package com.husaynhakeem.androidq_playground.utilities

import android.util.DisplayMetrics
import android.view.Window


fun computeSpanCount(window: Window): Int {
    val metrics = DisplayMetrics()
    window.windowManager.defaultDisplay.getMetrics(metrics)
    val widthInDP = metrics.widthPixels / metrics.density
    val spanCountWithMinWidth = (widthInDP / 200).toInt()
    return Math.max(2, spanCountWithMinWidth)
}