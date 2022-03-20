package com.sriyank.bankapp.UI

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.sriyank.bankapp.Model.ViewUserModel
import com.sriyank.bankapp.R
import kotlinx.android.synthetic.main.activity_user_details.*
import kotlinx.android.synthetic.main.transfer_dialog.*

class UserDetails : AppCompatActivity() {

    lateinit var user_name: TextView
    lateinit var user_email: TextView
    lateinit var mobile_data: TextView
    lateinit var account_data: TextView
    lateinit var ifsc_code: TextView
    lateinit var bank_balance: TextView
    lateinit var transfer: Button
    lateinit var toolbar: androidx.appcompat.widget.Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_details)

        supportActionBar?.hide()

        mappingViews()
        setUpData()
        transferMoney()
    }


    private fun transferMoney() {
        transfer.setOnClickListener {
            val builder = AlertDialog.Builder(this)
            val inflater = LayoutInflater.from(this)
            val dialogLayout = inflater.inflate(R.layout.transfer_dialog, null)
            val editText = dialogLayout.findViewById<EditText>(R.id.et_amount)

            with(builder) {
                setTitle("Amount")
                setPositiveButton("OK") { dialog, which ->
                    val amount = editText.text.toString()

                    // Checking whether amount entered is correct or not
                    val currentBalance: Int = bank_balance.getText().toString().toInt()
                    if (amount.isEmpty()) {
                        Toast.makeText(this@UserDetails,
                            "Enter amount", Toast.LENGTH_SHORT).show()

                    } else if (Integer.parseInt(amount) > currentBalance) {
                        Toast.makeText(this@UserDetails,
                            "Amount exceeded Available balance",
                            Toast.LENGTH_SHORT).show()
                    } else {
                        val intent = Intent(this@UserDetails,
                            SelectUser::class.java)
                        intent.putExtra("FROM_USER_ACCOUNT_NO",
                            account_data.getText())
                        // PRIMARY_KEY

                        intent.putExtra("FROM_USER_NAME", user_name.getText())
                        intent.putExtra("FROM_USER_ACCOUNT_BALANCE",
                            bank_balance.getText())
                        intent.putExtra("TRANSFER_AMOUNT", amount)
                        startActivity(intent)
                        finish()
                    }
                }
                setNegativeButton("Cancel") { dialog, which ->
                    Log.d("UserDetail", "negative button clicked")
                }
                setView(dialogLayout)
                show()
            }
        }
    }

    private fun mappingViews() {
        user_name = findViewById(R.id.user_name)
        user_email = findViewById(R.id.user_email)
        mobile_data = findViewById(R.id.mobile_data)
        account_data = findViewById(R.id.account_no_data)
        ifsc_code = findViewById(R.id.ifsc_code)
        bank_balance = findViewById(R.id.bank_balance_data)
        transfer = findViewById(R.id.transfer)
    }

    private fun setUpData() {
        val user = intent.getSerializableExtra("user") as ViewUserModel
        user_name.setText(user.user_name)
        user_email.setText(user.user_email)
        mobile_data.setText(user.mobile)
        account_data.setText(user.bank_account)
        ifsc_code.setText(user.ifsc_code)
        bank_balance.setText(user.balance)
    }
}