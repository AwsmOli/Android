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

        String title = problems.get(0).getCharacteristic();
        if(data.hasExtra("position")) title += "-" +problems.get(0).getPosition();
        if(data.hasExtra("ThrottlePosition")) if(problematicThrottlePosition) title += getString(R.string.onThrottle); else title += getString(R.string.OffThrottle);
        setTitle(title);



        System.out.println("Found " + problems.size() + " problems!");
        System.out.println("Have " + problems.get(0).getSuggestionList().size() + " Suggestions for you!");
        ListView listView = (ListView) findViewById(R.id.listView);

        final CustomAdapter customAdapter = new CustomAdapter(this);



        for(int i = 1; i < 9; i++){
            customAdapter.addSectionHeaderItem(getString(R.string.tri) + " " + getNumberString(i));
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
                return getString(R.string.first);
            case 2:
                return getString(R.string.second);
            case 3:
                return getString(R.string.third);
            case 4:
                return getString(R.string.fourth);
            case 5:
                return getString(R.string.fifth);
            case 6:
                return getString(R.string.sixth);
            case 7:
                return getString(R.string.seventh);
            case 8:
                return getString(R.string.eight);
            default:
                return  getString(R.string.then);
        }
    }

    public void btnStartOverClicked(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
