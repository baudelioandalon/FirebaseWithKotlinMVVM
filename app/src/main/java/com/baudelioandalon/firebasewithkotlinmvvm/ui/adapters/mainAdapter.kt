package com.baudelioandalon.firebasewithkotlinmvvm.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.baudelioandalon.firebasewithkotlinmvvm.R
import com.baudelioandalon.firebasewithkotlinmvvm.model.User
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_row.view.*

class mainAdapter(private val context: Context) : RecyclerView.Adapter<mainAdapter.MainViewHolder>(){

    private var datalist = mutableListOf<User>()
    fun setListData(data: MutableList<User>){
        datalist = data
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        return MainViewHolder(LayoutInflater.from(context).inflate(R.layout.item_row, parent, false))
    }
    override fun getItemCount(): Int {
        return datalist.size
    }
    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        val user: User = datalist[position]
        holder.bindView(user)
    }
    inner class MainViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        fun bindView(user: User){
            Glide.with(context).load(user.imageUri)
                .placeholder(R.drawable.allen_account)
                .into(itemView.text_img)
            itemView.text_title.text = user.title
            itemView.text_description.text = user.description
        }
    }
}