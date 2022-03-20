package com.sriyank.bankapp.UI

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sriyank.bankapp.Adapter.TransactionListAdapter
import com.sriyank.bankapp.DB.TransactionDB
import com.sriyank.bankapp.Model.TransactionModel
import com.sriyank.bankapp.R

class TransactionDetail : AppCompatActivity() {

    private lateinit var transaction_detail_rv: RecyclerView
    var arrayList = ArrayList<TransactionModel>()
    var myDb: TransactionDB? = null
    lateinit var emptyList: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_transaction_detail)

        supportActionBar?.hide()

        mappingViews()
        displayDatabaseInfo()
        setUpList()

    }

    private fun mappingViews() {
        transaction_detail_rv = findViewById(R.id.transaction_detail_rv)
        emptyList = findViewById(R.id.empty_text)
        myDb = TransactionDB(this)
    }

    private fun setUpList() {
        val layoutManager = LinearLayoutManager(this)
        transaction_detail_rv.layoutManager = layoutManager

        val adapter = TransactionListAdapter(arrayList)
        transaction_detail_rv.adapter = adapter
    }


    fun displayDatabaseInfo() {

        val res = myDb!!.allData

        while (res.moveToNext()) {
            val transactionModelList = TransactionModel(res.getString(1),
                res.getString(2), res.getInt(3), res.getInt(4))
            arrayList.add(transactionModelList)
        }

        if (arrayList.isEmpty()) {
            emptyList.visibility = View.VISIBLE
        } else {
            emptyList.visibility = View.GONE
        }
    }


}