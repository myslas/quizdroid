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

public class OverviewFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_overview, container, false);
        final Intent launchedMe = getActivity().getIntent();
        final String name = launchedMe.getStringExtra("name");
        TextView tv = (TextView) view.findViewById(R.id.name);
        tv.setText(name);
        TextView overview = (TextView) view.findViewById(R.id.overview);
        QuizApp q = new QuizApp();

        if(name.equals("Marvel Super Heroes")) {
            overview.setText(q.MarvelTopic().get(2));
        } else if(name.equals("Math")) {
            overview.setText(q.MathTopic().get(2));
        } else {
            overview.setText(q.PhysicsTopic().get(2));
        }

        Button b = (Button) view.findViewById(R.id.button);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchedMe.putExtra("name", name);
                launchedMe.putExtra("count", 0);
                launchedMe.putExtra("numCorrect", 0);
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
