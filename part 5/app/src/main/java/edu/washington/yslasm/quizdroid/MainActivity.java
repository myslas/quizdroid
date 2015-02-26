package edu.washington.yslasm.quizdroid;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.provider.Settings;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.JsonReader;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.view.View;
import android.content.Intent;
import android.widget.AdapterView;
import android.widget.SimpleAdapter;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
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
        Context context = getApplicationContext();
        if(isAirplaneModeOn(context)) {
            // 1. Instantiate an AlertDialog.Builder with its constructor
            AlertDialog.Builder builder = new AlertDialog.Builder(context);

// 2. Chain together various setter methods to set the dialog characteristics
            builder.setMessage("Airplane Mode is enabled. Would you like to go to settings to enable wifi?");
            builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    startActivity(new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS));
                }
            });
            builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {

                }
            });

// 3. Get the AlertDialog from create()
            AlertDialog dialog = builder.create();
        }
        ConnectivityManager connManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo mWifi = connManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);

        if (!mWifi.isConnected()) {
            // 1. Instantiate an AlertDialog.Builder with its constructor
            AlertDialog.Builder builder = new AlertDialog.Builder(context);

// 2. Chain together various setter methods to set the dialog characteristics
            builder.setMessage("Wifi is not connected. No downloads will be made");

// 3. Get the AlertDialog from create()
            AlertDialog dialog = builder.create();
        }
        QuizApp q = new QuizApp(context);
        final String[] names = {q.Topics().get(0), q.Topics().get(1), q.Topics().get(2)};
        final String[] descriptions = {q.Descriptions().get(0), q.Descriptions().get(1), q.Descriptions().get(2)};

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
                nextActivity.putExtra("desc", descriptions[position]);
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

    private static boolean isAirplaneModeOn(Context context) {

        return Settings.System.getInt(context.getContentResolver(),
                Settings.System.AIRPLANE_MODE_ON, 0) != 0;

    }
}




