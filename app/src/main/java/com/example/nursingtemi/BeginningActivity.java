package com.example.nursingtemi;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import java.util.Objects;

public class BeginningActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Objects.requireNonNull(getSupportActionBar()).hide();
        setContentView(R.layout.activity_beginning);

        ProgressBar progressBar = findViewById(R.id.progressBar);
        progressBar.setVisibility(View.INVISIBLE);

        Button startButton = findViewById(R.id.startButton);

        startButton.setOnClickListener((v) ->{
            progressBar.setVisibility(View.VISIBLE);
            try {
                Thread.sleep(250);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            loadActivity(MainActivity.class);
        });
    }

   public void loadActivity(Class a){
       Intent obj = new Intent(this,a);
       startActivity(obj);
   }

}