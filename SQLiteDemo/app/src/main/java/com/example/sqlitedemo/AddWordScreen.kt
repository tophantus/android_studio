package com.example.sqlitedemo

import android.annotation.SuppressLint
import android.database.sqlite.SQLiteDatabase
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
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
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun AddWordScreen(
    db: SQLiteDatabase,
    dbHelper: DatabaseHelper,
    navController: NavController
) {
    var word by remember { mutableStateOf("") }
    var definition by remember { mutableStateOf("") }


    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Add Word") },
                navigationIcon = {
                    IconButton(onClick = { navController.navigate("addWord") }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Add Word")
                    }
                }
            )
        },
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            OutlinedTextField(
                value = word,
                onValueChange = { word = it },
                label = { Text("Word") }
            )
            Spacer(modifier = Modifier.height(16.dp))
            OutlinedTextField(
                value = definition,
                onValueChange = { definition = it },
                label = { Text("Definition") }
            )
            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = {
                dbHelper.insertWord(db, word, definition)
                word = ""
                definition = ""
                navController.navigate("wordList")
            }) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Icon(Icons.Default.AddCircle, contentDescription = "Add Word")
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("Add Word")
                }

            }
        }
    }

}