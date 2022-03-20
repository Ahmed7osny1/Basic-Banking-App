package com.sriyank.bankapp.Model

import java.io.Serializable

data class ViewUserModel(val user_name: String,
                         val user_email:String,
                         val mobile:String,
                         val ifsc_code:String,
                         val bank_account: String,
                         val balance:String):Serializable
