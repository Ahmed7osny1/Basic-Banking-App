package com.sriyank.bankapp.UI

import android.content.ContentValues
import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sriyank.bankapp.Adapter.TranferToUserListAdapter
import com.sriyank.bankapp.DB.Database
import com.sriyank.bankapp.DB.TransactionDB
import com.sriyank.bankapp.Model.ViewUserModel
import com.sriyank.bankapp.R

class SelectUser : AppCompatActivity() , TranferToUserListAdapter.OnUserListener {

    private lateinit var select_user_rv: RecyclerView
    var arrayList = ArrayList<ViewUserModel>()
    var myDb: Database? = null


    var fromUserAccountNo: String? = null
    var toUserAccountNo: String? = null
    var toUserAccountBalance: String? = null
    var fromUserAccountName: String? = null
    var fromUserAccountBalance: String? = null
    var transferAmount: String? = null
    var toUserAccountName: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select_user)

        supportActionBar?.hide()

        myDb = Database(this)
        select_user_rv = findViewById(R.id.select_user_rv)

        getIntentFrom()
        displayDatabaseInfo()
        setUpList()
    }

    private fun getIntentFrom() {
        // Get Intent
        val bundle = intent.extras
        if (bundle != null) {
            fromUserAccountName = bundle.getString("FROM_USER_NAME")
            fromUserAccountNo = bundle.getString("FROM_USER_ACCOUNT_NO")
            fromUserAccountBalance = bundle.getString("FROM_USER_ACCOUNT_BALANCE")
            transferAmount = bundle.getString("TRANSFER_AMOUNT")
        }
    }

    private fun displayDatabaseInfo() {
        val res = myDb!!.allData

        while (res.moveToNext()) {
            val viewUserModelList = ViewUserModel(res.getString(1),
                res.getString(2), res.getString(3), res.getString(4),
                res.getString(5), res.getString(6))
            arrayList.add(viewUserModelList)
        }
    }

    private fun setUpList() {
        val layoutManager = LinearLayoutManager(this)
        select_user_rv.layoutManager = layoutManager

        val adapter = TranferToUserListAdapter(this, this, arrayList)
        select_user_rv.adapter = adapter;
    }


    private fun calculateAmount() {
        val currentAmount = fromUserAccountBalance
        val transferAmountInt = transferAmount
        val remainingAmount = Integer.parseInt(currentAmount!!) -
                Integer.parseInt(transferAmountInt!!)
        val increasedAmount = Integer.parseInt(transferAmountInt.toString()) +
                Integer.parseInt(toUserAccountBalance!!)


        fromUserAccountNo?.let { Database(this).updateBankBalance(it,
            remainingAmount) }
        toUserAccountNo?.let { Database(this).updateBankBalance(it,
            increasedAmount) }
    }

    override fun onUserClick(position: Int) {


        toUserAccountNo = arrayList.get(position).bank_account
        toUserAccountName = arrayList.get(position).user_name
        toUserAccountBalance = arrayList.get(position).balance

        calculateAmount()

        TransactionDB(this).insertTransferData(fromUserAccountName,
            toUserAccountName, transferAmount?.toInt(), 1)
        Toast.makeText(this, "Transaction Successful!!",
            Toast.LENGTH_LONG).show()

        startActivity(Intent(this@SelectUser, Home::class.java))
        finish()
    }

    override fun onBackPressed() {
        val builder_exitButton = AlertDialog.Builder(this@SelectUser)
        builder_exitButton.setTitle("Do you want to cancel the transaction?").
        setCancelable(false).setPositiveButton("yes") { dialogInterface, i ->

                val dbHelper = TransactionDB(applicationContext)
                val db: SQLiteDatabase = dbHelper.getWritableDatabase()
                val values = ContentValues()
                values.put(TransactionDB.COLUMN_FROM_NAME, fromUserAccountName)
                values.put(TransactionDB.COLUMN_TO_NAME, "Transaction Cancelled")
                values.put(TransactionDB.COLUMN_STATUS, 0)
                values.put(TransactionDB.COLUMN_AMOUNT, transferAmount)
                db.insert(TransactionDB.TABLE_NAME, null, values)
                Toast.makeText(this@SelectUser, "Transaction Cancelled!",
                    Toast.LENGTH_LONG).show()
                startActivity(Intent(this@SelectUser, Home::class.java))
                finish()
            }.setNegativeButton("No", null)
        val alertExit = builder_exitButton.create()
        alertExit.show()
    }

}