package com.example.nursingtemi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class FoodActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food);

        ImageView backButton = findViewById(R.id.backButton);

        backButton.setOnClickListener((y) -> {
            Intent obj = new Intent(this, StaffActivity.class);
            startActivity(obj);
       });

        Button confirmButton = findViewById(R.id.confirm_button);
        EditText item = findViewById(R.id.item);
        EditText quantity = findViewById(R.id.quantity);

        // when the confirm button is pressed
        confirmButton.setOnClickListener((v) ->
        {
            // makes sure all information has been given before confirming delivery
            if (!emptyCredentials(item,quantity))
            {
                DeliveryItem deliveryItem = new DeliveryItem(item.getText().toString(),quantity.getText().toString());
                // start delivery continuation activity
                Intent obj = new Intent(this,DeliveryContinuationActivity.class);
                obj.putExtra("deliveryType", "Food");
                obj.putExtra("item", deliveryItem);
                startActivity(obj);
            }
        });

    }

    /*
     * Checks if any fields are empty before confirming the delivery
     */
    public boolean emptyCredentials(EditText item, EditText quantity)
    {
        if  ( (item.getText().toString().isEmpty()) && (quantity.getText().toString().isEmpty()) )
        {
            item.setError("Please fill this field");
            quantity.setError("Please fill this field");
            return true;
        }
        else
        {
            if (item.getText().toString().isEmpty())
            {
                item.setError("Please fill this field");
                return true;
            }

            if (quantity.getText().toString().isEmpty())
            {
                quantity.setError("Please fill this field");
                return true;
            }
        }
        return false;
    }
}