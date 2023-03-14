package com.example.crossycar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GestureDetectorCompat;

import android.graphics.Point;
import android.media.Image;
import android.os.Bundle;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;


import java.util.ArrayList;
import java.util.List;
import java.util.Timer;



public class GameScreen extends AppCompatActivity {


    private TextView playerNameView;
    private TextView scoreTextView;

    private ImageView[] heartIcons = new ImageView[5];
    private ImageView leftButton;
    private ImageView rightButton;
    private ImageView upButton;
    private ImageView downButton;
    private int score = 0;

    private ImageView car;
    public int carY;
    public int carX;

    private int vLevel = 0;
    private int hLevel = 5;
    private int screenW = 1125;
    private int screenH = 2000;
    private int tileSize = 100;


    private Handler handler = new Handler();
    private Timer timer = new Timer();
    private GestureDetectorCompat gestureDetector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_screen);



        // on below line we are getting screen dimensions
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int screenW = size.x;
        int screenH = size.y;
        tileSize = screenH / 20;





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

        leftButton = findViewById(R.id.left_b);
        rightButton = findViewById(R.id.right_b);
        upButton = findViewById(R.id.up_b);
        downButton = findViewById(R.id.down_b);

        Cow cow1 = new Cow(screenW + 10, tileSize * 10, -40);
        ImageView cow1view = findViewById(R.id.cow_icon);
        cow1view.setY(cow1.getY());
        cow1view.setX(cow1.getX());
        cow1.moveObject(cow1view);




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



        carIconView.getLayoutParams().width = tileSize;
        carIconView.getLayoutParams().height = tileSize;


        tileSize -= 10;
        carX = screenW / 2 - 50;
        carY = tileSize * 16;

        carIconView.setY(carY);
        car.setVisibility(ImageView.VISIBLE);

        rightButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                moveRight();
            }
        });
        leftButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                moveLeft();
            }
        });
        upButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                moveUp();
            }
        });
        downButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                moveDown();
            }
        });


    }

    //List to keep track of previous carY locations so score updates correctly
    List<Integer> carPastY = new ArrayList<Integer>();
    public void moveUp() {
        if (vLevel < 15) {
            carY -= tileSize;
            if (!carPastY.contains(carY)) {
                score++;
                carPastY.add(carY);
            }
            carPastY.add(carY);
            vLevel++;
            scoreTextView.setText("Score: " + Integer.toString(score));
        }
        car.setX(carX);
        car.setY(carY);
    }
    public void moveDown() {
        if (vLevel > 0) {
            carY += tileSize;
            vLevel--;
//            score--;
            scoreTextView.setText("Score: " + Integer.toString(score));
        }
        car.setX(carX);
        car.setY(carY);
    }

    public void moveLeft() {
        if (hLevel > 0) {
            carX -= tileSize - 4;
            hLevel--;
        }
        car.setX(carX);
        car.setY(carY);
    }
    public void moveRight() {
        if (hLevel < 10) {
            carX += tileSize - 4;
            hLevel++;
        }
        car.setX(carX);
        car.setY(carY);
    }

}

