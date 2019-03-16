package com.husaynhakeem.androidq_playground

import com.husaynhakeem.sharingshortcut.MainActivity as SharingShortcutMainActivity

object FeaturesDataSource {

    fun get(): List<Feature> {
        return listOf(
            Feature(
                R.string.feature_sharing_shortcut_label,
                android.R.color.holo_orange_light,
                SharingShortcutMainActivity::class.java
            )
        )
    }
}