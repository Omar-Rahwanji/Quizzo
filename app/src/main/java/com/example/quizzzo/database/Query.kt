package com.example.quizzzo.database

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class Query(context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_VERSION = 1
        private const val DATABASE_NAME = "quizzzo.db"

    }

    override fun onCreate(db: SQLiteDatabase?) {

    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

    }

    fun insert(TblName: String, contentValues: ContentValues): Long {
        val db = this.writableDatabase
        val success = db.insert(
            TblName,
            null,
            contentValues
        )
        db.close()
        return success
    }

//    fun update(TblName: String, contentValues: ContentValues): Long {
//        val db = this.writableDatabase
//        val success = db.update()
//        db.close()
//        return success
//    }
//
//    fun delete(TblName: String, contentValues: ContentValues): Long {
//        val db = this.writableDatabase
//        //val success = db.delete()
//        //db.close()
//        //return success
//    }
//
//    fun select(TblName: String, contentValues: ContentValues): Long {
//        val db = this.writableDatabase
//        val success = db
//        db.close()
//        return success
//    }
}