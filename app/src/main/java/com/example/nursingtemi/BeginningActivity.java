package com.example.nursingtemi;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class BeginningActivity extends AppCompatActivity {

    private Button startButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beginning);

        startButton = findViewById(R.id.startButton);

        startButton.setOnClickListener((v) ->{
            loadActivity(MainActivity.class);
        });
    }

   public void loadActivity(Class a){
       Intent obj = new Intent(this,a);
       startActivity(obj);
   }

}