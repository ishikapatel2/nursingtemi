package com.example.nursingtemi;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;

import com.example.nursingtemi.R.id;


public class SurveyActivity extends AppCompatActivity {

    private WebView surveyWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_survey);



        surveyWebView = findViewById(R.id.surveyWebView);
        surveyWebView.getSettings().setJavaScriptEnabled(true);
        surveyWebView.setWebViewClient(new WebViewClient());

        String formUrl = "https://docs.google.com/forms/d/e/1FAIpQLScPb9tP-kzz9m8GCPqkfzv8ertHM57zCWSK7DzJ-5QBAP8jxA/viewform?usp=sf_link";
        surveyWebView.loadUrl(formUrl);



        ImageView backButton = findViewById(id.backButton2);
        backButton.setOnClickListener((v) ->{
            Intent obj = new Intent(this, MainActivity.class);
            startActivity(obj);
        });
    }
}