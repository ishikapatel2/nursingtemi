package com.example.nursingtemi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.robotemi.sdk.Robot;
import com.robotemi.sdk.TtsRequest;
import com.robotemi.sdk.listeners.OnRobotReadyListener;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import java.util.Objects;

public class DeliveryActivity extends AppCompatActivity implements OnRobotReadyListener
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        Objects.requireNonNull(getSupportActionBar()).hide();
        setContentView(R.layout.activity_delivery);

        Button confirmButton = findViewById(R.id.confirm_button);
        EditText item = findViewById(R.id.item);
        EditText quantity = findViewById(R.id.quantity);
        ImageView backButton = findViewById(R.id.backButton);

        animationBackground();

        backButton.setOnClickListener((v) ->
        {
            Intent obj = new Intent(this, MainActivity.class);
            startActivity(obj);
        });

        confirmButton.setOnClickListener((v) ->
        {
            if (!emptyCredentials(item,quantity))
            {
                DeliveryItem deliveryItem = new DeliveryItem(item.getText().toString(),quantity.getText().toString());

                Intent obj = new Intent(this,DeliveryContinuationActivity.class);
                obj.putExtra("item", deliveryItem);
                startActivity(obj);
            }

        });
    }

    @Override
    protected void onStart()
    {
        super.onStart();
        Robot.getInstance().addOnRobotReadyListener(this);
    }

    @Override
    protected void onStop()
    {
        super.onStop();
        Robot.getInstance().removeOnRobotReadyListener(this);
    }

    @Override
    public void onRobotReady(boolean isReady)
    {
        if (isReady)
        {
            Robot.getInstance().hideTopBar();
            Robot.getInstance().setVolume(3);
            Robot.getInstance().speak(TtsRequest.create("Place the item on me, then press confirm to continue", false));
        }
    }
    public void animationBackground()
    {
        ConstraintLayout constraintLayout = findViewById(R.id.constraintLayout);
        AnimationDrawable animationDrawable = (AnimationDrawable) constraintLayout.getBackground();
        animationDrawable.setEnterFadeDuration(2500);
        animationDrawable.setExitFadeDuration(5000);
        animationDrawable.start();
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