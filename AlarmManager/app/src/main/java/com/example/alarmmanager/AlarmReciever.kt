package com.example.alarmmanager

import android.annotation.SuppressLint
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

import android.widget.Toast

class AlarmReceiver : BroadcastReceiver() {
    @SuppressLint("ServiceCast")
    override fun onReceive(context: Context?, intent: Intent?) {
        // Hành động khi báo thức kích hoạt
        Toast.makeText(context, "Alarm Triggered!", Toast.LENGTH_SHORT).show()

    }
}
