package com.example.internalstorage

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper



class UserDbHelper (context:Context) : SQLiteOpenHelper(context,DATABASE_NAME, null,DATABASE_VERSION) {

    companion object{
        public val DATABASE_VERSION = 1
        public val DATABASE_NAME = "users.db"
        public val TABLE_NAME = "users"
        public val COLUMN_USER_ID = "id"
        public val COLUMN_NAME = "name"
        public val COLUMN_DATE = "date"
        public val COLUMN_EMAIL = "email"
        public val COLUMN_CLASSE = "classe"



        public val SQL_CREATE_ENTRIES =
            "CREATE TABLE " + TABLE_NAME + " (" +
                    COLUMN_USER_ID + " TEXT PRIMARY KEY AUTOINCREMENT," +
                    COLUMN_NAME + " TEXT," +
                    COLUMN_EMAIL + " TEXT," +
                    COLUMN_CLASSE + " TEXT," +
                    COLUMN_DATE + " TEXT)"

        public val SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS $TABLE_NAME"

    }
    public val db = writableDatabase

    override fun onCreate(p0: SQLiteDatabase?) {
        db.execSQL(SQL_CREATE_ENTRIES)
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        db.execSQL(SQL_DELETE_ENTRIES)
        onCreate(db)
    }



}