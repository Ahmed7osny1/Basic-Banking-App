package com.sriyank.bankapp.DB

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class TransactionDB (context: Context?) : SQLiteOpenHelper(context,
    DATABASE_NAME, null, DATABASE_VERSION) {
    override fun onCreate(db: SQLiteDatabase) {

        db.execSQL("create table $TABLE_NAME " +
                "(ID INTEGER PRIMARY KEY AUTOINCREMENT,from_name VARCHAR," +
                "to_name VARCHAR,amount INTEGER,status INTEGER)")
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        if (oldVersion != newVersion) {
            db.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
            onCreate(db)
        }
    }

    fun insertTransferData(fromName: String?, toName: String?, amount: Int?,
                           status: Int): Boolean {
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(COLUMN_FROM_NAME, fromName)
        contentValues.put(COLUMN_TO_NAME, toName)
        contentValues.put(COLUMN_AMOUNT, amount)
        contentValues.put(COLUMN_STATUS, status)
        val result = db.insert(TABLE_NAME, null, contentValues)
        return result != -1L
    }


    val allData: Cursor
        get() {
            val db = this.writableDatabase
            return db.rawQuery("select * from $TABLE_NAME", null)
        }

    companion object {


        private const val DATABASE_NAME = "transactions.db"
        const val TABLE_NAME = "transactions_table"
        const val COLUMN_FROM_NAME = "from_name"
        const val COLUMN_TO_NAME = "to_name"
        const val COLUMN_AMOUNT = "amount"
        const val COLUMN_STATUS = "status"



        private const val DATABASE_VERSION = 2
    }
}