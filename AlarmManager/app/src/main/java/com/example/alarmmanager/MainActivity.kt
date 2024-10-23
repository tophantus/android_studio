package com.example.alarmmanager

import android.annotation.SuppressLint
import com.example.alarmmanager.ui.theme.AlarmManagerTheme
import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.SystemClock
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AlarmManagerTheme {
                Surface(color = MaterialTheme.colorScheme.background) {
                    AlarmScreen(context = this)
                }
            }
        }

    }
}
