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
        val card:CardView
        var colors = arrayOf("#2a9d8f","#E9D2F4","#e9c46a","#06d6a0","#7209b7","#d90429","#006d77","#ff6700","#fb6f92","#ffbf69")
        init{
            textView = view.findViewById(R.id.select_result)
            card     = view.findViewById<CardView?>(R.id.card_bakground)
            card.setBackgroundColor(Color.parseColor(colors.random()))
            card.cardElevation =  6f
            card.radius = 16f
        }

    }

}