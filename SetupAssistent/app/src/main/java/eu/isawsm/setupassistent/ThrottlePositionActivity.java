package eu.isawsm.setupassistent;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

import Persitancy.Problem;


public class ThrottlePositionActivity extends Activity {

    private ArrayList<Problem> problems;
    private String problematicCharacteristic;
    private String problematicPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_throttle_position);


        Intent data = getIntent();
        problems = (ArrayList<Problem>) data.getSerializableExtra("Problems");
        problematicCharacteristic = data.getStringExtra("characteristic");
        problematicPosition = data.getStringExtra("position");

        TextView textView = (TextView) findViewById(R.id.textView);
        textView.setText(getString(R.string.ThrottlePrompt));
        System.out.println("Found " + problems.size() + " problems!");
    }

    public void btnYesClicked(View view) {
        callNextActivity(true,SuggestionActivity.class);
    }

    public void btnNoClicked(View view) {
        callNextActivity(false,SuggestionActivity.class);
    }

    private void callNextActivity(Boolean button, Class activity) {
        Intent intent = new Intent(this, activity);
        ArrayList<Problem> filteredProblems = new ArrayList<Problem>();
        for (Problem problem : problems) {
            if(!(problem.getOnThrottle() ^ button)){

                filteredProblems.add(problem);
            }
            System.out.println(problem.getOnThrottle() + " && " + button + " = " + !(problem.getOnThrottle() ^ button));
        }
        intent.putExtra("characteristic", problematicCharacteristic);
        intent.putExtra("position", problematicPosition);
        intent.putExtra("ThrottlePosition", button);
        intent.putExtra("Problems", filteredProblems);

        startActivity(intent);
    }
}
