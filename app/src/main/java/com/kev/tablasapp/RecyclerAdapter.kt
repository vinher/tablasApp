package com.kev.tablasapp

import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder

class RecyclerAdapter(private val data:ArrayList<String>):RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_card,parent,false)
        return ViewHolder(view)
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.textView.text = data[position]
    }
    override fun getItemCount(): Int = data.size

    class ViewHolder(view: View):RecyclerView.ViewHolder(view){
        val textView:TextView
        //val card:CardView
        init{
            textView = view.findViewById(R.id.select_result)
            //card     = view.findViewById<CardView?>(R.id.card_bakground)
            //card.setBackgroundColor(Color.parseColor("#E9D2F4"))
        }

    }

}