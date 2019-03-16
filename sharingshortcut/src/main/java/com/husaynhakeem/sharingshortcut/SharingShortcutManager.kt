package com.husaynhakeem.sharingshortcut

import android.content.Context
import android.content.Intent
import androidx.core.app.Person
import androidx.core.content.pm.ShortcutInfoCompat
import androidx.core.content.pm.ShortcutManagerCompat

class SharingShortcutManager {

    fun addAllShareShortcuts(context: Context) {
        val shareShortcuts = buildShortcutInfoList(maximumNumberOfShortcuts(context), context)
        ShortcutManagerCompat.addDynamicShortcuts(context, shareShortcuts)
    }

    private fun buildShortcutInfoList(size: Int, context: Context): List<ShortcutInfoCompat> {
        return (1..size).map { buildShortcutInfo(it, context) }
    }

    private fun buildShortcutInfo(index: Int, context: Context): ShortcutInfoCompat {
        return ShortcutInfoCompat.Builder(context, index.toString())
            .setShortLabel("Person $index short label")
            .setPerson(buildPerson(index))
            .setCategories(setOf("com.husaynhakeem.sharingshortcut.category.SEND_EMAIL"))
            .setIntent(Intent(Intent.ACTION_DEFAULT))
            .build()
    }

    private fun buildPerson(index: Int): Person {
        return Person.Builder()
            .setKey(index.toString())
            .setName("Person $index")
            .build()
    }

    private fun maximumNumberOfShortcuts(context: Context): Int {
        return ShortcutManagerCompat.getMaxShortcutCountPerActivity(context)
    }

    fun removeAllShareShortcuts(context: Context) {
        ShortcutManagerCompat.removeAllDynamicShortcuts(context)
    }
}