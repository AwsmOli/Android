package eu.isawsm.setupassistent;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.io.IOException;
import java.util.ArrayList;
import Persitancy.CSVParser;
import Persitancy.Problem;



public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void btnTractionRollClicked(View view) throws IOException {
        callNextActivity(CSVParser.TRACTION_ROLL, PositionActivity.class);
    }

    public void btnUndersteerClicked(View view) throws IOException {
        callNextActivity(CSVParser.UNDERSTEER, PositionActivity.class);
    }
    public void btnOversteerClicked(View view) throws IOException {
        callNextActivity(CSVParser.OVERSTEER, PositionActivity.class);
    }
    public void BtnStabilityClicked(View view) throws IOException {
        callNextActivity(CSVParser.STRAIGHT_LINE_STABILITY, SuggestionActivity.class);
    }
    public void btnResponseClicked(View view) throws IOException {
        callNextActivity(CSVParser.STEERING_RESPONSE, SuggestionActivity.class);
    }

    private void callNextActivity(String button, Class activity) throws IOException {
        Intent intent = new Intent(this, activity);
        ArrayList<Problem> problems = new ArrayList<Problem>();
        for (Problem problem : CSVParser.readCSV(getAssets().open("Data.csv"))) {
            if(problem.getCharacteristic().equals(button)){
                problems.add(problem);
            }
        }
        intent.putExtra("characteristic", button);
        intent.putExtra("Problems", problems);
        startActivity(intent);
    }

}
