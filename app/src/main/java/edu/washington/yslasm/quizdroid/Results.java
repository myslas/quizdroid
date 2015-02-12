package edu.washington.yslasm.quizdroid;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


public class Results extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = inflater.inflate(R.layout.activity_results, container, false);

        Intent test = getActivity().getIntent();
        TextView tv = (TextView) view.findViewById(R.id.name);
        tv.setText(test.getStringExtra("name"));

        int count = test.getIntExtra("count", 0);
        int numCorrect = test.getIntExtra("numCorrect", 0);

        TextView summary = (TextView) view.findViewById(R.id.summary);
        summary.setText("You have correctly answered " + numCorrect + " questions out of " + count);

        Button b = (Button) view.findViewById(R.id.button);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent nextActivity = new Intent(getActivity(), MainActivity.class);
                startActivity(nextActivity);
            }
        });

        return view;

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
