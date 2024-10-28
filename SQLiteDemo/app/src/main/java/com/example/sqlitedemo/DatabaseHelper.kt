package com.example.sqlitedemo

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, "words.db", null, 1) {
    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(
            "CREATE TABLE WORD_LIST_TABLE (" +
                    "_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "word TEXT, " +
                    "definition TEXT)"
        )
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS WORD_LIST_TABLE")
        onCreate(db)
    }

    fun insertWord(db: SQLiteDatabase, word: String, definition: String) {
        val values = ContentValues().apply {
            put("word", word)
            put("definition", definition)
        }
        db.insert("WORD_LIST_TABLE", null, values)
    }
}