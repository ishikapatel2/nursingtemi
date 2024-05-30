package com.example.nursingtemi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.VideoView;

import com.robotemi.sdk.Robot;
import com.robotemi.sdk.TtsRequest;

public class FoodActivity extends AppCompatActivity {

    private EditText patient;
    private Button confirmButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food);

        ImageView backButton = findViewById(R.id.backButton);

        ImageView tutorial = findViewById(R.id.tutorial);
        tutorial.setOnClickListener((v) ->{
            playVideo();
        });

        backButton.setOnClickListener((y) -> {
            Intent obj = new Intent(this, StaffActivity.class);
            startActivity(obj);
       });

        Robot.getInstance().speak(TtsRequest.create("Place the food tray on me, then press confirm to continue", false));

        confirmButton = findViewById(R.id.confirm_button);
        EditText item = findViewById(R.id.item);
        EditText quantity = findViewById(R.id.quantity);

        // when the confirm button is pressed
        confirmButton.setOnClickListener((v) ->
        {
            // makes sure all information has been given before confirming delivery
            if (!emptyCredentials(item,quantity))
            {
                patient = findViewById(R.id.patient);
                String text = patient.getText().toString();

                DeliveryItem deliveryItem = new DeliveryItem(item.getText().toString(),quantity.getText().toString());
                // start delivery continuation activity
                Intent obj = new Intent(this,DeliveryContinuationActivity.class);
                obj.putExtra("PatientName", text);
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

    private void playVideo() {
        VideoView videoView = findViewById(R.id.videoView);
        Button closeVideoButton = findViewById(R.id.closeVideoButton);

        String videoPath = "android.resource://" + getPackageName() + "/" + R.raw.video_food;
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
}