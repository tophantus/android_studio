package com.example.sqlitedemo

import android.database.sqlite.SQLiteDatabase
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun NavHost(
    db: SQLiteDatabase,
    dbHelper: DatabaseHelper,
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(navController, startDestination = "wordList") {
        composable("wordList") {
            WordListScreen(db, navController)
        }
        composable("addWord") {
            AddWordScreen(db, dbHelper, navController)
        }
    }
}