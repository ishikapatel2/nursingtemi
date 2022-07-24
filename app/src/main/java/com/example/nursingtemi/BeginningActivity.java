package com.example.nursingtemi;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

public class BeginningActivity extends AppCompatActivity {

    private Button startButton;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_beginning);

        progressBar = findViewById(R.id.progressBar);
        progressBar.setVisibility(View.INVISIBLE);

        startButton = findViewById(R.id.startButton);

        startButton.setOnClickListener((v) ->{
            progressBar.setVisibility(View.VISIBLE);
            try {
                Thread.sleep(1500);
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