package com.example.crossycar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
//import android.graphics.RadialGradient;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class ChoosePlayer extends AppCompatActivity {
//    private Button button;
    private RadioGroup carSelectionGroup;
    private RadioGroup difficultySelectionGroup;
    private EditText playerNameInput;
    private Button saveButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chooseplayer);

//        button = findViewById(R.id.choose_player_button);
        carSelectionGroup = findViewById(R.id.car_selection_group);
        difficultySelectionGroup = findViewById(R.id.difficulty_selection_group);
        playerNameInput = findViewById(R.id.player_name_input);
        saveButton = findViewById(R.id.save_button);
//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                openGameScreen();
//            }
//        });

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get the data from the input fields
                int selectedCarId = carSelectionGroup.getCheckedRadioButtonId();
                int selectedDifficultyId = difficultySelectionGroup.getCheckedRadioButtonId();
                String playerName = playerNameInput.getText().toString().trim();

                // Validate the input
                if (playerName.isEmpty()) {
                    playerNameInput.setError("Name cannot be empty");
                    return;
                } else if (playerName == null) {
                    playerNameInput.setError("Name cannot be null");
                }

                // Determine the selected car
                String carSelection = "Car 1";
                if (selectedCarId == R.id.car_2) {
                    carSelection = "Car 2";
                } else if (selectedCarId == R.id.car_3) {
                    carSelection = "Car 3";
                }

                // Determine the selected difficulty
                String difficulty = "Easy";
                if (selectedDifficultyId == R.id.difficulty_medium) {
                    difficulty = "Medium";
                } else if (selectedDifficultyId == R.id.difficulty_hard) {
                    difficulty = "Hard";
                }

                // Start the PlayerDisplayActivity and pass the data
                Intent GameScreen = new Intent(ChoosePlayer.this, GameScreen.class);
                GameScreen.putExtra("player_name", playerName);
                GameScreen.putExtra("car_selection", carSelection);
                GameScreen.putExtra("difficulty", difficulty);
                startActivity(GameScreen);
            }
        });

    }
//    public void openGameScreen() {
//        Intent GameScreen = new Intent(this, GameScreen.class);
//        GameScreen.putExtra("player_name", playerName);
//        GameScreen.putExtra("car_selection", carSelection);
//        GameScreen.putExtra("difficulty", difficulty);
//        startActivity(GameScreen);
//    }
}