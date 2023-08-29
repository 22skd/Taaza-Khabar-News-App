package com.example.newsapp;

import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageButton;

public class webView extends AppCompatActivity {
    private WebView webView;
    private ImageButton shareButton;
    private String newsUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);

        webView = findViewById(R.id.webview);
        shareButton = findViewById(R.id.shareButton);

        // Get the news URL from the previous activity
        newsUrl = getIntent().getStringExtra("url");

        // Configure WebView settings
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl(newsUrl);

        // Share button click listener
        shareButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shareNewsUrl();
            }
        });
    }

    private void shareNewsUrl() {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_SUBJECT, "Check out this news article");
        intent.putExtra(Intent.EXTRA_TEXT, newsUrl);
        startActivity(Intent.createChooser(intent, "Share via"));
    }
}
