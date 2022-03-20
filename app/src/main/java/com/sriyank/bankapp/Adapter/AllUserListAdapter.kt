package com.sriyank.bankapp.Adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.sriyank.bankapp.Model.ViewUserModel
import com.sriyank.bankapp.R
import com.sriyank.bankapp.UI.UserDetails

class AllUserListAdapter(Context: Context, private val arraylist: ArrayList<ViewUserModel>) : RecyclerView.Adapter<AllUserListAdapter.ViewHolder>() {

    var context: Context = Context
    val list: ArrayList<ViewUserModel> = arraylist

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AllUserListAdapter.ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.alluserview, parent, false)
        val viewHolder = ViewHolder(view);
        return viewHolder

    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: AllUserListAdapter.ViewHolder, position: Int) {
        val viewUserModel = arraylist[position]
        holder.passData(viewUserModel)
        holder.user_name.text = viewUserModel.user_name
        holder.balance.text = viewUserModel.balance
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var user_name: TextView
        var balance: TextView
        var currentUser: ViewUserModel? = null

        init {
            user_name = itemView.findViewById(R.id.user_name)
            balance = itemView.findViewById(R.id.bank_balance)

            itemView.setOnClickListener {
                val intent = Intent(context, UserDetails::class.java)
                intent.putExtra("user", currentUser)
                context.startActivity(intent)
            }
        }

        fun passData(viewUserModel: ViewUserModel) {
            currentUser = viewUserModel
        }
    }
}