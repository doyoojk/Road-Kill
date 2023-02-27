package com.example.crossycar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.core.view.GestureDetectorCompat;

import android.os.Bundle;
import android.os.Handler;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;


import java.util.Timer;



public class GameScreen extends AppCompatActivity {


    private TextView playerNameView;
    private TextView scoreTextView;
    private ImageView[] heartIcons = new ImageView[5];
    private int score = 0;

    private ImageView car;
    private int carY;
    private int carX;

    private int level = 0;


    private int screenW = 1125;
    private int screenH = 2000;

    private Handler handler = new Handler();
    private Timer timer = new Timer();
    private GestureDetectorCompat gestureDetector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_screen);


        // Add GridItems with image resource IDs and coordinates
//        for (int i = 0; i < 9; i++) {
//            int resId = getResources(); }

        // Get references to the views
        playerNameView = findViewById(R.id.player_name);
        ImageView carIconView = findViewById(R.id.car_icon);
        heartIcons[0] = findViewById(R.id.heart_icon_1);
        heartIcons[1] = findViewById(R.id.heart_icon_2);
        heartIcons[2] = findViewById(R.id.heart_icon_3);
        heartIcons[3] = findViewById(R.id.heart_icon_4);
        heartIcons[4] = findViewById(R.id.heart_icon_5);
        scoreTextView = findViewById(R.id.score_text);
        car = (ImageView) findViewById(R.id.car_icon);


        // Get the data from the intent
        String playerName = getIntent().getStringExtra("player_name");
        String carSelection = getIntent().getStringExtra("car_selection");
        String difficulty = getIntent().getStringExtra("difficulty");

        // Set the player name
        playerNameView.setText(playerName);

        // Set the car selection
        //carSelectionView.setText(carSelection);
        if (carSelection.equals("Car 1")) {
            carIconView.setImageResource(R.drawable.bmw);
        } else if (carSelection.equals("Car 2")) {
            carIconView.setImageResource(R.drawable.tesla);
        } else if (carSelection.equals("Car 3")) {
            carIconView.setImageResource(R.drawable.gwagon);
        }

        car = carIconView;
        car.setVisibility(ImageView.VISIBLE);

        // Show the appropriate number of heart icons based on the difficulty
        int heartCount = 5;
        if (difficulty.equals("Medium")) {
            heartCount = 4;
        } else if (difficulty.equals("Hard")) {
            heartCount = 2;
        }
        for (int i = 0; i < heartCount; i++) {
            heartIcons[i].setVisibility(ImageView.VISIBLE);
        }
        for (int i = heartCount; i < 5; i++) {
            heartIcons[i].setVisibility(ImageView.GONE);
        }


        //setting score to 0 initially
        scoreTextView.setText("Score: " + Integer.toString(score));

        carX = 1125 / 2;
        carY = 1900;

    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();
        if(level == 0) {
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                carY -= 108;
                level++;

            }

        } else {

        if(7 > level || level > 9) {

        //if touch is to the left
        if(x < 375) {
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                if(carX > 100) {
                    carX -= 100;
                }
            }


        //if touch to the right
        } else if(x > 750) {
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                if (carX < 950) {
                    carX += 100;
                }
            }
        }

        //if touch elsewhere
        }
        if(x > 375 && x < 750 && y < (carY)) {
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                if (carY >= 300) {
                        carY -= 108;
                        level++;

                }

            }
        }
            if(x > 375 && x < 750 && y > (carY)) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    if (carY < 1800) {
                        carY += 108;
                        level--;

                    }
                }
            }
        car.setX(carX);
        car.setY(carY);

    }
        return true;
}}



//    public boolean buttonPress(MotionEvent user) {
//        switch(user.getAction()) {
//            case MotionEvent.ACTION_MOVE:
//            case MotionEvent.ACTION_DOWN:
//            case MotionEvent.ACTION_UP:
//                carY -= 100;
//                break;
//        }
//
//
//        return true;
//    }


