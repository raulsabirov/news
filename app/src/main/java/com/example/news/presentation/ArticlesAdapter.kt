package com.example.news.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.news.databinding.ItemArticleListBinding
import com.example.news.models.Article


// diffutil payload
//https://medium.com/@domen.lanisnik/efficiently-updating-recyclerview-items-using-payloads-1305f65f3068

class ArticlesAdapter() :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    val articleList: ArrayList<Article> = arrayListOf()

    var callback: (() -> Unit)? = null


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return  ArticleViewHolder(
                ItemArticleListBinding
                    .inflate(LayoutInflater.from(parent.context), parent, false)
            )
    }

    override fun getItemCount(): Int {
        return articleList.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

            (holder as ArticleViewHolder).bind(articleList[position])
    }

    fun setItems(items: List<Article>, callback: (() -> Unit)? = null) {
        this.callback = callback
        articleList.clear()
        articleList.addAll(items)
        notifyDataSetChanged()
    }

    inner class ArticleViewHolder(var itemBinding: ItemArticleListBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(article: Article) {

            itemBinding.tvTitle.text = article.title
            itemBinding.tvDescription.text = article.description
        }
    }


    /*  private class ArticleDiffCallback : DiffUtil.ItemCallback<Article>() {

          override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
              return oldItem.id == newItem.id
          }

          override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
              return oldItem == newItem
          }

          override fun getChangePayload(oldItem: Article, newItem: Article): Any? {
              return when {
                  oldItem.commentsCount != newItem.commentsCount -> {
                      ArticleChangePayload.Comments(newItem.commentsCount)
                  }

                  oldItem.bookmarked != newItem.bookmarked -> {
                      ArticleChangePayload.Bookmark(newItem.bookmarked)
                  }

                  else -> super.getChangePayload(oldItem, newItem)
              }
          }
      }*/


    sealed class ArticleToUpdate {

    }

}