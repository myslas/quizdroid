package edu.washington.yslasm.quizdroid;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;


public class AnswerSummary extends Fragment {

    final String TAG = "AnswerSummary";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_answer_summary, container, false);

        final Intent sumIntent = getActivity().getIntent();
        TextView tv = (TextView) view.findViewById(R.id.name);
        final String name = sumIntent.getStringExtra("name");
        tv.setText(sumIntent.getStringExtra("name"));

        TextView userAnswer = (TextView) view.findViewById(R.id.useranswer);
        String answer = sumIntent.getStringExtra("answer");
        Log.i(TAG, "answer = " + answer);
        userAnswer.setText("Your answer: " + answer);

        final int count = sumIntent.getIntExtra("count", 0);
        int numCorrect = sumIntent.getIntExtra("numCorrect", 0);
        TextView rightAnswer = (TextView) view.findViewById(R.id.rightanswer);
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

        TextView summary = (TextView) view.findViewById(R.id.summary);
        summary.setText("You have correctly answered " + numCorrect + " questions out of " + count);
        final int num = numCorrect;

        Button b = (Button) view.findViewById(R.id.button);
        if(count == 3) {
            b.setText("Finish");
        }

        b.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                sumIntent.putExtra("name", name);
                sumIntent.putExtra("count", count);
                sumIntent.putExtra("numCorrect", num);
                    Fragment questionFragment = new Question();
                    FragmentTransaction transaction = getFragmentManager().beginTransaction();

                    // Replace whatever is in the fragment_container view with this fragment,
                    // and add the transaction to the back stack
                    transaction.replace(R.id.fragment_container, questionFragment);
                    transaction.addToBackStack(null);

                    // Commit the transaction
                    transaction.commit();
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
