package com.example.crossycar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GestureDetectorCompat;

import android.content.Intent;
import android.graphics.Point;

import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Bundle;
import android.os.Handler;

import android.view.Display;

import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;


import java.util.ArrayList;

import java.util.List;
import java.util.Random;
import java.util.Timer;



public class GameScreen extends AppCompatActivity {


    private TextView playerNameView;
    private TextView scoreTextView;
    private RelativeLayout grassView;
    private RelativeLayout riverView;
    private ImageView[] heartIcons = new ImageView[5];
    private ImageView leftButton;
    private ImageView rightButton;
    private ImageView upButton;
    private ImageView downButton;
    private int score = 0;

    private ImageView car;
    private int carY;
    private int carX;
    private int hLevel = 5;
    private int screenW = 1125; //before setting values
    private int screenH = 2000; //before setting values
    private int tileSize = 100; //before setting values
    private String[][] tileTypes = new String[20][10];

    private Handler handler = new Handler();
    private Timer timer = new Timer();
    private GestureDetectorCompat gestureDetector;
    private int lives = 0;
    private List<riverObject> riverObjects = new ArrayList<>();
    private List<grassObject> grassObjects = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_screen);
        makeGame();
    }

    //List to keep track of previous carY locations so score updates correctly
    private List<Integer> carPastY = new ArrayList<Integer>();
    public void moveUp() {
        if (carY > tileSize) {
            carY -= tileSize;
            //checking if the car makes it to the goal & sends it to the game end screen
            if (getTileType(carX, carY) == "goal" || lives == 0) {
                Intent gameEndScreen = new Intent(GameScreen.this, GameEnd.class);
                gameEndScreen.putExtra("score", score); // Pass the current score to GameEnd Class
                startActivity(gameEndScreen);
            }
            if (!carPastY.contains(carY)) {
                // The number of points gained from crossing different obstacles is different.
                if (getTileType(carX, carY) == "grass") {
                    score += 2;
                    carPastY.add(carY);
                } else if (getTileType(carX, carY) == "river" && !isPlayerOnLogOrBoat()) {
                    lives -= 1;
                    score = 0;
                    carPastY.clear();
                    if (lives > 0) {
                        setCarX(tileSize * 5);
                        setCarY(tileSize * 16);
                        updateHeartIcon();
                    }
                } else {
                    score += 1;
                    carPastY.add(carY);
                }
            }
            //carPastY.add(carY);
            scoreTextView.setText("Score: " + Integer.toString(score));
        }
        car.setX(carX);
        car.setY(carY);
    }
    public void moveDown() {
        if (carY < tileSize * 16) {
            carY += tileSize;
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
        int row = y / tileSize;
        int col = x / tileSize;
        return tileTypes[row][col];
    }

    public void updateHeartIcon() {
        // Update the heart icon view
        for (int i = 0; i < 5; i++) {
            if (i < lives) {
                heartIcons[i].setVisibility(ImageView.VISIBLE);
            } else {
                heartIcons[i].setVisibility(ImageView.GONE);
            }
        }
    }

//    public boolean isPlayerOnLogOrBoat() {
//        //should fill out later
//        return true;
//    }

    private boolean isPlayerOnLogOrBoat() {
        int carCenterX = carX * tileSize + tileSize / 2;
        int carTopY = carY * tileSize;
        int carBottomY = carTopY + tileSize;
        for (riverObject obj : riverObjects) {
            if (obj instanceof Log) {
                Log log = (Log) obj;
                float logLeftX = log.getX();
                float logRightX = logLeftX + log.getLength() * tileSize;
                float logTopY = log.getY();
                float logBottomY = logTopY + tileSize;
                System.out.println();
                if (carTopY == logTopY && carCenterX >= logLeftX && carCenterX <= logRightX) {
                    return true;
                }
            } else if (obj instanceof Boat) {
                Boat boat = (Boat) obj;
                float boatLeftX = boat.getX();
                float boatRightX = boatLeftX + boat.getLength() * tileSize;
                float boatTopY = boat.getY();
                float boatBottomY = boatTopY + tileSize;
                System.out.println();
                if (carTopY == boatTopY && carCenterX >= boatLeftX && carCenterX <= boatRightX) {
                    return true;
                }
            }
        }
        return false;
    }


    public int getCarY() {
        return carY;
    }
    public int getCarX() {
        return carX;
    }
    public void setCarY(int y) {
        carY = y;
    }
    public void setCarX(int x) {
        carX = x;
    }

    public void makeGame() {
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
            for (int j = 0; j < gameGrid.getColumnCount(); j++) {
                ImageView imageView = new ImageView(this);
                imageView.setLayoutParams(new ViewGroup.LayoutParams(tileSize, tileSize));
                imageView.setImageResource(R.drawable.filler);
                gameGrid.addView(imageView);
                tileTypes[i][j] = "filler";
            }
        }
        for (int i = 0; i < 1; i++) {
            for (int j = 0; j < gameGrid.getColumnCount(); j++) {
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
                            imageView.setLayoutParams(new ViewGroup.LayoutParams(
                                    tileSize, tileSize));
                            imageView.setImageResource(R.drawable.grass);
                            gameGrid.addView(imageView);
                            tileTypes[i + 2 + (14 - layers)][j] = "grass";
                        }
                    }
                    layers -= 3;
                } else { //if there is not enough layers to make 3 layers of grass
                    for (int i = 0; i < layers; i++) {
                        for (int j = 0; j < gameGrid.getColumnCount(); j++) {
                            ImageView imageView = new
                                    ImageView(this);
                            imageView.setLayoutParams(new ViewGroup.LayoutParams(
                                    tileSize, tileSize));
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
                        imageView.setLayoutParams(new ViewGroup.LayoutParams(
                                tileSize, tileSize));
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
                            imageView.setLayoutParams(new ViewGroup.LayoutParams(
                                    tileSize, tileSize));
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
                            imageView.setLayoutParams(new ViewGroup.LayoutParams(
                                    tileSize, tileSize));
                            imageView.setImageResource(R.drawable.river);
                            gameGrid.addView(imageView);
                            tileTypes[i + 2 + (14 - layers)][j] = "river";
                        }
                    }
                    layers = 0;
                }
                break;
            default:
                break;
            }

        }

        makeGame2(gameGrid, rand);
    }
    public void makeGame2(GridLayout gameGrid, Random rand) {
        //initialize start tiles
        for (int i = 0; i < 1; i++) {
            for (int j = 0; j < gameGrid.getColumnCount(); j++) {
                ImageView imageView = new ImageView(this);
                imageView.setLayoutParams(new ViewGroup.LayoutParams(tileSize, tileSize));
                imageView.setImageResource(R.drawable.goal);
                gameGrid.addView(imageView);
                tileTypes[i + 16][j] = "start";
            }
        }
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < gameGrid.getColumnCount(); j++) {
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
        grassView = findViewById(R.id.grass_view);
        riverView = findViewById(R.id.river_view);

        leftButton = findViewById(R.id.left_b);
        rightButton = findViewById(R.id.right_b);
        upButton = findViewById(R.id.up_b);
        downButton = findViewById(R.id.down_b);


        createObstacles(rand);


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
        lives = 5;
        if (difficulty.equals("Medium")) {
            heartCount = 4;
            lives = 4;
        } else if (difficulty.equals("Hard")) {
            heartCount = 2;
            lives = 3;
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
                //System.out.println("X: " + carX + " " + "Y: " + carY);
            }
        });
        leftButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                moveLeft();
                //System.out.println("X: " + carX + " " + "Y: " + carY);
            }
        });
        upButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                moveUp();
                //System.out.println("X: " + carX + " " + "Y: " + carY);
            }
        });
        downButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                moveDown();
                //System.out.println("X: " + carX + " " + "Y: " + carY);
            }
        });


    }

    public void createObstacles(Random rand) {
        //Creating cows on grass tiles
        for (int row = 0; row < tileTypes.length; row++) {
            if (tileTypes[row][0].equals("grass")) {
                int cowOrDeer = rand.nextInt(2);
                int vel = rand.nextInt(31);
                int delayDist = rand.nextInt(500) + 10;
                int start = screenW + delayDist;
                int direction = 0;
                if (row % 3 == 0) {
                    start = -100 - delayDist;
                    direction = 1;
                }

                if (cowOrDeer == 0) {
                    Deer deer1 = new Deer(start, row * tileSize, vel, direction);
                    ImageView deer1view = new ImageView(this);
                    deer1view.setImageResource(R.drawable.deer);
                    deer1view.setY(deer1.getY());
                    deer1view.setX(deer1.getX());
                    deer1view.setLayoutParams(new RelativeLayout.LayoutParams(
                            tileSize, tileSize)); // set layout params to match tile size
                    deer1.moveObject(deer1view);
                    grassView.addView(deer1view);
                } else {
                    // Create a new instance of the Cow class and pass in the grass coordinates
                    Cow cow1 = new Cow(start, row * tileSize, vel, direction);
                    ImageView cow1view = new ImageView(this);
                    cow1view.setImageResource(R.drawable.cow);
                    cow1view.setY(cow1.getY());
                    cow1view.setX(cow1.getX());
                    cow1view.setLayoutParams(new RelativeLayout.LayoutParams(
                            tileSize, tileSize)); // set layout params to match tile size
                    cow1.moveObject(cow1view);
                    grassView.addView(cow1view);
                }
            } else if (tileTypes[row][0].equals("river")) { //creating boats and logs on river tiles
                int numObjects = rand.nextInt(4) + 2; //generate random number of objects for the row
                int vel = Math.max(rand.nextInt(31), 0); // clamp velocity to non-negative value
                int delayDist = rand.nextInt(500) + 10;
                int objectCount = 0;
                while (objectCount < numObjects) {
                    int boatOrLog = rand.nextInt(2);
                    if (boatOrLog == 0) { //creating logs
                        int logLength = rand.nextInt(3) + 4; //generate random length of logs
                        GridLayout logViews = new GridLayout(this);
                        logViews.setColumnCount(logLength);
                        Log log = new Log(screenW + delayDist, row * tileSize, vel, logLength);
                        for (int i = 0; i < logLength; i++) {
                            ImageView logView = new ImageView(this);
                            logView.setImageResource(R.drawable.logs);
                            logView.setLayoutParams(new GridLayout.LayoutParams(
                                    new ViewGroup.LayoutParams(tileSize, tileSize)));
                            logViews.addView(logView);
                            //log.addLogView(logView);
                        }
                        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(
                                logLength * tileSize, tileSize);
                        layoutParams.leftMargin = delayDist;
                        layoutParams.topMargin = row * tileSize;
                        FrameLayout frameLayout = new FrameLayout(this);
                        frameLayout.addView(logViews);
                        riverView.addView(frameLayout, layoutParams);
                        log.moveObject(frameLayout, screenW, delayDist);
                        riverObjects.add(log);
                        System.out.println(riverObjects);
                        objectCount += logLength;
                    } else { //creating boats
                        int boatLength = rand.nextInt(2) + 1; //generate random length of boat (1 or 2)
                        GridLayout boatViews = new GridLayout(this);
                        boatViews.setColumnCount(boatLength);
                        Boat boat = new Boat(screenW + delayDist, row * tileSize, vel, boatLength);
                        for (int i = 0; i < boatLength; i++) {
                            ImageView boatView = new ImageView(this);
                            boatView.setImageResource(R.drawable.boat);
                            boatView.setLayoutParams(new GridLayout.LayoutParams(
                                    new ViewGroup.LayoutParams(tileSize, tileSize)));
                            boatViews.addView(boatView);
                        }
                        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(
                                boatLength * tileSize, tileSize);
                        layoutParams.leftMargin = delayDist;
                        layoutParams.topMargin = row * tileSize;
                        FrameLayout frameLayout = new FrameLayout(this);
                        frameLayout.addView(boatViews);
                        riverView.addView(frameLayout, layoutParams);
                        boat.moveObject(frameLayout, screenW, delayDist);
                        riverObjects.add(boat);
                        System.out.println(riverObjects);
                        objectCount += boatLength;
                    }
                }
            }
        }
    }
}



