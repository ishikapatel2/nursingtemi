package com.example.nursingtemi;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;



import java.util.Objects;

public class StaffActivity extends AppCompatActivity{

    private Button nurse;
    private Button pharm;
    private Button food;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Objects.requireNonNull(getSupportActionBar()).hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_staff);

        Button delivery = findViewById(R.id.delivery);
        Button vitals = findViewById(R.id.vitals);
        pharm = findViewById(R.id.pharmacy);
        nurse = findViewById(R.id.nurse);
        Button data = findViewById(R.id.data);
        food = findViewById(R.id.meal);
        ImageView backButton = findViewById(R.id.backButton);
        ImageView tutorial = findViewById(R.id.tutorial);

        tutorial.setOnClickListener((v) ->{
            playVideo();
        });

        data.setOnClickListener((v) -> {
            Intent obj = new Intent(this, RecordedVitalsActivity.class);
            startActivity(obj);
        });

        // selecting delivery
        delivery.setOnClickListener((v) -> {
            delivery.setVisibility(View.GONE);
            vitals.setVisibility(View.GONE);
            data.setVisibility(View.GONE);
            pharm.setVisibility(View.VISIBLE);
            nurse.setVisibility(View.VISIBLE);
            food.setVisibility(View.VISIBLE);
            tutorial.setVisibility(View.VISIBLE);

            backButton.setOnClickListener((y) -> {
                Intent obj = new Intent(this, StaffActivity.class);
                startActivity(obj);
            });
        });

        nurse.setOnClickListener((v) -> {
            Intent obj = new Intent(this, DeliveryActivityNurse.class);
            startActivity(obj);
        });

        pharm.setOnClickListener((v) -> {
            Intent obj = new Intent(this, DeliveryActivity.class);
            startActivity(obj);
        });

        food.setOnClickListener((v) -> {
            Intent obj = new Intent(this, FoodActivity.class);
            startActivity(obj);
        });

        // selecting vitals
        vitals.setOnClickListener((v) -> {
            Intent obj = new Intent(this, Vitals.class);
            startActivity(obj);
        });

        backButton.setOnClickListener((v) -> {
            tutorial.setVisibility(View.INVISIBLE);
            Intent obj = new Intent(this, MainActivity.class);
            startActivity(obj);
        });
    }
    private void playVideo() {
        VideoView videoView = findViewById(R.id.videoView);
        Button closeVideoButton = findViewById(R.id.closeVideoButton);

        String videoPath = "android.resource://" + getPackageName() + "/" + R.raw.video_delivery;
        Uri uri = Uri.parse(videoPath);
        videoView.setVideoURI(uri);
        videoView.start();

        videoView.setVisibility(View.VISIBLE);
        closeVideoButton.setVisibility(View.VISIBLE);
        pharm.setVisibility(View.INVISIBLE);
        food.setVisibility(View.INVISIBLE);
        nurse.setVisibility(View.INVISIBLE);



        closeVideoButton.setOnClickListener(v -> {
            videoView.stopPlayback();
            videoView.setVisibility(View.GONE);
            closeVideoButton.setVisibility(View.GONE);
            pharm.setVisibility(View.VISIBLE);
            food.setVisibility(View.VISIBLE);
            nurse.setVisibility(View.VISIBLE);

        });
    }

}
