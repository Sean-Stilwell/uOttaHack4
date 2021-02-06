package com.example.conjeuguez;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

public class Stats extends AppCompatActivity {
    SharedPreferences prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stats);
        prefs = this.getSharedPreferences("prefsKey", Context.MODE_PRIVATE);

        // PRESENTS STATS
        TextView presAttemptsView = (TextView) findViewById(R.id.textView15);
        TextView presCorrectView = (TextView) findViewById(R.id.textView17);
        TextView presRateView = (TextView) findViewById(R.id.textView19);
        float totalAttempts = (float) prefs.getInt("Présentattempts", 0);
        float totalCorrect = (float) prefs.getInt("Présentcorrect", 0);
        int successRate = 0;
        if (totalAttempts > 0){ successRate = (int) ((totalCorrect /  totalAttempts) * 100);}
        presAttemptsView.setText(Float.toString(totalAttempts));
        presCorrectView.setText(Float.toString(totalCorrect));
        presRateView.setText(Integer.toString(successRate) + "%");

        // PRESENTS STATS
        TextView impAttemptsView = (TextView) findViewById(R.id.textView24);
        TextView impCorrectView = (TextView) findViewById(R.id.textView26);
        TextView impRateView = (TextView) findViewById(R.id.textView22);
        float impAttempts = (float)  prefs.getInt("Imparfaitattempts", 0);
        float impCorrect = (float) prefs.getInt("Imparfaitcorrect", 0);
        int impSuccessRate = 0;
        if (impAttempts > 0){ impSuccessRate = (int) ((impCorrect / impAttempts) * 100);}
        impAttemptsView.setText(Float.toString(impAttempts));
        impCorrectView.setText(Float.toString(impCorrect));
        impRateView.setText(Integer.toString(impSuccessRate) + "%");

        // PASSE COMPOSE STATS
        TextView pcAttemptsView = (TextView) findViewById(R.id.textView28);
        TextView pcCorrectView = (TextView) findViewById(R.id.textView30);
        TextView pcRateView = (TextView) findViewById(R.id.textView32);
        float pcAttempts = (float) prefs.getInt("Passé composéattempts", 0);
        float pcCorrect = (float) prefs.getInt("Passé composécorrect", 0);
        int pcsuccessRate = 0;
        if (pcAttempts > 0){ pcsuccessRate = (int) ((pcCorrect / pcAttempts) * 100);}
        pcAttemptsView.setText(Float.toString(pcAttempts));
        pcCorrectView.setText(Float.toString(pcCorrect));
        pcRateView.setText(Integer.toString(pcsuccessRate) + "%");

        // FUTUR STATS
        TextView futurAttemptsView = (TextView) findViewById(R.id.textView37);
        TextView futurCorrectView = (TextView) findViewById(R.id.textView39);
        TextView futurRateView = (TextView) findViewById(R.id.textView41);
        float futurAttempts = (float) prefs.getInt("Futurattempts", 0);
        float futurCorrect = (float) prefs.getInt("Futurcorrect", 0);
        int futursuccessRate = 0;
        if (futurAttempts > 0) { futursuccessRate = (int) ((futurCorrect / futurAttempts) * 100); }
        futurAttemptsView.setText(Float.toString(futurAttempts));
        futurCorrectView.setText(Float.toString(futurCorrect));
        futurRateView.setText(Integer.toString(futursuccessRate) + "%");
    }
}