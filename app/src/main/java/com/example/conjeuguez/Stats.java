package com.example.conjeuguez;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Stats extends AppCompatActivity {
    SharedPreferences prefs; // Allows access to the preferences for displaying

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stats);
        prefs = this.getSharedPreferences("prefsKey", Context.MODE_PRIVATE);
        updateView();
    }

    /**
     * Updates the view to display the current stats on launch or after points reset
     */
    private void updateView(){
        // PRESENTS STATS
        TextView presAttemptsView = (TextView) findViewById(R.id.textView15);
        TextView presCorrectView = (TextView) findViewById(R.id.textView17);
        TextView presRateView = (TextView) findViewById(R.id.textView19);
        float totalAttempts = (float) prefs.getInt("Présentattempts", 0);
        float totalCorrect = (float) prefs.getInt("Présentcorrect", 0);
        int successRate = 0;
        if (totalAttempts > 0){ successRate = (int) ((totalCorrect /  totalAttempts) * 100);}
        presAttemptsView.setText(Integer.toString((int) totalAttempts));
        presCorrectView.setText(Integer.toString((int) totalCorrect));
        presRateView.setText(Integer.toString(successRate) + "%");

        // PRESENTS STATS
        TextView impAttemptsView = (TextView) findViewById(R.id.textView24);
        TextView impCorrectView = (TextView) findViewById(R.id.textView26);
        TextView impRateView = (TextView) findViewById(R.id.textView22);
        float impAttempts = (float)  prefs.getInt("Imparfaitattempts", 0);
        float impCorrect = (float) prefs.getInt("Imparfaitcorrect", 0);
        int impSuccessRate = 0;
        if (impAttempts > 0){ impSuccessRate = (int) ((impCorrect / impAttempts) * 100);}
        impAttemptsView.setText(Integer.toString((int) impAttempts));
        impCorrectView.setText(Integer.toString((int) impCorrect));
        impRateView.setText(Integer.toString(impSuccessRate) + "%");

        // PASSE COMPOSE STATS
        TextView pcAttemptsView = (TextView) findViewById(R.id.textView28);
        TextView pcCorrectView = (TextView) findViewById(R.id.textView30);
        TextView pcRateView = (TextView) findViewById(R.id.textView32);
        float pcAttempts = (float) prefs.getInt("Passé composéattempts", 0);
        float pcCorrect = (float) prefs.getInt("Passé composécorrect", 0);
        int pcsuccessRate = 0;
        if (pcAttempts > 0){ pcsuccessRate = (int) ((pcCorrect / pcAttempts) * 100);}
        pcAttemptsView.setText(Integer.toString((int) pcAttempts));
        pcCorrectView.setText(Integer.toString((int) pcCorrect));
        pcRateView.setText(Integer.toString(pcsuccessRate) + "%");

        // FUTUR STATS
        TextView futurAttemptsView = (TextView) findViewById(R.id.textView37);
        TextView futurCorrectView = (TextView) findViewById(R.id.textView39);
        TextView futurRateView = (TextView) findViewById(R.id.textView41);
        float futurAttempts = (float) prefs.getInt("Futurattempts", 0);
        float futurCorrect = (float) prefs.getInt("Futurcorrect", 0);
        int futursuccessRate = 0;
        if (futurAttempts > 0) { futursuccessRate = (int) ((futurCorrect / futurAttempts) * 100); }
        futurAttemptsView.setText(Integer.toString((int) futurAttempts));
        futurCorrectView.setText(Integer.toString((int) futurCorrect));
        futurRateView.setText(Integer.toString(futursuccessRate) + "%");

        // CONDITIONNEL (PRESENT) STATS
        TextView condAttemptsView = (TextView) findViewById(R.id.textView33);
        TextView condCorrectView = (TextView) findViewById(R.id.textView35);
        TextView condRateView = (TextView) findViewById(R.id.textView44);
        float condAttempts = (float) prefs.getInt("Conditionnel (Présent)attempts", 0);
        float condCorrect = (float) prefs.getInt("Conditionnel (Présent)correct", 0);
        int condsuccessRate = 0;
        if (condAttempts > 0) { condsuccessRate = (int) ((condCorrect / condAttempts) * 100); }
        condAttemptsView.setText(Integer.toString((int) condAttempts));
        condCorrectView.setText(Integer.toString((int) condCorrect));
        condRateView.setText(Integer.toString(condsuccessRate) + "%");

        // SUBJUNCTIVE (PRESENT) STATS
        TextView subAttemptsView = (TextView) findViewById(R.id.textView50);
        TextView subCorrectView = (TextView) findViewById(R.id.textView52);
        TextView subRateView = (TextView) findViewById(R.id.textView54);
        float subAttempts = (float) prefs.getInt("Subjonctifattempts", 0);
        float subCorrect = (float) prefs.getInt("Subjonctifcorrect", 0);
        int subsuccessRate = 0;
        if (subAttempts > 0) { subsuccessRate = (int) ((subCorrect / subAttempts) * 100); }
        subAttemptsView.setText(Integer.toString((int) subAttempts));
        subCorrectView.setText(Integer.toString((int) subCorrect));
        subRateView.setText(Integer.toString(subsuccessRate) + "%");

        // COND (PASSE) STATS
        TextView condpassAttemptsView = (TextView) findViewById(R.id.textView58);
        TextView condpassCorrectView = (TextView) findViewById(R.id.textView64);
        TextView condpassRateView = (TextView) findViewById(R.id.textView72);
        float condpassAttempts = (float) prefs.getInt("Conditionnel (Passé)attempts", 0);
        float condpassCorrect = (float) prefs.getInt("Conditionnel (Passé)correct", 0);
        int condpasssuccessRate = 0;
        if (futurAttempts > 0) { condpasssuccessRate = (int) ((condpassCorrect / condpassAttempts) * 100); }
        condpassAttemptsView.setText(Integer.toString((int) condpassAttempts));
        condpassCorrectView.setText(Integer.toString((int) condpassCorrect));
        condpassRateView.setText(Integer.toString(condpasssuccessRate) + "%");

        // FUTUR ANTERIEUR STATS
        TextView futAntAttemptsView = (TextView) findViewById(R.id.textView60);
        TextView futAntCorrectView = (TextView) findViewById(R.id.textView66);
        TextView futAntRateView = (TextView) findViewById(R.id.textView73);
        float futAntAttempts = (float) prefs.getInt("Futur antérieurattempts", 0);
        float futAntCorrect = (float) prefs.getInt("Futur antérieurcorrect", 0);
        int futAntsuccessRate = 0;
        if (condAttempts > 0) { futAntsuccessRate = (int) ((futAntCorrect / futAntAttempts) * 100); }
        futAntAttemptsView.setText(Integer.toString((int) futAntAttempts));
        futAntCorrectView.setText(Integer.toString((int) futAntCorrect));
        futAntRateView.setText(Integer.toString(futAntsuccessRate) + "%");

        // PLUS QUE PARFAIT STATS
        TextView pqpAttemptsView = (TextView) findViewById(R.id.textView62);
        TextView pqpCorrectView = (TextView) findViewById(R.id.textView68);
        TextView pqpRateView = (TextView) findViewById(R.id.textView74);
        float pqpAttempts = (float) prefs.getInt("Plus-que-parfaitattempts", 0);
        float pqpCorrect = (float) prefs.getInt("Plus-que-parfaitcorrect", 0);
        int pqpsuccessRate = 0;
        if (pqpAttempts > 0) { pqpsuccessRate = (int) ((pqpCorrect / pqpAttempts) * 100); }
        pqpAttemptsView.setText(Integer.toString((int) pqpAttempts));
        pqpCorrectView.setText(Integer.toString((int) pqpCorrect));
        pqpRateView.setText(Integer.toString(pqpsuccessRate) + "%");
    }

    /**
     * On clicking the button, resets all of the user's scores to 0.
     * @param v - The button being clicked.
     */
    public void resetstats(View v){
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt("Présentattempts", 0);
        editor.putInt("Présentcorrect", 0);
        editor.putInt("Imparfaitattempts", 0);
        editor.putInt("Imparfaitcorrect", 0);
        editor.putInt("Passé composéattempts", 0);
        editor.putInt("Passé composécorrect", 0);
        editor.putInt("Futurattempts", 0);
        editor.putInt("Futurcorrect", 0);
        editor.putInt("Conditionnel (Présent)attempts", 0);
        editor.putInt("Conditionnel (Présent)correct", 0);
        editor.commit(); // We have to commit our changes to save them.
        updateView();
    }
}