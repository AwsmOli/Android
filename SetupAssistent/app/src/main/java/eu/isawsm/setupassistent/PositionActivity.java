package eu.isawsm.setupassistent;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

import Persitancy.CSVParser;
import Persitancy.Problem;

public class PositionActivity extends Activity {

    private ArrayList<Problem> problems;
    private String problematicCharacteristic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_position);

        Intent data = getIntent();
        problems = (ArrayList<Problem>) data.getSerializableExtra("Problems");
        problematicCharacteristic = data.getStringExtra("characteristic");

        TextView textView = (TextView) findViewById(R.id.textView);
        textView.setText(getString(R.string.where_do_you_experience) +" " + problematicCharacteristic +getString(R.string.ing) + "?");
        System.out.println("Found " + problems.size() + " problems!");
    }

    public void btnCornerEntryClicked(View view) {
        callNextActivity(CSVParser.CORNER_ENTRY, ThrottlePositionActivity.class);
    }

    public void btnMidCornerClicked(View view) {
        if(problematicCharacteristic.equals(CSVParser.TRACTION_ROLL)){
            callNextActivity(CSVParser.MID_CORNER, SuggestionActivity.class);
        } else {
            callNextActivity(CSVParser.MID_CORNER, ThrottlePositionActivity.class);
        }
    }

    public void btnCornerExitClicked(View view) {
        callNextActivity(CSVParser.CORNER_EXIT, SuggestionActivity.class);
    }

    public void btnBreakingClicked(View view) {
        callNextActivity(CSVParser.BREAKING, SuggestionActivity.class);
    }
    private void callNextActivity(String button, Class activity) {
        Intent intent = new Intent(this, activity);
        ArrayList<Problem> filteredProblems = new ArrayList<Problem>();
        for (Problem problem : problems) {
            if(problem.getPosition().equals(button)){
                filteredProblems.add(problem);
            }
        }
        intent.putExtra("characteristic", problematicCharacteristic);
        intent.putExtra("position", button);
        intent.putExtra("Problems", filteredProblems);
        startActivity(intent);
    }
}
