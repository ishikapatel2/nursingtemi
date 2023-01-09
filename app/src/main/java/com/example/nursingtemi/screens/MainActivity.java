package com.example.nursingtemi.screens;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.nursingtemi.R;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Objects.requireNonNull(getSupportActionBar()).hide();
        setContentView(R.layout.activity_main);


        Button tourButton = findViewById(R.id.tourButton);
        tourButton.setOnClickListener((v) -> openActivity(TourActivity.class));

        Button deliveryButton = findViewById(R.id.deliveryButton);
        deliveryButton.setOnClickListener((v)-> openActivity(DeliveryActivity.class));

        Button surveyButton = findViewById(R.id.surveyButton);
        surveyButton.setOnClickListener((v)-> openActivity(SurveyActivity.class));

        Button exitButton = findViewById(R.id.exitButton);
        exitButton.setOnClickListener((v) ->{
            finishAffinity();
            System.exit(0);
        });
    }

    public void openActivity(Class a){
        Intent obj = new Intent(this,a);
        startActivity(obj);
    }



    public void openInformation (View v) {
        Intent obj = new Intent(this, InfoActivity.class);
        startActivity(obj);
    }

}













