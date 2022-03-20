package com.sriyank.bankapp.DB

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class Database (context: Context?) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, 1) {


    override fun onCreate(db: SQLiteDatabase) {

        db.execSQL("create table $TABLE_NAME (ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                "NAME VARCHAR,EMAIL VARCHAR,MOBILE BIGINT,IFSC_CODE BIGINT UNIQUE," +
                "ACCOUNT_NO BIGINT UNIQUE, BANK_BALANCE INTEGER NOT NULL)")

        db.execSQL("insert into $TABLE_NAME values(1,'Ahmed Hosny'," +
                "'ahmedhosny6688@gmail.com'," +
                "01279827842,17356,159753648275,150000)")
        db.execSQL("insert into $TABLE_NAME values(2,'Taha Talaat'," +
                "'tahatalaat112@gmail.com'," +
                "01004598632,25864,115975346827,150000)")
        db.execSQL("insert into $TABLE_NAME values(3,'Ahmed Fawzy'," +
                "'ahmedfawzy@gmail.com'," +
                "01159753648,65482,175385248275,10000)")
        db.execSQL("insert into $TABLE_NAME values(4,'Khaled Elgamel'," +
                "'KhaledElgamel@gmail.com'," +
                "0124567349,75368,195975395378,7000)")
        db.execSQL("insert into $TABLE_NAME values(5,'Esmael Mossad'," +
                "'EsmaelMossad@gmail.com'," +
                "01014632781,75864,145675348275,50000)")
        db.execSQL("insert into $TABLE_NAME values(6,'Ahmed Taha'," +
                 "'AhmedTaha@gmail.com'," +
                 "01117568423,85234,125945646321,10000)")
        db.execSQL("insert into $TABLE_NAME values(7,'Abdulrahman Ahmed'," +
                 "'AbdulrahmanAhmed@gmail.com'," +
                 "01129854632,45673,196857346827,19000)")
        db.execSQL("insert into $TABLE_NAME values(8,'Mayada Mohamed'," +
                "'MayadaMohamed@gmail.com'," +
                "01259753648,67482,135385248275,16000)")
        db.execSQL("insert into $TABLE_NAME values(9,'Aya Osama'," +
               "'AyaOsama@gmail.com'," +
               "01004598632,23864,185975346827,12000)")
        db.execSQL("insert into $TABLE_NAME values(10,'Ahmed Lotfi'," +
               "'AhmedLotfi@gmail.com'," +
               "01568743648,28324,139654248275,14000)")
        db.execSQL("insert into $TABLE_NAME values(11,'Ahmed Fathi'," +
                "'AhmedFathi@gmail.com'," +
                "01225687421,65432,195975655378,9000)")
        db.execSQL("insert into $TABLE_NAME values(12,'Ebrahim Eltori'," +
                "'EbrahimEltori@gmail.com'," +
                "01514632781,17368,165475348275,5000)")
        db.execSQL("insert into $TABLE_NAME values(13,'Abdelwahab Mohamed'," +
                "'AbdelwahabMohamed@gmail.com'," +
                "01127568423,85643,115975646321,8000)")




    }

    //methods of SQLiteOpenHelper class
    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {


        db.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }
    val allData: Cursor
        get() {
            val db = this.writableDatabase
            return db.rawQuery("select * from $TABLE_NAME", null)
        }

    fun deleteData(account: String): Boolean {
        val db = this.writableDatabase
        val cursor = db.rawQuery("select * from user_table where ACCOUNT_NO=?", arrayOf(account))
        if (cursor.count > 0) {
            val result = db.delete(TABLE_NAME, "ACCOUNT_NO = ?", arrayOf(account))
            return result != -1
        } else {
            return false
        }

    }

    fun updateBankBalance(accountNo: String, amount: Int): Boolean {
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(COL_7, amount)
        val cursor = db.rawQuery(
            "select * from $TABLE_NAME where ACCOUNT_NO=?",
            arrayOf(accountNo.toString())
        )
        if (cursor.count > 0) {
            val result = db.update(
                TABLE_NAME,
                contentValues,
                "ACCOUNT_NO = ?",
                arrayOf(accountNo.toString())
            )
            return result != -1
        } else {
            return false
        }

    }

    companion object {
        const val DATABASE_NAME = "User.db"
        const val TABLE_NAME = "user_table"
        const val COL_7 = "BANK_BALANCE"
    }
}
