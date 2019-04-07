package com.husaynhakeem.androidq_playground.data

import com.husaynhakeem.androidq_playground.R
import com.husaynhakeem.location.MainActivity as LocationMainActivity
import com.husaynhakeem.settingspanel.MainActivity as SettingsPanelMainActivity
import com.husaynhakeem.sharingshortcut.MainActivity as SharingShortcutMainActivity

object FeaturesDataSource {

    private val features: List<Feature> by lazy {
        listOf(
            Feature(
                R.string.feature_sharing_shortcut_label,
                android.R.color.holo_orange_light,
                SharingShortcutMainActivity::class.java
            ),
            Feature(
                R.string.feature_settings_panel_label,
                android.R.color.holo_blue_light,
                SettingsPanelMainActivity::class.java
            ),
            Feature(
                R.string.feature_location_label,
                android.R.color.holo_purple,
                LocationMainActivity::class.java
            )
        )
    }

    fun getAllFeatures() = features
}