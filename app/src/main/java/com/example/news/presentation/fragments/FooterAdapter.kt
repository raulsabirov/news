/*
package com.example.news.presentation.fragments

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.example.news.databinding.ItemFooterListBinding

class FooterAdapter() :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    val list = mutableListOf<String>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return FooterViewHolder(
            ItemFooterListBinding
                .inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        (holder as FooterViewHolder).apply {
            itemBinding.tvFooter.text = list[position]
            itemBinding.tvFooter.isVisible
        }
    }


    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }


    fun setItems(items: List<String>) {
        list.clear()
        list.addAll(items)
        notifyDataSetChanged()
    }

    inner class FooterViewHolder(var itemBinding: ItemFooterListBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
    }

}*/
