package edu.washington.yslasm.quizdroid;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.ArrayList;


public class AnswerSummary extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_answer_summary);

        Intent test = getIntent();
        TextView tv = (TextView) findViewById(R.id.name);
        final String name = test.getStringExtra("name");
        tv.setText(test.getStringExtra("name"));

        TextView userAnswer = (TextView) findViewById(R.id.useranswer);
        String answer = test.getStringExtra("answer");
        userAnswer.setText("Your answer: " + answer);

        final int count = test.getIntExtra("count", 0);
        int numCorrect = test.getIntExtra("numCorrect", 0);
        TextView rightAnswer = (TextView) findViewById(R.id.rightanswer);
        Questions q = new Questions();
        if(name.equals("Math")) {
            ArrayList<String> right = q.getMathCorrect();
            rightAnswer.setText("Correct answer: " + right.get(count - 1));
            if(answer.equals(right.get(count - 1))) {
                numCorrect++;
            }
        } else if(name.equals("Marvel Super Heroes")) {
            ArrayList<String> right = q.getMarvelCorrect();
            rightAnswer.setText("Correct answer: " + right.get(count - 1));
            if(answer.equals(right.get(count - 1))) {
                numCorrect++;
            }
        } else {
            ArrayList<String> right = q.getPhysicsCorrect();
            rightAnswer.setText("Correct answer: " + right.get(count - 1));
            if(answer.equals(right.get(count - 1))) {
                numCorrect++;
            }

        }

        TextView summary = (TextView) findViewById(R.id.summary);
        summary.setText("You have correctly answered " + numCorrect + " questions out of " + count);
        final int num = numCorrect;

        Button b = (Button) findViewById(R.id.button);
        if(count == 3) {
            b.setText("Finish");
            b.setOnClickListener(new View.OnClickListener() {
                @Override
            public void onClick(View v) {
                    Intent nextActivity = new Intent(AnswerSummary.this, Results.class);
                    nextActivity.putExtra("name", name);
                    nextActivity.putExtra("count", count);
                    nextActivity.putExtra("numCorrect", num);
                    startActivity(nextActivity);
                }
            });
        } else {
            b.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent nextActivity = new Intent(AnswerSummary.this, Question.class);
                    nextActivity.putExtra("name", name);
                    nextActivity.putExtra("count", count);
                    nextActivity.putExtra("numCorrect", num);
                    startActivity(nextActivity);
                }
            });
        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_answer_summary, menu);
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
