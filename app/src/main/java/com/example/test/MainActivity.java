package com.example.test;


import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    TestObject test = new TestObject();
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = (Button) findViewById(R.id.button);
        test.setWords("Hello");

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FragmentManager manager = getSupportFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();

                Bundle bundle = new Bundle();
                String myWords = test.getWords();
                bundle.putString("key ", "myWords");

                DetailsFragment detailsFragment = new DetailsFragment();
                detailsFragment.setArguments(bundle);
                transaction.replace(R.id.frame, detailsFragment).commit();
            }
        });
    }//onCreate
}