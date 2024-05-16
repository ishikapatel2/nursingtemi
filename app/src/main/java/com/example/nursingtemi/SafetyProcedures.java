
package com.example.nursingtemi;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.util.Objects;

public class SafetyProcedures extends AppCompatActivity {

    private ImageView backButton;
    private ImageView qrcode;
    private Button nav;

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




    }

}