package com.example.danielmaina.flashnews;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;

public class NewsDetails extends AppCompatActivity {
WebView webView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_details);

        //linking the webView xml to the java
        webView = (WebView) findViewById(R.id.webview);
        //bundle to store the links
        Bundle bundle=getIntent().getExtras();
        webView.loadUrl(bundle.getString("Link"));
    }
}
