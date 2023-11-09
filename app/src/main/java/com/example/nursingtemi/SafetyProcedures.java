/**
 * Author: Nathan Teku
 * Purpose: To have the user have the option to see the emergency plan pdf and to go to the nearest location.
 */


package com.example.nursingtemi;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import java.util.Objects;

// TODO: Still working on coordinate error that I have on my other branch.
public class SafetyProcedures extends AppCompatActivity {

    private Button pdf;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Objects.requireNonNull(getSupportActionBar()).hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_safety_procedures);

        pdf = findViewById(R.id.pdfButton);

        pdf.setOnClickListener((v)-> openActivity(PDFView.class));
    }


    public void openActivity(Class a){
        Intent obj = new Intent(this,a);
        startActivity(obj);
    }

}