package com.example.asynctask

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.asynctask.ui.theme.AsyncTaskTheme

class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AsyncTaskTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) {
                    MyApp()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyApp() {
    var text1 by remember { mutableStateOf("Nhấn nút để bắt đầu công việc nền 1") }
    var text2 by remember { mutableStateOf("Nhấn nút để bắt đầu công việc nền 2") }

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("AsyncTask") })
        },
        content = { padding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
                    .padding(16.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text1)
                Spacer(modifier = Modifier.height(16.dp))
                Button(onClick = {
                    DownloadTask { result ->
                        text1 = result
                    }.execute()
                }) {
                    Text("Bắt đầu công việc nền 1")
                }
                Spacer(modifier = Modifier.height(16.dp))
                Text(text1)
                Spacer(modifier = Modifier.height(16.dp))
                Button(
                    onClick = {
                        DownloadTask { result ->
                            text2 = result
                        }.execute()
                    }
                ) {
                    Text("Bắt đầu công việc nền 2")
                }

            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MyApp()
}