package com.example.nursingtemi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

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
    private int curLoc = 0;

    private final TourLocation[] locations = {
            /*
            new TourLocation("VR Station", "vr station", R.drawable.qr_vr),
            new TourLocation("Graduate Student Station", "grad desk", R.drawable.qr_grad_student),
            new TourLocation("Dr. Carter's Desk", "carter desk", R.drawable.qr_carter)

             */
    };
    private static final String HOME_BASE_LOCATION = "home base";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tour);
        /*
        titleTextView = findViewById(R.id.location_title);
        qrCodeImageView = findViewById(R.id.qr_code);
//        infoTextView = findViewById(R.id.info_text_view);
        continueButton = findViewById(R.id.continue_button);

         */
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

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Set options menu to have exit icon
//        getMenuInflater().inflate(R.menu.menu_main, menu);
//        return super.onCreateOptionsMenu(menu);
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
//        int id = item.getItemId();
//        if (id == R.id.action_finish_tour) {
//            finish();
//        }
//        return super.onOptionsItemSelected(item);
//    }

    @Override
    public void onRobotReady(boolean isReady) {
        if (isReady) {
            Robot.getInstance().hideTopBar();

            // Begin the tour
            Robot.getInstance().setVolume(3);
            Robot.getInstance().speak(TtsRequest.create("Please follow me around the lab and scan the QR code at each stop", false));
//            TODO: remove text animation stuff
//            startTextAnimation("This is an example string of info text for the center for digital humanities upcoming demo on Friday");
            this.goToStop();
        }
    }

    private void goToStop() {
        // Temporarily remove click listener
        continueButton.setOnClickListener(null);

        TourLocation location = this.locations[this.curLoc];
        Log.d("abcdefg", "Heading to location " + location.getLocation());
        Robot.getInstance().goTo(location.getLocation());

        // Set location and QR code
        titleTextView.setText(location.getTitle());
        qrCodeImageView.setImageDrawable(location.getQr(getResources()));

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