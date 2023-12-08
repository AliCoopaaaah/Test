package com.example.test;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

public class FactsFragment extends Fragment {
    private TextView textView;
    private ProgressBar progressbar;
    private Button factButton;
    private int number = 1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_facts, container, false);

        textView = (TextView) view.findViewById(R.id.fact);
        progressbar = (ProgressBar)view.findViewById(R.id.progressbar);
        factButton = (Button)view.findViewById(R.id.factbutton);

        return view;
    }//onCreateView

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        //set textview default to fact 1
        textView.setText(R.string.fact1);

        //get new fact when button is clicked
        factButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressbar.setProgress((number*20));
                number++;
                if(number==1){
                    textView.setText(R.string.fact1);
                } else if (number==2) {
                    textView.setText(R.string.fact2);
                } else if (number==3) {
                    textView.setText(R.string.fact3);
                } else if (number==4) {
                    textView.setText(R.string.fact4);
                } else if (number==5) {
                    textView.setText(R.string.fact5);
                } else if (number==6) {
                    textView.setText(R.string.fact6);
                    number=0;
                }
            }
        });
    }//onActivityCreated
}