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

    fun readUsers(): MutableList<UserModel> {
        val users: MutableList<UserModel> = ArrayList<UserModel>()
        val db = readableDatabase
        val projection = arrayOf(
            DBContract.UserEntry.COLUMN_USER_ID,
            DBContract.UserEntry.COLUMN_NAME,
            DBContract.UserEntry.COLUMN_EMAIL,
            DBContract.UserEntry.COLUMN_CLASSE,
            DBContract.UserEntry.COLUMN_DATE
        )
        val cursor = db.query(
            DBContract.UserEntry.TABLE_NAME,
            projection,
            null,
            null,
            null,
            null,
            null
        )
        with(cursor) {
            while (moveToNext()) {
                val id = getInt(getColumnIndexOrThrow(DBContract.UserEntry.COLUMN_USER_ID))
                val name = getString(getColumnIndexOrThrow(DBContract.UserEntry.COLUMN_NAME))
                val email = getString(getColumnIndexOrThrow(DBContract.UserEntry.COLUMN_EMAIL))
                val classe = getString(getColumnIndexOrThrow(DBContract.UserEntry.COLUMN_CLASSE))
                val date = getString(getColumnIndexOrThrow(DBContract.UserEntry.COLUMN_DATE))
                users.add(UserModel(id, name, email, classe, date))
            }
        }
        println("users: $users")
        println("users size: ${users.size}")
        return users

    }

    fun searchUsers(search: String): MutableList<UserModel> {
        // search by name or email
        val users: MutableList<UserModel> = ArrayList<UserModel>()
        val db = readableDatabase
        val projection = arrayOf(
            DBContract.UserEntry.COLUMN_USER_ID,
            DBContract.UserEntry.COLUMN_NAME,
            DBContract.UserEntry.COLUMN_EMAIL,
            DBContract.UserEntry.COLUMN_CLASSE,
            DBContract.UserEntry.COLUMN_DATE
        )
        val selection = "${DBContract.UserEntry.COLUMN_NAME} LIKE ? OR ${DBContract.UserEntry.COLUMN_EMAIL} LIKE ?"
        val selectionArgs = arrayOf("%$search%", "%$search%")
        val cursor = db.query(
            DBContract.UserEntry.TABLE_NAME,
            projection,
            selection,
            selectionArgs,
            null,
            null,
            null
        )
        with(cursor) {
            while (moveToNext()) {
                val id = getInt(getColumnIndexOrThrow(DBContract.UserEntry.COLUMN_USER_ID))
                val name = getString(getColumnIndexOrThrow(DBContract.UserEntry.COLUMN_NAME))
                val email = getString(getColumnIndexOrThrow(DBContract.UserEntry.COLUMN_EMAIL))
                val classe = getString(getColumnIndexOrThrow(DBContract.UserEntry.COLUMN_CLASSE))
                val date = getString(getColumnIndexOrThrow(DBContract.UserEntry.COLUMN_DATE))
                users.add(UserModel(id, name, email, classe, date))
            }
        }
        return users

    }

    fun deleteUser(userId: Int) {
        val db = writableDatabase
        val selection = "${DBContract.UserEntry.COLUMN_USER_ID} = ?"
        val selectionArgs = arrayOf("$userId")
        db.delete(DBContract.UserEntry.TABLE_NAME, selection, selectionArgs)
    }

    fun getUser(userId: Int): UserModel {
        val db = readableDatabase
        val projection = arrayOf(
            DBContract.UserEntry.COLUMN_USER_ID,
            DBContract.UserEntry.COLUMN_NAME,
            DBContract.UserEntry.COLUMN_EMAIL,
            DBContract.UserEntry.COLUMN_CLASSE,
            DBContract.UserEntry.COLUMN_DATE
        )
        val selection = "${DBContract.UserEntry.COLUMN_USER_ID} = ?"
        val selectionArgs = arrayOf("$userId")
        val cursor = db.query(
            DBContract.UserEntry.TABLE_NAME,
            projection,
            selection,
            selectionArgs,
            null,
            null,
            null
        )
        var user : UserModel? = null
        with(cursor) {
            while (moveToNext()) {
                val id = getInt(getColumnIndexOrThrow(DBContract.UserEntry.COLUMN_USER_ID))
                val name = getString(getColumnIndexOrThrow(DBContract.UserEntry.COLUMN_NAME))
                val email = getString(getColumnIndexOrThrow(DBContract.UserEntry.COLUMN_EMAIL))
                val classe = getString(getColumnIndexOrThrow(DBContract.UserEntry.COLUMN_CLASSE))
                val date = getString(getColumnIndexOrThrow(DBContract.UserEntry.COLUMN_DATE))
                user = UserModel(id, name, email, classe, date)
            }
        }
        return user!!
    }


}