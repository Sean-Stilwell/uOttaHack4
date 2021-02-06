package com.example.conjeuguez;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    boolean tenseChecked;
    boolean timeChecked;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tenseChecked = false;
        timeChecked = false;
    }

    /**
     * Starts the game using the currently selected settings
     * @param v - The button to start the game
     */
    public void playGameButton(View v){
        if (tenseChecked && timeChecked){
            Intent intent = new Intent(MainActivity.this, PlayGame.class);
            startActivity(intent);
        } else if (!tenseChecked && !timeChecked){
            Toast.makeText(getApplicationContext(), "Please select a verb tense and time limit to play.", Toast.LENGTH_LONG).show();
        } else if (!tenseChecked){
            Toast.makeText(getApplicationContext(), "Please select a verb tense!", Toast.LENGTH_LONG).show();
        } else if (!timeChecked){
            Toast.makeText(getApplicationContext(), "Please select a time limit!", Toast.LENGTH_LONG).show();
        }
    }

    /**
     * Opens the screen to view stats
     * @param v - The button to view the start
     */
    public void statsButton(View v){
        Intent intent = new Intent(MainActivity.this, Stats.class);
        startActivity(intent);
    }

    /**
     * Controls the verb tense being played and sets it to an instance variable
     * @param v - The button that was clicked
     * */
    public void radioButtonTense(View v){
        tenseChecked = true;

        switch(v.getId()){
            case R.id.radioButton:
                PlayGame.tenseVal = 0;
                break;
            case R.id.radioButton2:
                PlayGame.tenseVal = 1;
                break;
            case R.id.radioButton3:
                PlayGame.tenseVal = 2;
                break;
            case R.id.radioButton4:
                PlayGame.tenseVal = 3;
                break;
        }
    }

    /**
     * Controls the amount of time for the game and sets it to an instance variable
     * @param v - The button that was clicked
     */
    public void radioButtonTime(View v){
        timeChecked = true;
        switch(v.getId()){
            case R.id.radioButton5:
                PlayGame.time = 60;
                PlayGame.startingTime = 60;
                break;
            case R.id.radioButton6:
                PlayGame.time = 120;
                PlayGame.startingTime = 120;
                break;
            case R.id.radioButton7:
                PlayGame.time = 300;
                PlayGame.startingTime = 300;
                break;
        }
    }
}