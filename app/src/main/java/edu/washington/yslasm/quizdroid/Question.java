package edu.washington.yslasm.quizdroid;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.content.Intent;
import android.widget.TextView;
import java.util.ArrayList;
import android.widget.Button;
import android.view.View;
import android.widget.RadioGroup;


public class Question extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

        Intent test = getIntent();
        TextView tv = (TextView) findViewById(R.id.name);
        final String name = test.getStringExtra("name");
        tv.setText(test.getStringExtra("name"));
        TextView question = (TextView) findViewById(R.id.question);
        TextView answer1 = (TextView) findViewById(R.id.answer1);
        TextView answer2 = (TextView) findViewById(R.id.answer2);
        TextView answer3 = (TextView) findViewById(R.id.answer3);
        TextView answer4 = (TextView) findViewById(R.id.answer4);

        final int numCorrect = test.getIntExtra("numCorrect", 0);
        final int count = test.getIntExtra("count", 0);
        Questions q = new Questions();
        if(name.equals("Marvel Super Heroes")) {
            ArrayList<String> questions = q.getMarvelQuestions();
            ArrayList<String> answers = q.getMarvelAnswers();
            question.setText(questions.get(count));
            answer1.setText(answers.get(4 * count));
            answer2.setText(answers.get(4 * count + 1));
            answer3.setText(answers.get(4 * count + 2));
            answer4.setText(answers.get(4 * count + 3));

        } else if(name.equals("Math")) {
            ArrayList<String> questions = q.getMathQuestions();
            ArrayList<String> answers = q.getMathAnswers();
            question.setText(questions.get(count));
            answer1.setText(answers.get(4 * count));
            answer2.setText(answers.get(4 * count + 1));
            answer3.setText(answers.get(4 * count + 2));
            answer4.setText(answers.get(4 * count + 3));


        } else {
            ArrayList<String> questions = q.getPhysicsQuestions();
            ArrayList<String> answers = q.getPhysicsAnswers();
            question.setText(questions.get(count));
            answer1.setText(answers.get(4 * count));
            answer2.setText(answers.get(4 * count + 1));
            answer3.setText(answers.get(4 * count + 2));
            answer4.setText(answers.get(4 * count + 3));

        }

        Button b = (Button) findViewById(R.id.button);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RadioGroup radioGroup = (RadioGroup) findViewById(R.id.group);
                if(radioGroup.getCheckedRadioButtonId() != -1) {
                    TextView radioButton = (TextView) radioGroup.findViewById(radioGroup.getCheckedRadioButtonId());
                    Intent nextActivity = new Intent(Question.this, AnswerSummary.class);
                    nextActivity.putExtra("name", name);
                    nextActivity.putExtra("count", count + 1);
                    nextActivity.putExtra("answer", radioButton.getText());
                    nextActivity.putExtra("numCorrect", numCorrect);
                    startActivity(nextActivity);
                }
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_question, menu);
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
