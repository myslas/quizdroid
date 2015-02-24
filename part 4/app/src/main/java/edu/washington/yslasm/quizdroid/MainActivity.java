package edu.washington.yslasm.quizdroid;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.view.View;
import android.content.Intent;
import android.widget.AdapterView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final ListView listView = (ListView) findViewById(R.id.listView);
        QuizApp q = new QuizApp();

        final String[] names = {q.MathTopic().get(0), q.PhysicsTopic().get(0), q.MarvelTopic().get(0)};
        String[] descriptions = {q.MathTopic().get(1), q.PhysicsTopic().get(1), q.MarvelTopic().get(1)};

        List<Map<String, String>> data = new ArrayList<Map<String, String>>();
        for (int i = 0; i < names.length; i++) {
            Map<String, String> datum = new HashMap<String, String>(2);
            datum.put("names", names[i]);
            datum.put("descriptions", descriptions[i]);
            data.add(datum);
        }
        SimpleAdapter adapter = new SimpleAdapter(this, data,
                android.R.layout.simple_list_item_2,
                new String[] {"names", "descriptions"},
                new int[] {android.R.id.text1,
                        android.R.id.text2});



        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent nextActivity = new Intent(MainActivity.this, Overview.class);
                nextActivity.putExtra("name", names[position]);
                startActivity(nextActivity);
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        if (id == R.id.preferences) {
            Intent nextActivity = new Intent(MainActivity.this, Preferences.class);
            startActivity(nextActivity);
        }

        return super.onOptionsItemSelected(item);
    }
}
