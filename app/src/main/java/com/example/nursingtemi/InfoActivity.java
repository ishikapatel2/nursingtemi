package com.example.nursingtemi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

public class InfoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
    }

    public void nursingWebsite(View view) {
        Intent websiteIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.nursing.arizona.edu/welcome"));
        startActivity(websiteIntent);
    }



    // Random Comment
}