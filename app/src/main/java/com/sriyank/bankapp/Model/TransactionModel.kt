package com.sriyank.bankapp.Model

data class TransactionModel(var from_user_name:String,
                            var to_user_name:String,
                            var money_transferred:Int,
                            var status:Int)