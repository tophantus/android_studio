package com.example.asynctask

import android.os.AsyncTask

class DownloadTask(private val callback: (String) -> Unit) : AsyncTask<Void, Void, String>() {
    override fun doInBackground(vararg params: Void?): String {
        // Giả sử đây là công việc nền
        Thread.sleep(2000)  //Mô phỏng thời gian xử lý
        return "Kết quả từ công việc nền"
    }

    override fun onPostExecute(result: String) {
        super.onPostExecute(result)
        callback(result)
    }
}
