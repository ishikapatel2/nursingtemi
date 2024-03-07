package com.example.nursingtemi;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Objects;

public class PatientActivity extends AppCompatActivity {

    private Button food;
    private Button water;
    private Button bathroom;
    private Button pain;
    private Button sleep;
    private Button call;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Objects.requireNonNull(getSupportActionBar()).hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient);

        ImageView backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener((v) -> {
            Intent obj = new Intent(this, MainActivity.class);
            startActivity(obj);
        });

        food = findViewById(R.id.food);
        water = findViewById(R.id.water);
        bathroom = findViewById(R.id.bathroom);
        pain = findViewById(R.id.pain);
        sleep = findViewById(R.id.sleep);
        call = findViewById(R.id.call);


    }

}


