package com.nine.ninenews.data.view

import android.content.Intent
import com.nine.ninenews.R
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.nine.ninenews.data.model.Asset
import com.nine.ninenews.data.model.AssetRelatedImage
import com.squareup.picasso.Picasso

class NewsAdapter : ListAdapter<Asset, NewsAdapter.NewsViewHolder>(NewsDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_news, parent, false)
        return NewsViewHolder(view)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val newsArticle = getItem(position)
        holder.bind(newsArticle)
    }

    inner class NewsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val headlineTextView: TextView = itemView.findViewById(R.id.headlineTextView)
        private val abstractTextView: TextView = itemView.findViewById(R.id.abstractTextView)
        private val byLineTextView: TextView = itemView.findViewById(R.id.byLineTextView)
        private val imageView: ImageView = itemView.findViewById(R.id.imageViewNews)

        fun bind(newsArticle: Asset) {
            headlineTextView.text = newsArticle.headline
            abstractTextView.text = newsArticle.theAbstract
            byLineTextView.text = newsArticle.byLine


            if (newsArticle.relatedImages.isNotEmpty()) {
                // Find the smallest image in the relatedImages list
                val smallestImage = findSmallestImage(newsArticle.relatedImages)
                if (smallestImage != null)
                    Picasso.get().load(smallestImage.url).into(imageView)
            }

            // Set an item click listener here to open the article in a WebView when clicked
            itemView.setOnClickListener {

                // Extract the URL from the newsArticle model
                val url = newsArticle.url

                // Create an Intent to open a WebViewActivity with the URL as data
                val intent = Intent(itemView.context, WebViewActivity::class.java)
                intent.putExtra("url", url)
                itemView.context.startActivity(intent)
            }
        }
    }

    // Function to find the smallest image in the list
    private fun findSmallestImage(images: List<AssetRelatedImage>): AssetRelatedImage? {
        var smallestImage: AssetRelatedImage? = null
        var smallestArea = Int.MAX_VALUE

        for (image in images) {
            val area = image.width * image.height
            if (area < smallestArea) {
                smallestArea = area.toInt()
                smallestImage = image
            }
        }

        return smallestImage
    }
}

class NewsDiffCallback : DiffUtil.ItemCallback<Asset>() {
    override fun areItemsTheSame(oldItem: Asset, newItem: Asset): Boolean {
        return oldItem.headline == newItem.headline
    }

    override fun areContentsTheSame(oldItem: Asset, newItem: Asset): Boolean {
        return oldItem == newItem
    }
}