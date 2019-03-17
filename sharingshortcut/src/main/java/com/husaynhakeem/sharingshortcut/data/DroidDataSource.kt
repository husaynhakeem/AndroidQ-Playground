package com.husaynhakeem.sharingshortcut.data

import com.husaynhakeem.sharingshortcut.R

object DroidDataSource {

    private val droids: List<Droid> by lazy {
        listOf(
            Droid(
                "black",
                "Black droid",
                R.drawable.ic_android_black
            ),
            Droid(
                "blue",
                "Blue droid",
                R.drawable.ic_android_blue
            ),
            Droid(
                "green",
                "Green droid",
                R.drawable.ic_android_green
            ),
            Droid(
                "orange",
                "Orange droid",
                R.drawable.ic_android_orange
            ),
            Droid(
                "purple",
                "Purple droid",
                R.drawable.ic_android_purple
            ),
            Droid(
                "red",
                "Red droid",
                R.drawable.ic_android_red
            )
        )
    }

    fun getAllDroids() = droids

    fun getDroidById(id: String): Droid {
        return droids.firstOrNull { id == it.id }
            ?: throw RuntimeException("DroidDataSource.getDroidById($id): Unknown id")
    }
}