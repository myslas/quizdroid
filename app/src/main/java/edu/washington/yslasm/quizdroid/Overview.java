package edu.washington.yslasm.quizdroid;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Button;


public class Overview extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_overview);

        Intent launchedMe = getIntent();
        final String name = launchedMe.getStringExtra("name");
        TextView tv = (TextView) findViewById(R.id.name);
        tv.setText(name);
        TextView overview = (TextView) findViewById(R.id.overview);

        if(name.equals("Marvel Super Heroes")) {
            overview.setText("The Marvel Universe is the shared fictional " +
                    "universe where stories in most comic book titles and other media published " +
                    "by Marvel Entertainment take place, including those featuring Marvel's most " +
                    "familiar characters, such as Spider-Man, the X-Men, the Fantastic Four and the Avengers.");
        } else if(name.equals("Math")) {
            overview.setText("Mathematics (from Greek μάθημα máthēma, “knowledge, study, " +
                    "learning”) is the study of topics such as quantity (numbers), structure, space, and change.");
        } else {
            overview.setText("Physics  is the natural science that involves the study of matter and its motion through space and time, along with related concepts such as energy and force.");
        }

        Button b = (Button) findViewById(R.id.button);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent nextActivity = new Intent(Overview.this, Question.class);
                nextActivity.putExtra("name", name);
                nextActivity.putExtra("count", 0);
                nextActivity.putExtra("numCorrect", 0);
                startActivity(nextActivity);
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_overview, menu);
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

        return super.onOptionsItemSelected(item);
    }
}
