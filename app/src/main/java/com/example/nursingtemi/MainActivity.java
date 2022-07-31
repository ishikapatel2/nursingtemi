package com.example.nursingtemi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Objects.requireNonNull(getSupportActionBar()).hide();
        setContentView(R.layout.activity_main);
        animationBackground();

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

    public void animationBackground(){
        ConstraintLayout constraintLayout = findViewById(R.id.constraintLayout);
        AnimationDrawable animationDrawable = (AnimationDrawable) constraintLayout.getBackground();
        animationDrawable.setEnterFadeDuration(2500);
        animationDrawable.setExitFadeDuration(5000);
        animationDrawable.start();
    }

    public void openInformation (View v) {
        Intent obj = new Intent(this, InfoActivity.class);
        startActivity(obj);
    }

}













