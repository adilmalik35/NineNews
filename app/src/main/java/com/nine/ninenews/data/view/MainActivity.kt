package com.nine.ninenews.data.view

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.nine.ninenews.R
import com.nine.ninenews.data.viewmodel.NewsViewModel
import com.nine.ninenews.data.viewmodel.NewsViewModelFactory
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: NewsViewModelFactory

    lateinit var viewModel: NewsViewModel


    private lateinit var newsAdapter: NewsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        // Initialize ViewModel
        viewModel = ViewModelProvider(this, viewModelFactory).get(NewsViewModel::class.java)

        // Initialize RecyclerView
        val recyclerView: RecyclerView = findViewById(R.id.recyclerViewNews)
        recyclerView.layoutManager = LinearLayoutManager(this)
        newsAdapter = NewsAdapter()
        recyclerView.adapter = newsAdapter

        // Attach observer
        viewModel.newsArticles.observe(this) { newsArticles ->
            // Update the RecyclerView with the fetched news articles
            newsAdapter.submitList(newsArticles)
        }

        // Initialize progressBar
        val progressBar: ProgressBar = findViewById(R.id.progressBar)
        viewModel.isLoading.observe(this) { show ->
            progressBar.visibility = if (show) View.VISIBLE else View.GONE
        }

        // Initialize textview
        val textViewEmptyState: TextView = findViewById(R.id.textViewEmptyState)
        viewModel.error.observe(this) { error ->
            textViewEmptyState.visibility =  View.VISIBLE
            textViewEmptyState.text = error
        }

        // Trigger the initial data fetch
        fetchNewsArticles()
    }

    private fun fetchNewsArticles() {
        // Use a coroutine to fetch news articles asynchronously
        GlobalScope.launch(Dispatchers.IO) {
            viewModel.fetchNewsArticles()
        }
    }
}