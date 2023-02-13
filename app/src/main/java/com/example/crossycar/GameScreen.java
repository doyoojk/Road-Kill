package com.example.crossycar;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class GameScreen extends AppCompatActivity {

    private TextView playerNameView;
    //private TextView carSelectionView;
    //private ImageView[] difficulty = new ImageView[5];
    private ImageView[] heartIcons = new ImageView[5];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_screen);

        // Get references to the views
        playerNameView = findViewById(R.id.player_name);
        ImageView carIconView = findViewById(R.id.car_icon);
        heartIcons[0] = findViewById(R.id.heart_icon_1);
        heartIcons[1] = findViewById(R.id.heart_icon_2);
        heartIcons[2] = findViewById(R.id.heart_icon_3);
        heartIcons[3] = findViewById(R.id.heart_icon_4);
        heartIcons[4] = findViewById(R.id.heart_icon_5);

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
    }
}