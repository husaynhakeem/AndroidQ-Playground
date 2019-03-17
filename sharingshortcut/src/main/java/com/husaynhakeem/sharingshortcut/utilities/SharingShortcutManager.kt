package com.husaynhakeem.sharingshortcut.utilities

import android.content.Context
import android.content.Intent
import androidx.core.app.Person
import androidx.core.content.pm.ShortcutInfoCompat
import androidx.core.content.pm.ShortcutManagerCompat
import androidx.core.graphics.drawable.IconCompat
import com.husaynhakeem.sharingshortcut.data.Droid
import com.husaynhakeem.sharingshortcut.data.DroidDataSource

class SharingShortcutManager {

    fun addAllShareShortcuts(context: Context) {
        runOnIOThread {
            ShortcutManagerCompat.addDynamicShortcuts(context, getShortcutInfoList(context))
        }
    }

    fun removeAllShareShortcuts(context: Context) {
        runOnIOThread {
            ShortcutManagerCompat.removeAllDynamicShortcuts(context)
        }
    }

    private fun getShortcutInfoList(context: Context): List<ShortcutInfoCompat> {
        return DroidDataSource.getAllDroids()
            .take(maximumNumberOfShortcuts(context))
            .map { it.toShortcutInfo(context) }
    }

    private fun maximumNumberOfShortcuts(context: Context): Int {
        return ShortcutManagerCompat.getMaxShortcutCountPerActivity(context)
    }

    private fun Droid.toShortcutInfo(context: Context): ShortcutInfoCompat {
        return ShortcutInfoCompat.Builder(context, this.id)
            .setShortLabel(this.name)
            .setPerson(this.toPerson(context))
            .setIcon(IconCompat.createWithResource(context, this.image))
            .setCategories(setOf(CATEGORY_SEND_MESSAGE))
            .setIntent(Intent(Intent.ACTION_DEFAULT))
            .build()
    }

    private fun Droid.toPerson(context: Context): Person {
        return Person.Builder()
            .setKey(this.id)
            .setName(this.name)
            .build()
    }

    companion object {
        private const val CATEGORY_SEND_MESSAGE = "com.husaynhakeem.sharingshortcut.category.SEND_TEXT"
    }
}