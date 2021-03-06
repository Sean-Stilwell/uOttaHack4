package com.example.conjeuguez;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import java.io.InputStream;
import java.util.List;
import java.util.Random;

public class PlayGame extends AppCompatActivity {
    // These static variables are used in the Main Activity to control the game
    public static int tenseVal;
    public static int time;
    public static int startingTime;

    // Values to be printed on the game
    String tense;
    String verb;
    String pronoun;
    String answer;

    int pronounVal; // Position in the array of a pronoun
    int points; // Number of points earned by a player in a game
    int correct;
    int attempts;
    SharedPreferences prefs; // Allows us to store results
    boolean running = true; // When true, the timer is counting down
    Handler handler; // For controlling the timer

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_game);
        points = 0;
        correct = 0;
        attempts = 0;
        newPuzzle(); // The game immediately starts a puzzle when the user starts

        // Converts the static variable used by MainActivity into what we want to display
        if (tenseVal == 0){
            tense = "Présent";
        } else if (tenseVal == 1){
            tense = "Imparfait";
        } else if (tenseVal == 2){
            tense = "Passé composé";
        } else if (tenseVal == 3){
            tense = "Futur";
        } else if (tenseVal == 4){
            tense = "Conditionnel (Présent)";
        } else if (tenseVal == 5) {
            tense = "Subjonctif";
        } else if (tenseVal == 6) {
            tense = "Conditionnel (Passé)";
        } else if (tenseVal == 7) {
            tense = "Futur antérieur";
        } else if (tenseVal == 8) {
            tense = "Plus-que-parfait";
        } else {
            tense = "Présent";
        }

        prefs = this.getSharedPreferences("prefsKey", Context.MODE_PRIVATE); // Allows us to store user info and results
        runTimer(); // Starts the timer
        updateView(); // Updates all text displayed on the screen (verb tense, points, etc)
    }

    @Override
    protected void onPause() {
        super.onPause(); // When the game is paused, we stop the timer (fixes a weird glitch where a second timer is also counting down)
        running = false;
    }

    @Override
    protected void onResume(){
        super.onResume();
        running = true; // Restart the timer when a user rejoins
        time = startingTime; // Reset the time to their beginning
        newPuzzle(); // Create a new puzzle
    }

    /**
     * Method that runs when the Submit button is clicked
     * @param v - The button that was clicked
     */
    public void submitButton(View v){
        // We save the Edit Text box as a variable so we can clear it after a submission.
        EditText box = (EditText) findViewById(R.id.answerBox);
        TextView resultView1 = (TextView) findViewById(R.id.textView55);
        TextView resultView2 = (TextView) findViewById(R.id.textView56);

        // We keep track of how users perform in each verb tense.
        int totalAttempts = prefs.getInt(tense + "attempts", 0);
        totalAttempts++;
        int totalCorrect = prefs.getInt(tense + "correct", 0);

        // If the user gets an answer right, they are awarded points.
        if (box.getText().toString().toLowerCase().trim().equals(answer.trim())){
            resultView1.setVisibility(View.GONE);
            resultView2.setVisibility(View.GONE);
            points = points + 10;
            correct++;
            totalCorrect++;
        }
        else { // If the user is wrong, their points are decremented
            points = points - 5;
            resultView1.setVisibility(View.VISIBLE);
            resultView2.setVisibility(View.VISIBLE);
            resultView2.setText(answer);
        }
        attempts++;

        // We then update the user's total correct and total attempted scores
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt(tense+"attempts", totalAttempts);
        editor.putInt(tense+"correct", totalCorrect);
        editor.commit(); // We have to commit our changes to save them.

        newPuzzle(); // A new puzzle is created and shown.
    }

    /**
     * When a user finishes, or decides to in the middle of the game, they can restart the game from the starting time.
     * @param v - The button that was clicked.
     */
    public void resetButton(View v){
        time = startingTime;
        newPuzzle();
        correct = 0;
        attempts = 0;
        points = 0;
        Button button = (Button) findViewById(R.id.button2);
        button.setEnabled(true);
        EditText textbox = (EditText) findViewById(R.id.answerBox);
        textbox.setEnabled(true);
        running = true;
        updateView();
    }

    /**
     * Generates a new puzzle, pulling from supporting CSV files
     */
    private void newPuzzle(){
        // The user's current input must first be cleared
        EditText box = (EditText) findViewById(R.id.answerBox);
        box.setText("");

        try { // We then access the CSV file that corresponds to the current chosen tense.
            InputStream inputStream = getResources().openRawResource(R.raw.present);
            if (tenseVal == 1) { inputStream = getResources().openRawResource(R.raw.imparfait); }
            if (tenseVal == 2) { inputStream = getResources().openRawResource(R.raw.passecompose); }
            if (tenseVal == 3) { inputStream = getResources().openRawResource(R.raw.futur); }
            if (tenseVal == 4) { inputStream = getResources().openRawResource(R.raw.conditionnel); }
            if (tenseVal == 5) { inputStream = getResources().openRawResource(R.raw.subjonctif); }
            if (tenseVal == 6) { inputStream = getResources().openRawResource(R.raw.conditionnelpasse); }
            if (tenseVal == 7) { inputStream = getResources().openRawResource(R.raw.futuranterieur); }
            if (tenseVal == 8) { inputStream = getResources().openRawResource(R.raw.plusqueparfait); }

            // We use this custom class to read that CSV file, and then convert it to list format.
            CSVFile csvFile = new CSVFile(inputStream);
            List list = csvFile.read();

            // Chooses a random verb to be used for the problem
            Random rand = new Random();
            int verbPos = rand.nextInt(list.size());
            String[] array = (String[]) list.get(verbPos); // Retrieves the row from the CSV corresponding to the random verb

            verb = array[0]; // The unconjugated verb is found in the 0 position
            generatePronoun(); // We select a random pronoun to be used in the solution
            answer = array[pronounVal + 1].trim(); // We then select the answer that corresponds to the pronoun.
            if (pronoun.equals("Je")){
                validateJe();
            }
            updateView(); // We update the view to show the verb and pronoun needed.
        } catch (Exception e){
            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    /**
     * Changes the French singular first-person to J' if the answer starts with a vowel.
     */
    private void validateJe(){
        if (answer.charAt(0) == 'a' || answer.charAt(0) == 'e' || answer.charAt(0) == 'i' || answer.charAt(0) == 'o' || answer.charAt(0) == 'u' || answer.charAt(0) == 'é'){
            pronoun = "J'";
        }
    }

    /**
     * Updates the game view after launch or when a new puzzle is created
     */
    private void updateView(){
        TextView pointsView = (TextView) findViewById(R.id.textView10);
        pointsView.setText(Integer.toString(points));
        TextView verbView = (TextView) findViewById(R.id.textView4);
        verbView.setText(verb);
        TextView tenseView = (TextView) findViewById(R.id.textView5);
        tenseView.setText(tense);
        TextView pronounView = (TextView) findViewById(R.id.textView13);
        pronounView.setText(pronoun);
        TextView timeView = (TextView)findViewById(R.id.textView12);
        timeView.setText(Integer.toString(time));
        TextView correctAnswers = (TextView) findViewById(R.id.textView46);
        correctAnswers.setText(Integer.toString(correct));
        TextView attemptedAnswers = (TextView) findViewById(R.id.textView48);
        attemptedAnswers.setText(Integer.toString(attempts));
    }

    /**
     * Code to run the countdown timer
     */
    private void runTimer(){
        // Get the text view.
        final TextView timeView = (TextView)findViewById(R.id.textView12);

        // Creates a new Handler
        handler = new Handler();

        // Call the post() method, passing in a new Runnable. The post() method processes
        // code without a delay, so the code in the Runnable will run almost immediately.
        handler.post(new Runnable() {
            @Override
            public void run() {
                // Set the text view text.
                timeView.setText(Integer.toString(time));
                if (running) {
                    time--;
                }

                TextView resultView1 = (TextView) findViewById(R.id.textView45);
                TextView resultView2 = (TextView) findViewById(R.id.textView46);
                TextView resultView3 = (TextView) findViewById(R.id.textView47);
                TextView resultView4 = (TextView) findViewById(R.id.textView48);
                EditText textbox = (EditText) findViewById(R.id.answerBox);
                Button button = (Button) findViewById(R.id.button2);

                // When time is out, we disable the user's ability to add a new solution.
                if (time <= 0){
                    running = false;
                    button.setEnabled(false);
                    textbox.setEnabled(false);
                    resultView1.setVisibility(View.VISIBLE);
                    resultView2.setVisibility(View.VISIBLE);
                    resultView3.setVisibility(View.VISIBLE);
                    resultView4.setVisibility(View.VISIBLE);
                }
                else {
                    button.setEnabled(true);
                    textbox.setEnabled(true);
                    resultView1.setVisibility(View.INVISIBLE);
                    resultView2.setVisibility(View.INVISIBLE);
                    resultView3.setVisibility(View.INVISIBLE);
                    resultView4.setVisibility(View.INVISIBLE);
                }

                // Post the code again with a delay of 1 second.
                handler.postDelayed(this, 1000);
            }
        });
    }

    /**
     * Generates a new pronoun to be used in the solution
     */
    private void generatePronoun(){
        Random rand = new Random();
        pronounVal = rand.nextInt(8); // Generates from 0 to 7 to choose what tense is used
        int giveNameProb = rand.nextInt(10); // Decides whether the generic term will be used or a name.
        switch (pronounVal){
            case (0): // 1st-person singular
                pronoun = "Je";
                break;
            case (1): // 2nd-person singular
                pronoun = "Tu";
                break;
            case (2): // 3rd-person male singular, names can be generated.
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
            case (3): // 3rd-person female singular, names can be generated.
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
            case (4):  // 1st-person plural, names can be generated.
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
            case (5): // 2nd-person plural
                pronoun = "Vous";
                break;
            case (6): // 3rd-person non-feminine plural, names can be generated
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
            case (7): // 3rd-person feminine plural, names can be generated
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