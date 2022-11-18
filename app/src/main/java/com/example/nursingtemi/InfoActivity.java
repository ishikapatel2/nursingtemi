package com.example.nursingtemi;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import androidx.appcompat.app.AppCompatActivity;

public class InfoActivity extends AppCompatActivity {

    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_info);
        webView = (WebView) findViewById(R.id.webView1);
        webView.setVisibility(View.GONE);
    }


    public void open(View view) {
        webView = (WebView) findViewById(R.id.webView1);
        webView.setVisibility(View.VISIBLE);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl("https://www.nursing.arizona.edu/simulated-learning");
    }
}