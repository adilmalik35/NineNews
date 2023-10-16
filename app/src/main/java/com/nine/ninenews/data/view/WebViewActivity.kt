package com.nine.ninenews.data.view

import android.os.Bundle
import android.webkit.WebView
import androidx.appcompat.app.AppCompatActivity
import com.nine.ninenews.R

class WebViewActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_webview)

        // Get the URL from the intent extra
        val url = intent.getStringExtra("url")

        // Find the WebView in your layout
        val webView = findViewById<WebView>(R.id.webView)

        // Load the URL into the WebView
        url?.let { webView.loadUrl(it) }
    }
}


