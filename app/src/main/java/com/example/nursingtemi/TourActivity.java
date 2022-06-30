package com.example.nursingtemi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.robotemi.sdk.Robot;
import com.robotemi.sdk.TtsRequest;
import com.robotemi.sdk.listeners.OnRobotReadyListener;

public class TourActivity extends AppCompatActivity implements OnRobotReadyListener {

    private Handler handler;
    private Runnable textLoop;
    private TextView titleTextView;
    //    private TextView infoTextView;
    private ImageView qrCodeImageView;
    private Button continueButton;
    private static int curLoc;

    private final TourLocation[] locations = {



            new TourLocation("VR Station", "vr station"),
            new TourLocation("Graduate Student Station", "grad desk"),
            new TourLocation("Dr. Carter's Desk", "carter desk")
    };
    private static final String HOME_BASE_LOCATION = "home base";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tour);

        titleTextView = findViewById(R.id.locationText);
        continueButton = findViewById(R.id.continueButton);


        continueButton.setOnClickListener((v)->{
            if (curLoc == locations.length){
                Robot.getInstance().speak(TtsRequest.create("Thank you for participating in the tour of the hospital. Returning to home base", false));
                Robot.getInstance().goTo(HOME_BASE_LOCATION);
            }
            else {
               // Toast.makeText(this, locations[curLoc].getLocation(), Toast.LENGTH_LONG).show();
                Robot.getInstance().goTo(locations[curLoc].getLocation());
                curLoc++;
            }
                });
    }

    @Override
    protected void onStart() {
        super.onStart();
        Robot.getInstance().addOnRobotReadyListener(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        Robot.getInstance().removeOnRobotReadyListener(this);
    }

    @Override
    public void onRobotReady(boolean isReady) {
        if (isReady) {
            Robot.getInstance().hideTopBar();

            // Begin the tour
            Robot.getInstance().setVolume(3);
            Robot.getInstance().speak(TtsRequest.create("Please follow me around the premiscense.", false));
            Robot.getInstance().goTo(locations[curLoc].getLocation());
           curLoc++;
           // this.goToStop();
        }
    }

    public void animationBackground(){
        ConstraintLayout constraintLayout = findViewById(R.id.constraintLayout);
        AnimationDrawable animationDrawable = (AnimationDrawable) constraintLayout.getBackground();
        animationDrawable.setEnterFadeDuration(2500);
        animationDrawable.setExitFadeDuration(5000);
        animationDrawable.start();
    }
    /*
    private void goToStop() {
        // Temporarily remove click listener
        continueButton.setOnClickListener(null);

        TourLocation location = this.locations[this.curLoc];
        Log.d("abcdefg", "Heading to location " + location.getLocation());
        Robot.getInstance().goTo(location.getLocation());

        // Set location and QR code
        titleTextView.setText(location.getTitle());
      //  qrCodeImageView.setImageDrawable(location.getQr(getResources()));

        // Set up continue button for next stop
        ++this.curLoc;
        if (this.curLoc < this.locations.length) {
            continueButton.setOnClickListener((v) -> {
                this.goToStop();
            });
        } else {
            continueButton.setText("Finish");
            continueButton.setOnClickListener((v) -> {
                Robot.getInstance().speak(TtsRequest.create(
                        "Thank you for participating in the tour of the lab. Returning to home base", false));
                Robot.getInstance().goTo(HOME_BASE_LOCATION);
                finish();
            });
        }
    }

    /**
     * Starts the text animation
     */
//    private void startTextAnimation(String infoStr) {
//        handler = new Handler();
//        textLoop = new Runnable() {
//            /** Current index in the info string */
//            private int indexInStr = 0;
//
//            @Override
//            public void run() {
//                if (indexInStr < infoStr.length()) {
//                    // Replace the text in the info TextView
//                    infoTextView.setText(infoStr.substring(0, indexInStr + 1) + '\u2588');
//
//                    // Set up for the next character
//                    int delay;
//                    if (this.isPunctuation(infoStr.charAt(indexInStr))) {
//                        delay = 180;    // longer delay after punctuation
//                    } else {
//                        delay = 50;
//                    }
//                    ++indexInStr;
//                    handler.postDelayed(this, delay);
//                } else {
//                    concludeTextAnimation(infoStr);
//                }
//            }
//
//            /**
//             * Basic check for if a character is punctuation
//             * @param c is the character to check
//             * @return whether the character is punctuation
//             */
//            private boolean isPunctuation(char c) {
//                return c == ',' || c == '.' || c == '!' || c == '?' || c == ':' || c == ';';
//            }
//        };
//        handler.postDelayed(textLoop, 500);
//    }
//
//    /**
//     * Ends the info text animation
//     */
//    private void stopTextAnimation(String infoStr) {
//        if (textLoop != null) {
//            handler.removeCallbacks(textLoop);
//        }
//        this.concludeTextAnimation(infoStr);
//    }
//
//    /**
//     * Concludes the text animation by setting the text to the complete info string
//     */
//    private void concludeTextAnimation(String infoStr) {
//        textLoop = null;
//        infoTextView.setText(infoStr);
//    }

}