package com.example.jobscheduler

import android.app.job.JobInfo
import android.app.job.JobScheduler
import android.content.ComponentName
import android.content.Context
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext

@Composable
fun MainScreen(
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = "Demo JobScheduler")
        Button(onClick = {
            // Lập lịch công việc
            val jobScheduler = context.getSystemService(Context.JOB_SCHEDULER_SERVICE) as JobScheduler
            val jobInfo = JobInfo.Builder(
                1,
                ComponentName(context, DownloadJobService::class.java)
            )
                .setRequiredNetworkType(JobInfo.NETWORK_TYPE_UNMETERED)
                .build()
            jobScheduler.schedule(jobInfo)
        }) {
            Text(text = "Start Download Job")
        }
    }
}