package com.husaynhakeem.androidq_playground.data

import com.husaynhakeem.androidq_playground.R
import com.husaynhakeem.sharingshortcut.MainActivity as SharingShortcutMainActivity

object FeaturesDataSource {

    private val features: List<Feature> by lazy {
        listOf(
            Feature(
                R.string.feature_sharing_shortcut_label,
                android.R.color.holo_orange_light,
                SharingShortcutMainActivity::class.java
            )
        )
    }

    fun getAllFeatures() = features
}