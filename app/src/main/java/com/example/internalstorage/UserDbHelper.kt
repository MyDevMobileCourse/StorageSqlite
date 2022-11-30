package com.example.internalstorage

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper



class UserDbHelper (context:Context) : SQLiteOpenHelper(context,DATABASE_NAME, null,DATABASE_VERSION) {
    public val db = writableDatabase

    companion object{
        public val DATABASE_VERSION = 1
        public val DATABASE_NAME = "dbusers.db"



        public val SQL_CREATE_ENTRIES =
            "CREATE TABLE " + DBContract.UserEntry.TABLE_NAME + " (" +
                    DBContract.UserEntry.COLUMN_USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    DBContract.UserEntry.COLUMN_NAME + " TEXT," +
                    DBContract.UserEntry.COLUMN_EMAIL + " TEXT," +
                    DBContract.UserEntry.COLUMN_CLASSE + " TEXT," +
                    DBContract.UserEntry.COLUMN_DATE + " TEXT)"

        public val SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS $DBContract.UserEntry.TABLE_NAME"

    }

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(SQL_CREATE_ENTRIES)
    }

    override fun onUpgrade(db: SQLiteDatabase, p1: Int, p2: Int) {
        db.execSQL(SQL_DELETE_ENTRIES)
        onCreate(db)
    }



}