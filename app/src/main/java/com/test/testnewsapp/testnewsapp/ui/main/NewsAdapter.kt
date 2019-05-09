package com.test.testnewsapp.testnewsapp.ui.main

import android.content.Context
import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import com.bumptech.glide.Glide
import com.test.testnewsapp.testnewsapp.R
import com.test.testnewsapp.testnewsapp.data.network.model.Article
import com.test.testnewsapp.testnewsapp.interfaces.IRecyclerView
import org.joda.time.DateTime

class NewsAdapter(context: Context) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val mData = arrayListOf<Article>()
    private val mInflater = LayoutInflater.from(context)
    private var recyclerViewCallback: IRecyclerView? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = mInflater.inflate(R.layout.item_news, parent, false)
        return NewsItemViewHolder(view)
    }

    override fun getItemCount(): Int = mData.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val customViewHolder = holder as NewsItemViewHolder
        val item = mData[position]

        item.urlToImage?.let {
            Glide
                .with(holder.itemView)
                .load(it)
                .into(customViewHolder.imageArticle)
        }
        customViewHolder.tvTitle.text = item.title
        customViewHolder.tvDescription.text = item.description
        customViewHolder.tvPublishedAt.text = DateTime.parse(item.publishedAt).toString("EEE, dd.MM.yyyy HH:mm a")
        customViewHolder.cardView.tag = position

        customViewHolder.cardView.setOnClickListener { view ->
            recyclerViewCallback?.let {
                val position = (view as CardView).tag as Int
                it.onRecyclerViewItemClick(position)
            }
        }
    }

    /**
     * This method is to set a callback so that we can handle the tap on recycler view item
     */
    fun setRecyclerViewCallback(callback: IRecyclerView) {
        recyclerViewCallback = callback
    }

    /**
     * This method will update the list bound with the recycler view
     */
    fun addNews(list: List<Article>, shouldClear: Boolean) {
        if (shouldClear) mData.clear()
        mData.addAll(list)
        notifyDataSetChanged()
    }

    //If we need to clear the list, we can use this method
    fun clearData() {
        mData.clear()
        notifyDataSetChanged()
    }

    //This method will return the actual item at specific position
    fun getItemAtPosition(position: Int) = mData[position]

    inner class NewsItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        @BindView(R.id.card_view)
        lateinit var cardView: CardView
        @BindView(R.id.img_article)
        lateinit var imageArticle: ImageView
        @BindView(R.id.tv_title)
        lateinit var tvTitle: TextView
        @BindView(R.id.tv_description)
        lateinit var tvDescription: TextView
        @BindView(R.id.tv_published_at)
        lateinit var tvPublishedAt: TextView

        init {
            ButterKnife.bind(this, itemView)
        }
    }
}