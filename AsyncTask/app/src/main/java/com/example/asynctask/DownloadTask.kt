package com.example.asynctask

import android.os.AsyncTask

class Task1(private val callback: (String) -> Unit) : AsyncTask<Void, Void, String>() {
    override fun doInBackground(vararg params: Void?): String {
        Thread.sleep(2000)  // Giả sử đây là công việc nền
        return "Kết quả từ công việc nền 1"
    }

    override fun onPostExecute(result: String) {
        super.onPostExecute(result)
        callback(result)
    }
}

class Task2(private val callback: (String) -> Unit) : AsyncTask<Void, Void, String>() {
    override fun doInBackground(vararg params: Void?): String {
        Thread.sleep(2000)  // Giả sử đây là công việc nền
        return "Kết quả từ công việc nền 2"
    }

    override fun onPostExecute(result: String) {
        super.onPostExecute(result)
        callback(result)
    }
}
