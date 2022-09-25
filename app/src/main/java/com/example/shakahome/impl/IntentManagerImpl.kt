package com.example.shakahome.impl

import android.content.Context
import android.content.Intent
import android.net.Uri
import com.example.core.IntentManager

class IntentManagerImpl(private val context: Context) : IntentManager {
    override fun transition(url: String) {
        val uri = Uri.parse(url)
        val intent = Intent(Intent.ACTION_VIEW, uri)
        context.startActivity(intent)
    }
}