package com.example.news.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.news.databinding.ItemArticleListBinding
import com.example.news.models.Article


// diffutil payload
//https://medium.com/@domen.lanisnik/efficiently-updating-recyclerview-items-using-payloads-1305f65f3068

//https://russianblogs.com/article/15692380584/

//https://ziginsider.github.io/RecyclerView/#adapter

class ArticlesAdapter() :
    ListAdapter<Article, ArticlesAdapter.ArticleViewHolder>(ArticleDiffCallback()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        return ArticleViewHolder(
            ItemArticleListBinding
                .inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }


    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    /*    override fun onBindViewHolder(
            holder: ArticleViewHolder,
            position: Int,
            payloads: MutableList<Any>
        ) {
            when (val latestPayload = payloads.lastOrNull()) {
                ArticleChangePayload.Title -> {
                }

                ArticleChangePayload.Description -> {

                }

                else ->
                    holder.bind(getItem(position))
            }
        }*/

    inner class ArticleViewHolder(var itemBinding: ItemArticleListBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(article: Article) {

            itemBinding.tvTitle.text = article.title
            itemBinding.tvDescription.text = article.description
        }
    }


    private class ArticleDiffCallback : DiffUtil.ItemCallback<Article>() {

        override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem.source == newItem.source
        }

        override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem == newItem
        }

        override fun getChangePayload(oldItem: Article, newItem: Article): Any? {
            return when {
                oldItem.title != newItem.title ->
                    ArticleChangePayload.Title

                oldItem.description != newItem.description ->
                    ArticleChangePayload.Description

                else -> super.getChangePayload(oldItem, newItem)
            }
        }
    }


    /* fun addData(data: Pair<Boolean, MutableList<DocumentEntity>>) {
         val margetArray = items + data.second
         val diffCallback = DiffUtilsCallback(items, margetArray)
         val diffResult = DiffUtil.calculateDiff(diffCallback)
         with(data) {
             if (first) {
                 items.clear()
                 */
    /**
     * Удялем все выбранные элементы если page == 0
     *//*
                selectDeselectAll()
                items.addAll(second)
            } else {
                items.addAll(second)
            }
        }
        diffResult.dispatchUpdatesTo(this)

        */
    /**
     * Если поставлен чекбокс выбрать все
     * Добавляем к выбранным все элементы пришетшие с бэка
     *//*
        if (isCheckedAll) {
            selectDeselectAll(true)
        }
    }*/


    enum class ArticleChangePayload {
        Title,
        Description
    }

}