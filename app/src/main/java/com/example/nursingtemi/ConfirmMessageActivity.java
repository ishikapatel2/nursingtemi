package com.example.nursingtemi;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import java.util.Objects;

public class ConfirmMessageActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        Objects.requireNonNull(getSupportActionBar()).hide();
        setContentView(R.layout.activity_confirm_message);

        TextView message = findViewById(R.id.textMessage);
        Button returnButton = findViewById(R.id.returnButton);

        DeliveryItem item = (DeliveryItem) getIntent().getSerializableExtra("item");
        message.setText("Hello, we were ordered to give " + item.getQuantity() + ", " + item.getItem() + "to this room.");

        returnButton.setOnClickListener((v)->{
            Intent intent = new Intent(this,MainActivity.class);
            startActivity(intent);
        });
    }
}