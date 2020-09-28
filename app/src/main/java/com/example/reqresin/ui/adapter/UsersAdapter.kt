package com.example.reqresin.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.reqresin.R
import com.example.reqresin.data.repository.record.UserRecord
import com.squareup.picasso.Picasso

class UsersAdapter : RecyclerView.Adapter<UsersAdapter.UsersViewHolder>() {
    var items: MutableList<UserRecord> = mutableListOf()
        set(value) {
            items.clear()
            items.addAll(value)
            notifyDataSetChanged()
        }

    private var callback: (UserRecord) -> Unit = { }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UsersViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.user_item, parent, false)
        return UsersAdapter.UsersViewHolder(itemView)
    }

    override fun getItemCount(): Int =
        items.size

    fun setCallback(callback: (UserRecord) -> Unit) {
        this.callback = callback
    }

    override fun onBindViewHolder(holder: UsersViewHolder, position: Int) {
        with(items[position]) {
            holder.apply {
                Picasso.with(ivAvatar.context)
                    .load(avatar)
                    .placeholder(R.drawable.avatar)
                    .error(R.drawable.avatar)
                    .into(ivAvatar)
                tvFirstName.text = firstName
                tvLastName.text = lastName
                llUser.setOnClickListener {
                    callback(items[position])
                }
            }
        }
    }

    class UsersViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val llUser: LinearLayout = itemView.findViewById(R.id.ll_user)
        val ivAvatar: ImageView = itemView.findViewById(R.id.iv_avatar)
        val tvFirstName: TextView = itemView.findViewById(R.id.tv_first_name)
        val tvLastName: TextView = itemView.findViewById(R.id.tv_last_name)
    }

}