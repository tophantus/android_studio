package com.example.alarmmanager

import android.annotation.SuppressLint
import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.SystemClock
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp




@Composable
fun AlarmScreen(context: Context) {

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Button(onClick = {
            //setExactAlarm(context, 5000)
            //setElapsedRealtimeAlarm(context)
            setAlarmClock(context)
        }) {
            Text(text = "Set Alarm")
        }
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = {
            cancelAlarm(context)
        }) {
            Text(text = "Cancel Alarm")
        }
    }
}

@SuppressLint("ServiceCast")
fun setExactAlarm(context: Context, time: Int) {
    val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
    val intent = Intent(context, AlarmReceiver::class.java)
    val pendingIntent = PendingIntent.getBroadcast(
        context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
    )

    val triggerTime = System.currentTimeMillis() + time

    alarmManager.setExact(AlarmManager.RTC_WAKEUP, triggerTime, pendingIntent)
    Toast.makeText(context, "Alarm set for ${time/1000} seconds from now", Toast.LENGTH_SHORT).show()
}

fun setAlarm(context: Context, time: Int) {
    val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
    val intent = Intent(context, AlarmReceiver::class.java)
    val pendingIntent = PendingIntent.getBroadcast(
        context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
    )

    val triggerTime = System.currentTimeMillis() + time

    alarmManager.set(AlarmManager.RTC_WAKEUP, triggerTime, pendingIntent)
    Toast.makeText(context, "Alarm set for ${time/1000} seconds from now", Toast.LENGTH_SHORT).show()
}

fun setWindowAlarm(context: Context, time: Int, window: Long) {
    val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
    val intent = Intent(context, AlarmReceiver::class.java)
    val pendingIntent = PendingIntent.getBroadcast(
        context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
    )

    val triggerTime = System.currentTimeMillis() + time

    alarmManager.setWindow(
        AlarmManager.RTC_WAKEUP,
        triggerTime,
        window,
        pendingIntent
    )
    Toast.makeText(context, "Alarm set for ${time/1000} seconds from now", Toast.LENGTH_SHORT).show()
}

fun setElapsedRealtimeAlarm(context: Context) {
    val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
    val intent = Intent(context, AlarmReceiver::class.java)
    val pendingIntent = PendingIntent.getBroadcast(
        context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
    )

    val triggerAtMillis = System.currentTimeMillis() + 10000
    val alarmClockInfo = AlarmManager.AlarmClockInfo(triggerAtMillis, pendingIntent)
    alarmManager.setAlarmClock(alarmClockInfo, pendingIntent)
    Toast.makeText(context, "Alarm set for 5 seconds from now", Toast.LENGTH_SHORT).show()
}

fun setAlarmClock(context: Context) {
    val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
    val intent = Intent(context, AlarmReceiver::class.java)
    val pendingIntent = PendingIntent.getBroadcast(
        context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
    )

    val triggerAtMillis = System.currentTimeMillis() + 5000 // Kích hoạt sau 5 giây
    val alarmClockInfo = AlarmManager.AlarmClockInfo(triggerAtMillis, pendingIntent)
    alarmManager.setAlarmClock(alarmClockInfo, pendingIntent)
}

fun cancelAlarm(context: Context) {
    val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
    val intent = Intent(context, AlarmReceiver::class.java)
    val pendingIntent = PendingIntent.getBroadcast(
        context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
    )
    alarmManager.cancel(pendingIntent)
}

fun isAlarmPendingIntent(context: Context): Boolean {
    val intent = Intent(context, AlarmReceiver::class.java)
    val pendingIntent = PendingIntent.getBroadcast(
        context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
    )
    return pendingIntent != null
}





