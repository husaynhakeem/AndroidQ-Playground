package com.husaynhakeem.sharingshortcut.data

import androidx.annotation.DrawableRes

data class Droid(
    val id: String,
    val name: String,
    @DrawableRes val image: Int
)