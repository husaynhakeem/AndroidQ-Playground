package com.husaynhakeem.settingspanel

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.settings_panel_activity_main.*


class MainActivity : AppCompatActivity() {

    @SuppressLint("InlinedApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.settings_panel_activity_main)

        mainInternetButton.setOnClickListener { startActivity(Intent(this, InternetConnectivityActivity::class.java)) }
        mainNfcButton.setOnClickListener { displaySettingsPanel(Settings.Panel.ACTION_NFC) }
        mainVolumeButton.setOnClickListener { displaySettingsPanel(Settings.Panel.ACTION_VOLUME) }
    }

    private fun displaySettingsPanel(action: String) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            startActivity(Intent(action))
        } else {
            Toast.makeText(this, R.string.main_settings_panel_not_available, Toast.LENGTH_SHORT).show()
        }
    }
}