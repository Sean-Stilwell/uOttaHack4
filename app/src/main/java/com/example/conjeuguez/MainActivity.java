package com.example.conjeuguez;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    boolean tenseChecked;
    boolean timeChecked;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tenseChecked = false;
        timeChecked = false;
        Spinner spinnerTense = findViewById(R.id.spinner3);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.tenses, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spinnerTense.setAdapter(adapter);
        spinnerTense.setOnItemSelectedListener(this);

        Spinner spinnerTime = findViewById(R.id.spinner4);
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this, R.array.times, android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spinnerTime.setAdapter(adapter2);
        spinnerTime.setOnItemSelectedListener(this);
    }

    /**
     * Starts the game using the currently selected settings
     * @param v - The button to start the game
     */
    public void playGameButton(View v){
        Intent intent = new Intent(MainActivity.this, PlayGame.class);
        startActivity(intent);
    }

    public void websiteButton(View v){
        Uri uriUrl = Uri.parse("https://seanstilwell.ca");
        Intent launchBrowser = new Intent(Intent.ACTION_VIEW, uriUrl);
        startActivity(launchBrowser);
    }

    public void githubButton(View v){
        Uri uriUrl = Uri.parse("https://github.com/Sean-Stilwell/uOttaHack4");
        Intent launchBrowser = new Intent(Intent.ACTION_VIEW, uriUrl);
        startActivity(launchBrowser);
    }

    /**
     * Opens the screen to view stats
     * @param v - The button to view the start
     */
    public void statsButton(View v){
        Intent intent = new Intent(MainActivity.this, Stats.class);
        startActivity(intent);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String val = parent.getItemAtPosition(position).toString();
        if (val.equals("Présent")){
            PlayGame.tenseVal = 0;
        } else if (val.equals("Imparfait")){
            PlayGame.tenseVal = 1;
        } else if (val.equals("Passé composé")){
            PlayGame.tenseVal = 2;
        } else if (val.equals("Futur")){
            PlayGame.tenseVal = 3;
        } else if (val.equals("Conditionnel (Présent)")){
            PlayGame.tenseVal = 4;
        }

        if (val.equals("1 minute")){
            PlayGame.time = 60;
            PlayGame.startingTime = 60;
        } else if (val.equals("2 minutes")){
            PlayGame.time = 120;
            PlayGame.startingTime = 120;
        } else if (val.equals("5 minutes")){
            PlayGame.time = 500;
            PlayGame.startingTime = 500;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}