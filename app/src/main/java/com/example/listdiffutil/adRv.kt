package com.example.listdiffutil

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.listdiffutil.databinding.LstBinding

class adRv:RecyclerView.Adapter<adRv.ContentViews>() {
//lista
    //resp
    class ContentViews(var layout:LstBinding):RecyclerView.ViewHolder(layout.root)

    var callback=object : DiffUtil.ItemCallback<String>(){
        override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
            return true
        }

        override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {

            return oldItem==newItem

        }

    }

    var differ=AsyncListDiffer(this,callback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContentViews {

        val views=LstBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ContentViews(views)

    }

    override fun getItemCount()=differ.currentList.size

    override fun onBindViewHolder(holder: ContentViews, position: Int) {
        var currentItem=differ.currentList.get(position)

        holder.layout.apply {
            txtTitulo.text=currentItem
        }



    }



}