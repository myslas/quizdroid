package edu.washington.yslasm.quizdroid;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.ArrayList;
import android.widget.Button;
import android.view.View;
import android.widget.RadioGroup;


public class Question extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.activity_question, container, false);


        final Intent test = getActivity().getIntent();
        TextView tv = (TextView) view.findViewById(R.id.name);
        final String name = test.getStringExtra("name");
        tv.setText(test.getStringExtra("name"));
        TextView question = (TextView) view.findViewById(R.id.question);
        TextView answer1 = (TextView) view.findViewById(R.id.answer1);
        TextView answer2 = (TextView) view.findViewById(R.id.answer2);
        TextView answer3 = (TextView) view.findViewById(R.id.answer3);
        TextView answer4 = (TextView) view.findViewById(R.id.answer4);

        final int numCorrect = test.getIntExtra("numCorrect", 0);
        final int count = test.getIntExtra("count", 0);
        QuizApp q = new QuizApp();
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

        Button b = (Button) view.findViewById(R.id.button);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RadioGroup radioGroup = (RadioGroup) view.findViewById(R.id.group);
                if(radioGroup.getCheckedRadioButtonId() != -1) {
                    TextView radioButton = (TextView) radioGroup.findViewById(radioGroup.getCheckedRadioButtonId());
                    test.putExtra("name", name);
                    test.putExtra("count", count + 1);
                    test.putExtra("answer", "" + radioButton.getText());
                    test.putExtra("numCorrect", numCorrect);
                    Fragment answerFragment = new AnswerSummary();
                    answerFragment.setArguments(getActivity().getIntent().getExtras());
                    FragmentTransaction transaction = getFragmentManager().beginTransaction();

                    // Replace whatever is in the fragment_container view with this fragment,
                    // and add the transaction to the back stack
                    transaction.replace(R.id.fragment_container, answerFragment);
                    transaction.addToBackStack(null);

                    // Commit the transaction
                    transaction.commit();
                }
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
