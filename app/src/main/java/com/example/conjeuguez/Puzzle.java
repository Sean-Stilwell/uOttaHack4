package com.example.conjeuguez;

import android.os.Environment;

import com.opencsv.CSVReader;

import java.io.File;
import java.io.IOException;
import java.io.FileReader;
import java.util.Random;

public class Puzzle {
    private String pronoun;
    private int pronounVal; // Allows us to find the correct conjugation for the verb
    private String verb;
    private String answer;

    public Puzzle(){
        generatePronoun();
        chooseAnswer();
    }

    public String getPronoun() { return this.pronoun; }
    public String getVerb() { return this.verb; }
    public String getAnswer() { return this.answer; }

    private void chooseAnswer(){
        try {
            // Opens the file and prepares it to read
            File csvfile = new File(Environment.getExternalStorageDirectory() + "/present.csv");
            switch (MainActivity.tense){
                case (0):
                    csvfile = new File(Environment.getExternalStorageDirectory() + "/present.csv");
                    break;
                case (1):
                    csvfile = new File(Environment.getExternalStorageDirectory() + "/imparfait.csv");
                    break;
                case (2):
                    csvfile = new File(Environment.getExternalStorageDirectory() + "/passedcomposed.csv");
                    break;
                case (3):
                    csvfile = new File(Environment.getExternalStorageDirectory() + "/futur.csv");
                    break;
            }
            CSVReader reader = new CSVReader(new FileReader(csvfile.getAbsolutePath()));
            String[] nextLine = reader.readNext();

            // Chooses a random verb and finds the proper line for it
            Random rand = new Random();
            int chooseVerb = rand.nextInt(50);
            for (int i = 1; i < chooseVerb; i++){
                nextLine = reader.readNext();
            }
            verb = nextLine[0]; // The unconjugated verb can be found at the first spot in the CSV
            answer = nextLine[pronounVal]; // Finds the answer using the chosen pronoun.
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
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
