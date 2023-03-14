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
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;
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
    private int screenW = 1125; //before setting values
    private int screenH = 2000; //before setting values
    private int tileSize = 100; //before setting values
    private String[][] tileTypes = new String[20][10];

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
        //tileSize = screenH / 20;

        // Create grid layout
        GridLayout gameGrid = new GridLayout(this);
        // Calculate tile size based on screen dimensions
        gameGrid.setColumnCount(10);
        gameGrid.setRowCount(20);
        tileSize = Math.min(screenW / gameGrid.getColumnCount(), screenH / gameGrid.getRowCount());

        // Set layout parameters to match parent layout's dimensions
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);
        gameGrid.setLayoutParams(layoutParams);

        //initializing goal tiles
        for (int i = 0; i < 1; i++) {
            for (int j = 0; j < gameGrid.getColumnCount(); j++){
                ImageView imageView = new ImageView(this);
                imageView.setLayoutParams(new ViewGroup.LayoutParams(tileSize, tileSize));
                imageView.setImageResource(R.drawable.filler);
                gameGrid.addView(imageView);
                tileTypes[i][j] = "filler";
            }
        }
        for (int i = 0; i < 1; i++) {
            for (int j = 0; j < gameGrid.getColumnCount(); j++){
                ImageView imageView = new ImageView(this);
                imageView.setLayoutParams(new ViewGroup.LayoutParams(tileSize, tileSize));
                imageView.setImageResource(R.drawable.goal);
                gameGrid.addView(imageView);
                tileTypes[i + 1][j] = "goal";
            }
        }

        // Add tile layers to grid
        Random rand = new Random();
        int layers = 14;
        int prevRand = -1;
        while (layers > 0) {
            int x = rand.nextInt(3);
            if (x == prevRand) {
                continue;
            }
            switch (x) {
                case 0: //grass
                    prevRand = 0;
                    if (layers > 3) {
                        for (int i = 0; i < 3; i++) {
                            for (int j = 0; j < gameGrid.getColumnCount(); j++) {
                                ImageView imageView = new ImageView(this);
                                imageView.setLayoutParams(new ViewGroup.LayoutParams(tileSize, tileSize));
                                imageView.setImageResource(R.drawable.grass);
                                gameGrid.addView(imageView);
                                tileTypes[i + 2 + (14 - layers)][j] = "grass";
                            }
                        }
                        layers -= 3;
                    } else { //if there is not enough layers to make 3 layers of grass
                        for (int i = 0; i < layers; i++) {
                            for (int j = 0; j < gameGrid.getColumnCount(); j++) {
                                ImageView imageView = new ImageView(this);
                                imageView.setLayoutParams(new ViewGroup.LayoutParams(tileSize, tileSize));
                                imageView.setImageResource(R.drawable.grass);
                                gameGrid.addView(imageView);
                                tileTypes[i + 2 + (14 - layers)][j] = "grass";
                            }
                        }
                        layers = 0;
                    }
                    break;
                case 1: //street
                    prevRand = 1;
                    for (int i = 0; i < 1; i++) {
                        for (int j = 0; j < gameGrid.getColumnCount(); j++) {
                            ImageView imageView = new ImageView(this);
                            imageView.setLayoutParams(new ViewGroup.LayoutParams(tileSize, tileSize));
                            imageView.setImageResource(R.drawable.street);
                            gameGrid.addView(imageView);
                            tileTypes[i + 2 + (14 - layers)][j] = "street";
                        }
                    }
                    layers -= 1;
                    break;
                case 2: //river
                    prevRand = 2;
                    if (layers > 2) {
                        for (int i = 0; i < 2; i++) {
                            for (int j = 0; j < gameGrid.getColumnCount(); j++) {
                                ImageView imageView = new ImageView(this);
                                imageView.setLayoutParams(new ViewGroup.LayoutParams(tileSize, tileSize));
                                imageView.setImageResource(R.drawable.river);
                                gameGrid.addView(imageView);
                                tileTypes[i + 2 + (14 - layers)][j] = "river";
                            }
                        }
                        layers -= 2;
                    } else { //if there is not enough layers to make 2 layers of river
                        for (int i = 0; i < layers; i++) {
                            for (int j = 0; j < gameGrid.getColumnCount(); j++) {
                                ImageView imageView = new ImageView(this);
                                imageView.setLayoutParams(new ViewGroup.LayoutParams(tileSize, tileSize));
                                imageView.setImageResource(R.drawable.river);
                                gameGrid.addView(imageView);
                                tileTypes[i + 2 + (14 - layers)][j] = "river";
                            }
                        }
                        layers = 0;
                    }
                    break;
            }

        }

        //initialize start tiles
        for (int i = 0; i < 1; i++) {
            for (int j = 0; j < gameGrid.getColumnCount(); j++){
                ImageView imageView = new ImageView(this);
                imageView.setLayoutParams(new ViewGroup.LayoutParams(tileSize, tileSize));
                imageView.setImageResource(R.drawable.goal);
                gameGrid.addView(imageView);
                tileTypes[i + 16][j] = "start";
            }
        }
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < gameGrid.getColumnCount(); j++){
                ImageView imageView = new ImageView(this);
                imageView.setLayoutParams(new ViewGroup.LayoutParams(tileSize, tileSize));
                imageView.setImageResource(R.drawable.filler);
                gameGrid.addView(imageView);
                tileTypes[i + 17][j] = "filler";
            }
        }

//        //testing for tileTypes
//        for (int i = 0; i < tileTypes.length; i++) {
//            for (int j = 0; j < tileTypes[i].length; j++) {
//                System.out.print(tileTypes[i][j] + i + j + " ");
//            }
//            System.out.println();
//        }


        // Add grid layout to root view
        RelativeLayout rootView = findViewById(R.id.root_view);
        rootView.addView(gameGrid);

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


        carX = tileSize * 5;
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

    public String getTileType(int x, int y) {
        int row = y/tileSize;
        int col = x/tileSize;
        return tileTypes[row][col];
    }
}

