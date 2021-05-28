package com.example.algoriasearchproject

import android.content.Context
import android.view.ViewGroup
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class adapter(var list : ArrayList<Apt>):RecyclerView.Adapter<RecyclerView.ViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        var view  = View.inflate(parent.context , R.layout.item,null)

        return Viewholder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        holder as Viewholder
                holder.aptName.setText(list.get(position).aptName)
        holder.aptAddress.setText(list.get(position).aptAddress)

    }

    fun resetting(){
        notifyDataSetChanged()
    }
    inner class Viewholder(view : View) : RecyclerView.ViewHolder(view){
             var aptName : TextView = view.findViewById(R.id.aptName)
         var aptAddress : TextView = view.findViewById(R.id.aptAddress)

    }
}