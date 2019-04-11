package com.husaynhakeem.settingspanel

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_internet_connectivity.*

class InternetConnectivityActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_internet_connectivity)
        loadWebPage()
    }

    private fun loadWebPage() {
        if (isDeviceConnected()) {
            internetConnectivityWebView.loadUrl(ANY_WEBSITE_URL)
        } else {
            displayNoConnectivityMessage()
        }
    }

    @Suppress("DEPRECATION")
    private fun isDeviceConnected(): Boolean {
        val connectivityManager =
            (getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?) ?: return false
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            val activeNetworkInfo = connectivityManager.activeNetworkInfo
            return activeNetworkInfo != null && activeNetworkInfo.isConnected &&
                    (activeNetworkInfo.type in arrayOf(ConnectivityManager.TYPE_WIFI, ConnectivityManager.TYPE_MOBILE))
        } else {
            val activeNetwork = connectivityManager.activeNetwork ?: return false
            val networkCapabilities = connectivityManager.getNetworkCapabilities(activeNetwork) ?: return false
            return networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) ||
                    networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) ||
                    networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_VPN)
        }
    }

    private fun displayNoConnectivityMessage() {
        Snackbar.make(
            internetConnectivityWebView,
            R.string.settings_panel_network_connectivity_no_connectivity_message,
            Snackbar.LENGTH_INDEFINITE
        )
            .setAction(R.string.settings_panel_network_connectivity_no_connectivity_action) { displaySettingsPanel() }
            .show()
    }

    private fun displaySettingsPanel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            val intent = Intent(Settings.Panel.ACTION_INTERNET_CONNECTIVITY)
            startActivityForResult(intent, REQUEST_CODE)
        } else {
            Toast.makeText(this, R.string.main_settings_panel_not_available, Toast.LENGTH_SHORT).show()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CODE) {
            loadWebPage()
        }
    }

    companion object {
        private const val ANY_WEBSITE_URL = "https://www.google.com"
        private const val REQUEST_CODE = 10
    }
}
