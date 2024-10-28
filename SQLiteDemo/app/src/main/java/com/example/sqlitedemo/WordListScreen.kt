package com.example.sqlitedemo

import android.annotation.SuppressLint
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WordListScreen(
    db: SQLiteDatabase,
    navController: NavController
) {
    val words = fetchData(
        db
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Word List") },
                actions = {
                    IconButton(onClick = { navController.navigate("addWord") }) {
                        Icon(Icons.Default.Add, contentDescription = "Add Word")
                    }
                }
            )
        },
    ) {
        Column(

            modifier = Modifier
                .padding(
                    top = 100.dp,
                    start = 32.dp,
                    end = 16.dp
                )
        ) {
            var cnt = 1
            words.forEach { word ->
                Text(
                    text = "No $cnt"
                )

                Text(
                    text = "Word: ${word.word}",
                    style = MaterialTheme.typography.bodyMedium
                )
                Text(
                    text = "Definition: ${word.definition}",
                    style = MaterialTheme.typography.bodyMedium
                )
                Spacer(modifier = Modifier.height(24.dp))

                cnt += 1
            }
        }
    }
}



fun fetchData(
    db: SQLiteDatabase
): List<Word> {
    val words = mutableListOf<Word>()
    val cursor = db.query(
        "WORD_LIST_TABLE",
        arrayOf("word", "definition"),
        null,
        null,
        null,
        null,
        null
    )

    if (cursor.moveToFirst()) {
        do {
            val word = cursor.getString(cursor.getColumnIndexOrThrow("word"))
            val definition = cursor.getString(cursor.getColumnIndexOrThrow("definition"))
            words.add(Word(word, definition))
        } while (cursor.moveToNext())
    }
    cursor.close()

    return words
}

fun fetchAllWordsWithRawQuery(db: SQLiteDatabase): List<Word> {
    val words = mutableListOf<Word>()
    val rawQuery = "SELECT word, definition FROM WORD_LIST_TABLE"
    val cursor = db.rawQuery(rawQuery, null)

    if (cursor.moveToFirst()) {
        do {
            val word = cursor.getString(cursor.getColumnIndexOrThrow("word"))
            val definition = cursor.getString(cursor.getColumnIndexOrThrow("definition"))
            words.add(Word(word, definition))
        } while (cursor.moveToNext())
    }
    cursor.close()
    return words
}