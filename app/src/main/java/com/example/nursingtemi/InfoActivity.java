package com.example.nursingtemi;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;
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


        urlButton = findViewById(R.id.urlButton);

        urlButton.setOnClickListener((v)->{
            webView = (WebView) findViewById(R.id.webviewButton);
            webView.getSettings().setJavaScriptEnabled(true);
            webView.loadUrl("https://www.nursing.arizona.edu/simulated-learning");
            //startActivity(new Intent(Intent.ACTION_VIEW,Uri.parse("https://www.nursing.arizona.edu/simulated-learning")));
        });



    }
}