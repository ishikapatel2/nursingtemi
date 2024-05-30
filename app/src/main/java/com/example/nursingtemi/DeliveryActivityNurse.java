package com.example.nursingtemi;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;

import com.robotemi.sdk.Robot;
import com.robotemi.sdk.TtsRequest;
import com.robotemi.sdk.listeners.OnRobotReadyListener;

import java.util.Objects;

public class DeliveryActivityNurse extends AppCompatActivity implements OnRobotReadyListener
{
    private Button confirmButton;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        Objects.requireNonNull(getSupportActionBar()).hide();
        setContentView(R.layout.activity_delivery_nurse);

        ImageView tutorial = findViewById(R.id.tutorial);
        tutorial.setOnClickListener((v) ->{
            playVideo();
        });

        confirmButton = findViewById(R.id.confirm_button);
        ImageView backButton = findViewById(R.id.backButton);

        backButton.setOnClickListener((v) ->
        {
            Intent obj = new Intent(this, MainActivity.class);
            startActivity(obj);
        });

        // when the confirm button is pressed
        confirmButton.setOnClickListener((v) ->
        {

            EditText sender = findViewById(R.id.sender);
            EditText item = findViewById(R.id.item);
            EditText quantity = findViewById(R.id.quantity);

            // makes sure all information has been given before confirming delivery
            if (!emptyCredentials(item,quantity))
            {
                DeliveryItem deliveryItem = new DeliveryItem(item.getText().toString(),quantity.getText().toString());
                Intent obj = new Intent(this,DeliveryContinuationActivityNurse.class);
                obj.putExtra("temp", deliveryItem);
                obj.putExtra("item", item.getText().toString());
                obj.putExtra("quantity", quantity.getText().toString());
                obj.putExtra("sender", sender.getText().toString());
                startActivity(obj);
            }
        });
    }

    private void playVideo() {
        VideoView videoView = findViewById(R.id.videoView);
        Button closeVideoButton = findViewById(R.id.closeVideoButton);

        String videoPath = "android.resource://" + getPackageName() + "/" + R.raw.video_nurse;
        Uri uri = Uri.parse(videoPath);
        videoView.setVideoURI(uri);
        videoView.start();

        videoView.setVisibility(View.VISIBLE);
        closeVideoButton.setVisibility(View.VISIBLE);
        confirmButton.setVisibility(View.INVISIBLE);



        closeVideoButton.setOnClickListener(v -> {
            videoView.stopPlayback();
            videoView.setVisibility(View.GONE);
            closeVideoButton.setVisibility(View.GONE);
            confirmButton.setVisibility(View.VISIBLE);

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
            Robot.getInstance().speak(TtsRequest.create("Please fill out this form to request a delivery", false));
        }
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