package com.example.conjeuguez;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;

public class MainActivity extends AppCompatActivity {

    public static int time;
    public static int tense; // 1 = present, 2 = imperfect, 3 = past composed, 4 = future

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * Starts the game using the currently selected settings
     * @param v - The button to start the game
     */
    public void playGameButton(View v){
        Intent intent = new Intent(MainActivity.this, PlayGame.class);
        startActivity(intent);
    }

    /**
     * Controls the verb tense being played and sets it to an instance variable
     * @param v - The button that was clicked
     * */
    public void radioButtonTense(View v){
        boolean checked = ((RadioButton) v).isChecked();

        switch(v.getId()){
            case R.id.radioButton:
                tense = 1;
                break;
            case R.id.radioButton2:
                tense = 2;
                break;
            case R.id.radioButton3:
                tense = 3;
                break;
            case R.id.radioButton4:
                tense = 4;
                break;
        }
    }

    /**
     * Controls the amount of time for the game and sets it to an instance variable
     * @param v - The button that was clicked
     */
    public void radioButtonTime(View v){
        boolean checked = ((RadioButton) v).isChecked();

        switch(v.getId()){
            case R.id.radioButton5:
                time = 1;
                break;
            case R.id.radioButton6:
                time = 2;
                break;
            case R.id.radioButton7:
                time = 5;
                break;
        }
    }
}