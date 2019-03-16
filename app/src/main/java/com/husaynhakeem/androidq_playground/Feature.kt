package com.husaynhakeem.androidq_playground

import androidx.annotation.ColorRes
import androidx.annotation.StringRes

data class Feature(
    @StringRes val labelId: Int,
    @ColorRes val colorId: Int,
    val destinationClass: Class<*>
)