package com.husaynhakeem.sharingshortcut.utilities

import java.util.concurrent.Executors


private val IO_EXECUTOR = Executors.newSingleThreadExecutor()

fun runOnIOThread(action: () -> Unit) {
    IO_EXECUTOR.execute(action)
}