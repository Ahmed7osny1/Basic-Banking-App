package com.sriyank.bankapp.UI

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sriyank.bankapp.Adapter.AllUserListAdapter
import com.sriyank.bankapp.DB.Database
import com.sriyank.bankapp.Model.ViewUserModel
import com.sriyank.bankapp.R

class AllUser : AppCompatActivity() {

    private lateinit var view_user_rv: RecyclerView
    var arrayList = ArrayList<ViewUserModel>()
    var myDb: Database? = null

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_all_user)

        supportActionBar?.hide()

        mappingViews()
        displayDatabaseInfo()
        setUpList()
    }

    private fun mappingViews() {
        myDb = Database(this)
        view_user_rv = findViewById(R.id.view_user_rv)
    }

    private fun displayDatabaseInfo() {
        val res = myDb!!.allData

        while (res.moveToNext()) {
            val viewUserModelList = ViewUserModel(res.getString(1),
                res.getString(2), res.getString(3),
                res.getString(4), res.getString(5),
                res.getString(6))
            arrayList.add(viewUserModelList)
        }
    }


    private fun setUpList() {

        val layoutManager = LinearLayoutManager(this)
        view_user_rv.layoutManager = layoutManager

        val adapter = AllUserListAdapter(this, arrayList)
        view_user_rv.adapter = adapter
    }
}