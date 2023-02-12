package com.example.crossycar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ChoosePlayer extends AppCompatActivity {
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chooseplayer);

        button = findViewById(R.id.choose_player_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGameScreen();
            }
        });
    }

    public void openGameScreen() {
        Intent GameScreen = new Intent(this, GameScreen.class);
        startActivity(GameScreen);
    }
}