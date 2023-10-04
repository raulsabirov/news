package com.example.news.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.news.databinding.ItemArticleListBinding
import com.example.news.databinding.ItemHeadListBinding
import com.example.news.models.Article

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
    ListAdapter<String, RecyclerView.ViewHolder>(HeaderDiffCallback()) {


    val headerList = mutableListOf("HEADER")

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return HeaderViewHolder(
            ItemHeadListBinding
                .inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        (holder as HeaderViewHolder).apply {
            itemBinding.tvHeader.text = headerList[position]
        }
    }


    override fun getItemCount(): Int {
        return headerList.size
    }


    fun setItems(items: List<String>) {
        headerList.clear()
        headerList.addAll(items)
        notifyDataSetChanged()
    }

    inner class HeaderViewHolder(var itemBinding: ItemHeadListBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
    }

}