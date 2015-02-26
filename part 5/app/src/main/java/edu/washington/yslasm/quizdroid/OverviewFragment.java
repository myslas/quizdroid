package edu.washington.yslasm.quizdroid;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class OverviewFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_overview, container, false);
        final Intent launchedMe = getActivity().getIntent();
        final String name = launchedMe.getStringExtra("name");
        QuizApp q = new QuizApp(getActivity().getApplicationContext());
        int size = 0;
        if(name.equals("Marvel Super Heroes")) {
            size = q.getMarvelCorrect().size();
        } else if(name.equals("Math")) {
            size = q.getMathCorrect().size();
        } else {
            size = q.getScienceCorrect().size();
        }
        TextView num = (TextView) view.findViewById(R.id.textView2);
        num.setText("Number of Questions: " + size);
        TextView tv = (TextView) view.findViewById(R.id.name);
        tv.setText(name);
        TextView overview = (TextView) view.findViewById(R.id.overview);
        overview.setText(launchedMe.getStringExtra("desc"));


        Button b = (Button) view.findViewById(R.id.button);
        final int finalSize = size;
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchedMe.putExtra("name", name);
                launchedMe.putExtra("count", 0);
                launchedMe.putExtra("numCorrect", 0);
                launchedMe.putExtra("size", finalSize);
                Fragment questionFragment = new Question();
                questionFragment.setArguments(getActivity().getIntent().getExtras());
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
}
