
package com.example.nursingtemi;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.VideoView;

import java.util.Objects;

public class SafetyProcedures extends AppCompatActivity {

    private ImageView backButton;
    private ImageView qrcode;
    private Button nav;

    private Boolean check;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Objects.requireNonNull(getSupportActionBar()).hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_safety_procedures);

        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inSampleSize = 4; // reduces width and height by a factor of 4
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.qrcode, options);
        qrcode = findViewById(R.id.qrcode);
        qrcode.setImageBitmap(bitmap);

        backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener((v) -> {
            Intent obj = new Intent(this, MainActivity.class);
            startActivity(obj);
        });

        nav = findViewById(R.id.nav);

        nav.setOnClickListener((v) -> {
            Intent obj = new Intent(this, MapActivity.class);
            startActivity(obj);
        });

        ImageView tutorial = findViewById(R.id.tutorial);
        ImageView tutorial2 = findViewById(R.id.tutorial2);

        tutorial.setOnClickListener((v) ->{
            check = true;
            playVideo();
        });

        tutorial2.setOnClickListener((v) ->{
            check = false;
            playVideo();
        });




    }

    private void playVideo() {
        VideoView videoView = findViewById(R.id.videoView);
        Button closeVideoButton = findViewById(R.id.closeVideoButton);
        String videoPath;

        if (check) {
            videoPath = "android.resource://" + getPackageName() + "/" + R.raw.video_safety;
        }
        else {
            videoPath = "android.resource://" + getPackageName() + "/" + R.raw.video_map;
        }
        Uri uri = Uri.parse(videoPath);
        videoView.setVideoURI(uri);
        videoView.start();

        videoView.setVisibility(View.VISIBLE);
        closeVideoButton.setVisibility(View.VISIBLE);
        nav.setVisibility(View.INVISIBLE);

        closeVideoButton.setOnClickListener(v -> {
            videoView.stopPlayback();
            videoView.setVisibility(View.GONE);
            closeVideoButton.setVisibility(View.GONE);
            nav.setVisibility(View.VISIBLE);

        });
    }

}