package com.sriyank.bankapp.Adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.sriyank.bankapp.Model.TransactionModel
import com.sriyank.bankapp.R
import java.util.ArrayList

class TransactionListAdapter(list: ArrayList<TransactionModel>) : RecyclerView.Adapter<TransactionListAdapter.ViewHolder>() {
    private val TransactionModelArrayList: ArrayList<TransactionModel> = list

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var fromName: TextView
        var toName: TextView
        var amountTransferred: TextView
        var cardView: CardView
        var toUserInfo: LinearLayout

        init {
            fromName = itemView.findViewById(R.id.txt_from_name)
            toName = itemView.findViewById(R.id.txt_to_name)
            amountTransferred = itemView.findViewById(R.id.txt_amount)
            cardView = itemView.findViewById(R.id.transaction_card_view)
            toUserInfo = itemView.findViewById(R.id.to_user_info)
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view: View = LayoutInflater.from(viewGroup.context).
        inflate(R.layout.transaction_view, viewGroup, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val transactionModel = TransactionModelArrayList[position]

        viewHolder.fromName.text = transactionModel.from_user_name
        viewHolder.toName.text = transactionModel.to_user_name
        viewHolder.amountTransferred.text = String.format("%d",
            transactionModel.money_transferred)
        if (transactionModel.status == 1) {
            viewHolder.cardView.setCardBackgroundColor(Color
                .argb(100, 60, 179, 113))
        } else {
            viewHolder.cardView.setCardBackgroundColor(Color
                .argb(100, 239, 100, 100))
        }
    }

    override fun getItemCount(): Int {
        return TransactionModelArrayList.size
    }
}
