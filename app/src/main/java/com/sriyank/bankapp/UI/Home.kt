package com.sriyank.bankapp.UI


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.sriyank.bankapp.DB.Database
import com.sriyank.bankapp.R
import kotlinx.android.synthetic.main.activity_home.*

class Home : AppCompatActivity() {

    var myDb: Database? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        supportActionBar?.hide()

        myDb = Database(this)

        viewAllUser()
        viewAllTransaction()


    }

    private fun viewAllTransaction() {
        AllTransfer.setOnClickListener {
            val intent = Intent(this, TransactionDetail::class.java)
            startActivity(intent)
        }
    }

    private fun viewAllUser() {

        allUser.setOnClickListener {
            val intent = Intent(this, AllUser::class.java)
            startActivity(intent)

        }
    }


}