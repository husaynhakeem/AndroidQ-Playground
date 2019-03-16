package com.husaynhakeem.sharingshortcut

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.sharingshortcut_activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.sharingshortcut_activity_main)
        setupView()
        publishShareTargets()
    }

    private fun setupView() {
        mainShareButton.setOnClickListener {
            val input = getInput()
            if (isInputValid(input)) {
                shareInput(input)
            } else {
                displayErrorMessage()
            }
        }
    }

    private fun getInput(): String = mainEditText.text.toString()

    private fun isInputValid(input: String): Boolean = input.isNotBlank()

    private fun shareInput(input: String) {
        val intent = Intent(Intent.ACTION_SEND).apply {
            type = "text/plain"
            putExtra(Intent.EXTRA_TEXT, input)
        }
        startActivity(Intent.createChooser(intent, getString(R.string.main_chooser_title)))
    }

    private fun displayErrorMessage() =
        Toast.makeText(this, R.string.main_invalid_input_error, Toast.LENGTH_SHORT).show()

    private fun publishShareTargets() {
        SharingShortcutManager().addAllShareShortcuts(this)
    }
}