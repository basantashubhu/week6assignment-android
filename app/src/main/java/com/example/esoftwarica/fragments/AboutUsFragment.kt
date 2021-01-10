package com.example.esoftwarica.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import com.example.esoftwarica.R

class AboutUsFragment : Fragment() {
    private lateinit var webView: WebView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_about_us, container, false)
        //binding
        webView = view.findViewById(R.id.webview)

        webView.apply {
            settings.javaScriptEnabled = true
            canGoBack()
            webViewClient = MyBrowser()

            loadUrl("https://softwarica.edu.np/")
        }

        return view
    }

    private class MyBrowser : WebViewClient() {
        @SuppressLint("NewApi")
        override fun shouldOverrideUrlLoading(
            view: WebView?,
            request: WebResourceRequest?
        ): Boolean {
            if (request != null) {
                view?.loadUrl(request.url.toString())
            }
            return true
        }
    }
}