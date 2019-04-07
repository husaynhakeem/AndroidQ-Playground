package com.husaynhakeem.location

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import kotlinx.android.synthetic.main.location_activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.location_activity_main)
        setupView()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when {
            requestCode == REQUEST_CODE_FOREGROUND && resultCode == Activity.RESULT_OK -> onForegroundPermissionGranted()
            requestCode == REQUEST_CODE_BACKGROUND && resultCode == Activity.RESULT_OK -> onBackgroundPermissionGranted()
        }
    }

    private fun setupView() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            mainForegroundPermissionButton.setOnClickListener { requestForegroundPermission() }
            mainBackgroundPermissionButton.setOnClickListener { requestBackgroundPermission() }
        } else {
            showMessage(R.string.main_android_version_not_supported)
        }
    }

    private fun requestForegroundPermission() {
        val foregroundPermission = ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
        if (foregroundPermission == PackageManager.PERMISSION_GRANTED) {
            showMessage(R.string.main_foreground_permission_already_granted)
        } else {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.ACCESS_COARSE_LOCATION),
                REQUEST_CODE_FOREGROUND
            )
        }
    }

    @SuppressLint("InlinedApi")
    private fun requestBackgroundPermission() {
        val foregroundPermission = ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
        val backgroundPermission =
            ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_BACKGROUND_LOCATION)

        if (arrayOf(foregroundPermission, backgroundPermission).all { it == PackageManager.PERMISSION_GRANTED }) {
            showMessage(R.string.main_background_permission_already_granted)
        } else {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_BACKGROUND_LOCATION),
                REQUEST_CODE_BACKGROUND
            )
        }
    }

    private fun onForegroundPermissionGranted() {
        showMessage(R.string.main_foreground_permission_granted)
    }

    private fun onBackgroundPermissionGranted() {
        showMessage(R.string.main_background_permission_granted)
    }

    private fun showMessage(@StringRes messageId: Int) {
        Toast.makeText(this, messageId, Toast.LENGTH_LONG).show()
    }

    companion object {
        private const val REQUEST_CODE_FOREGROUND = 123
        private const val REQUEST_CODE_BACKGROUND = 124
    }
}
