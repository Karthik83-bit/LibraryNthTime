package com.example.lazylist

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.lazylist.data.Weather

class RVAdapter(val list:ArrayList<Weather>): RecyclerView.Adapter<RVAdapter.ViewHolder>() {

    class ViewHolder(view:View): RecyclerView.ViewHolder(view) {
        public val viewMain:TextView
        public val desc:TextView
        lateinit var imageView:ImageView

        init {
            viewMain=view.findViewById(R.id.main)
            desc=view.findViewById(R.id.desc)
            imageView=view.findViewById(R.id.image)
        }

    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val holderView=LayoutInflater.from(parent.context).inflate(R.layout.list_item,parent,false)
        Log.d("msg","created")
        return ViewHolder(holderView)
    }



    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currItem=list[position]
        holder.desc.text=currItem.description
        holder.viewMain.text=currItem.main
        Glide.with(holder.imageView).load(currItem.icon).into(holder.imageView)

    }

    }

   