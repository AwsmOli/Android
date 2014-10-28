package eu.isawsm.setupassistent;

import android.app.Activity;
import android.app.LauncherActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import eu.isawsm.setupassistent.custom.*;
import java.util.ArrayList;



import Persitancy.Problem;
import Persitancy.Suggestion;


public class SuggestionActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_suggestion);

        Intent data = getIntent();
        ArrayList<Problem> problems = (ArrayList<Problem>) data.getSerializableExtra("Problems");
        String problematicCharacteristic = data.getStringExtra("characteristic");
        String problematicPosition = data.getStringExtra("position");
        boolean problematicThrottlePosition = data.getBooleanExtra("ThrottlePosition", false);

        String title = problematicCharacteristic;
        if(data.hasExtra("position")) title += "-" +problematicPosition;
        if(data.hasExtra("ThrottlePosition")) if(problematicThrottlePosition) title += "-On Throttle"; else title += "-Off Throttle";
        setTitle(title);



        System.out.println("Found " + problems.size() + " problems!");
        System.out.println("Have " + problems.get(0).getSuggestionList().size() + " Suggestions for you!");
        ListView listView = (ListView) findViewById(R.id.listView);

        final CustomAdapter customAdapter = new CustomAdapter(this);



        for(int i = 1; i < 9; i++){
            customAdapter.addSectionHeaderItem("Try " + getNumberString(i));
            for(Suggestion s : problems.get(0).getSuggestionList()){
                if(i == s.getUseOrder())
                    customAdapter.addItem(s);
            }
        }

        listView.setAdapter(customAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                showToast(customAdapter.getItem(i));

            }
        });
    }

    private void showToast(Object item){
        Toast.makeText(this,item.toString(),Toast.LENGTH_SHORT).show();

    }


    private String getNumberString(int i) {
        switch (i) {
            case 1:
                return "First";
            case 2:
                return "Second";
            case 3:
                return "Third";
            case 4:
                return "Fourth";
            case 5:
                return "Fifth";
            case 6:
                return "Sixth";
            case 7:
                return "Seventh";
            case 8:
                return "Eighth";
            default:
                return  "then";
        }
    }

    public void btnStartOverClicked(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
