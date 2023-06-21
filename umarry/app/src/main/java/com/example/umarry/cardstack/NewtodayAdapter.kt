package com.example.umarry.cardstack

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.umarry.R

class NewtodayAdapter(val context : Context, val items : List<String>) :
    RecyclerView.Adapter<NewtodayAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewtodayAdapter.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view :View =  inflater.inflate(R.layout.itemcard, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder : NewtodayAdapter.ViewHolder, position: Int) {
        holder.binding(items[position]) //???
    }

    override fun getItemCount(): Int {
        return items.size
    }

    inner class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        fun binding(data : String) {

        }
    }

}