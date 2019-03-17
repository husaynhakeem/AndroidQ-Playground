package com.husaynhakeem.sharingshortcut

import android.app.Activity
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.husaynhakeem.sharingshortcut.data.DroidDataSource
import com.husaynhakeem.sharingshortcut.utilities.EXTRA_DROID_ID
import com.husaynhakeem.sharingshortcut.utilities.MIME_TYPE_TEXT_PLAIN
import com.husaynhakeem.sharingshortcut.utilities.REQUEST_CODE_SELECT_DROID
import kotlinx.android.synthetic.main.sharingshortcut_activity_send_message.*

class SendMessageActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.sharingshortcut_activity_send_message)

        when {
            isComingFromShareShortcut(intent) && hasDroidId(intent) -> {
                val (droidId, message) = extractDroidIdAndMessageFrom(intent)
                setupView(droidId, message)
            }
            isComingFromShareShortcut(intent) -> openDroidSelectionScreen()
            else -> finish()
        }
    }

    private fun isComingFromShareShortcut(intent: Intent) =
        intent.action == Intent.ACTION_SEND && intent.type == MIME_TYPE_TEXT_PLAIN

    private fun hasDroidId(intent: Intent) =
        Build.VERSION.SDK_INT >= Build.VERSION_CODES.P && intent.hasExtra(Intent.EXTRA_SHORTCUT_ID)

    private fun extractDroidIdAndMessageFrom(intent: Intent): Pair<String, String> {
        val message = intent.getStringExtra(Intent.EXTRA_TEXT)
        val droidId = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            intent.getStringExtra(Intent.EXTRA_SHORTCUT_ID)
        } else {
            ""
        }
        return Pair(droidId, message)
    }

    private fun openDroidSelectionScreen() {
        val intent = Intent(this, SelectDroidActivity::class.java)
        startActivityForResult(intent, REQUEST_CODE_SELECT_DROID)
    }

    private fun setupView(droidId: String, message: String) {
        val droid = DroidDataSource.getDroidById(droidId)
        sendMessageDroidTextView.text = droid.name
        sendMessageDroidTextView.setCompoundDrawablesRelativeWithIntrinsicBounds(ContextCompat.getDrawable(this, droid.image), null, null, null)

        sendMessageMessageTextView.text = message

        sendMessageButton.setOnClickListener {
            val toastMessage = getString(R.string.send_message_success_message, droid.name)
            Toast.makeText(this, toastMessage, Toast.LENGTH_LONG).show()
            finish()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CODE_SELECT_DROID && resultCode == Activity.RESULT_OK) {
            val droidId = data?.getStringExtra(EXTRA_DROID_ID)
                ?: throw RuntimeException("SendMessageActivity: Must select a droid")
            val message = intent.getStringExtra(Intent.EXTRA_TEXT) ?: ""
            setupView(droidId, message)
        }
    }
}