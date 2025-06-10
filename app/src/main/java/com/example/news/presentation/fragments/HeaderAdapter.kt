/*
package com.example.news.presentation.fragments

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.news.databinding.ItemHeadListBinding

private class HeaderDiffCallback : DiffUtil.ItemCallback<String>() {

    override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
        return oldItem == newItem
    }
}

class HeaderAdapter() :
// RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    ListAdapter<String, HeaderAdapter.HeaderViewHolder>(HeaderDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HeaderViewHolder {
        return HeaderViewHolder(
            ItemHeadListBinding
                .inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: HeaderViewHolder, position: Int) {
        holder.itemBinding.tvHeader.text = getItem(position)
    }


    inner class HeaderViewHolder(var itemBinding: ItemHeadListBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
    }
}*/
