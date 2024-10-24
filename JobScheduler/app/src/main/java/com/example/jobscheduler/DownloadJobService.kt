package com.example.jobscheduler

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.job.JobParameters
import android.app.job.JobService
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat

class DownloadJobService : JobService() {
    override fun onStartJob(params: JobParameters?): Boolean {
        // Chuyển công việc sang luồng khác
        Thread {
            // Giả lập tải dữ liệu
            Thread.sleep(3000)

            // Gửi thông báo khi hoàn thành
            sendCompletionNotification()

            // Công việc hoàn thành
            jobFinished(params, false)
        }.start()
        return true
    }

    override fun onStopJob(params: JobParameters?): Boolean {
        return true // Lên lịch lại công việc nếu cần
    }

    private fun sendCompletionNotification() {
        val channelId = "download_channel"
        val channelName = "Download Notifications"
        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        // Tạo kênh thông báo cho Android 8.0 trở lên
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                channelId,
                channelName,
                NotificationManager.IMPORTANCE_DEFAULT
            )
            notificationManager.createNotificationChannel(channel)
        }
        // Tạo Intent khi nhấn vào thông báo
        val intent = Intent(this, NotificationActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(
            this,
            0,
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )


        val builder = NotificationCompat.Builder(this, channelId)
            .setSmallIcon(R.drawable.ic_download) // Đặt biểu tượng thông báo
            .setContentTitle("Download Complete")
            .setContentText("Your data has been downloaded.")
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setContentIntent(pendingIntent)

        notificationManager.notify(1, builder.build())
    }
}
