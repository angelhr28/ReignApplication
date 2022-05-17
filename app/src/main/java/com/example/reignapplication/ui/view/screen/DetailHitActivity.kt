package com.example.reignapplication.ui.view.screen

import android.os.Bundle
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import com.example.reignapplication.databinding.ActivityDetailHitBinding
import com.example.reignapplication.domain.model.Hit
import com.example.reignapplication.utils.parseJson

class DetailHitActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailHitBinding
    private lateinit var hit: Hit

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailHitBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initData()
        initView()
    }

    private fun initData() {
        intent.extras?.apply {
            getString("hit")?.let {
                hit = parseJson(it)
            }
        }
    }

    private fun initView() {

        binding.apply {
            webView.webViewClient = WebViewClient()

            webView.loadUrl(hit.url ?: "")

            webView.settings.javaScriptEnabled = true

            webView.settings.setSupportZoom(true)

            contentBack.setOnClickListener {
                onBackPressed()
            }
        }


    }

    override fun onBackPressed() {
        binding.webView.apply {
            if (canGoBack()) goBack()
            else super.onBackPressed()
        }
    }
}