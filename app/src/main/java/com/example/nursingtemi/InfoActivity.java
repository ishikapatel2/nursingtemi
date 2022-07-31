package com.example.nursingtemi;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class InfoActivity extends AppCompatActivity {


    private Button urlButton;
    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_info);
        webView = findViewById(R.id.webviewButton);
        webView.setVisibility(View.INVISIBLE);
        urlButton = findViewById(R.id.urlButton);

        urlButton.setOnClickListener((v)->{
            webView.setVisibility(View.VISIBLE);
            webView.getSettings().setJavaScriptEnabled(true);
            webView.loadUrl("https://www.nursing.arizona.edu/simulated-learning");
        });
    }
}