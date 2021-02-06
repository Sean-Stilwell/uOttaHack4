package com.example.conjeuguez;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Random;

import com.opencsv.CSVReader;

public class PlayGame extends AppCompatActivity {
    public static int tenseVal;
    public static int time;

    String tense;
    String verb;
    String pronoun;
    int pronounVal;
    String answer;
    int points;
    SharedPreferences prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_game);
        points = 0;
        newPuzzle();

        if (tenseVal == 0){
            tense = "Présent";
        } else if (tenseVal == 1){
            tense = "Imparfait";
        } else if (tenseVal == 2){
            tense = "Passé composé";
        } else if (tenseVal == 3){
            tense = "Futur";
        } else {
            tense = "Présent";
        }
        prefs = this.getSharedPreferences("prefsKey", Context.MODE_PRIVATE);
        updateView();
    }

    public void submitButton(View v){
        EditText box = (EditText) findViewById(R.id.answerBox);

        // We keep track of how users perform in each verb tense.
        int totalAttempts = prefs.getInt(tense + "attempts", 0);
        totalAttempts++;
        int totalCorrect = prefs.getInt(tense + "correct", 0);

        // If the user gets an answer right, they are awarded points.
        if (box.getText().toString().equals(answer.trim())){
            points = points + 10;
            totalCorrect++;
        }
        else {
            points = points - 5;
        }

        // We then update the user's total correct and total attempted scores
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt(tense+"attempts", totalAttempts);
        editor.putInt(tense+"correct", totalCorrect);

        newPuzzle();
    }

    private void newPuzzle(){
        try {
            InputStream inputStream = getResources().openRawResource(R.raw.present);
            CSVFile csvFile = new CSVFile(inputStream);
            List list = csvFile.read();

            // Chooses a verb to be used for the solution
            Random rand = new Random();
            int verbPos = rand.nextInt(list.size());
            String[] array = (String[]) list.get(verbPos);
            verb = array[0];
            generatePronoun();
            answer = array[pronounVal + 1];
            updateView();

        } catch (Exception e){
            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    private void updateView(){
        TextView pointsView = (TextView) findViewById(R.id.textView10);
        pointsView.setText(Integer.toString(points));
        TextView timeView = (TextView) findViewById(R.id.textView12);
        timeView.setText(Integer.toString(time));
        TextView verbView = (TextView) findViewById(R.id.textView4);
        verbView.setText(verb);
        TextView tenseView = (TextView) findViewById(R.id.textView5);
        tenseView.setText(tense);
        TextView pronounView = (TextView) findViewById(R.id.textView13);
        pronounView.setText(pronoun);
    }

    private void generatePronoun(){
        Random rand = new Random();
        pronounVal = rand.nextInt(8); // Generates from 0 to 7
        int giveNameProb = rand.nextInt(10);
        switch (pronounVal){
            case (0):
                pronoun = "Je";
                break;
            case (1):
                pronoun = "Tu";
                break;
            case (2):
                if (giveNameProb < 6){
                    pronoun = "Il";
                }
                else if (giveNameProb < 7){
                    pronoun = "Robert";
                }
                else if (giveNameProb < 8){
                    pronoun = "Jacques";
                }
                else{
                    pronoun = "Louis";
                }
                break;
            case (3):
                if (giveNameProb < 6){
                    pronoun = "Elle";
                }
                else if (giveNameProb < 7){
                    pronoun = "Sophie";
                }
                else if (giveNameProb < 8){
                    pronoun = "Madeleine";
                }
                else{
                    pronoun = "Charlotte";
                }
                break;
            case (4):
                if (giveNameProb < 7){
                    pronoun = "Nous";
                }
                else if (giveNameProb < 8){
                    pronoun = "Hugo et moi";
                }
                else{
                    pronoun = "Philippe et moi";
                }
                break;
            case (5):
                pronoun = "Vous";
                break;
            case (6):
                if (giveNameProb < 7){
                    pronoun = "Ils";
                }
                else if (giveNameProb < 8){
                    pronoun = "Hugo et Jean";
                }
                else{
                    pronoun = "Philippe et Sophie";
                }
                break;
            case (7):
                if (giveNameProb < 7){
                    pronoun = "Elles";
                }
                else if (giveNameProb < 8){
                    pronoun = "Sophie et Veronique";
                }
                else{
                    pronoun = "Juliette et Genevieve";
                }
                break;
        }
    }
}