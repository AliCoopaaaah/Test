package com.example.test;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentTransaction;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);

    }//onCreate

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.toolbar_menu, menu);
        return true;
    }//onCreateOptionsMenu

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        DadJoke joke = new DadJoke();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        if(item.getItemId()==R.id.dad_joke){
            transaction.replace(R.id.frame, joke);
            transaction.addToBackStack(null);
            transaction.commit();
        } else if (item.getItemId()==R.id.dragon){
            Toast.makeText(this, getResources().getString(R.string.dragon), Toast.LENGTH_SHORT).show();
        } else if (item.getItemId()==R.id.duck){
            Toast.makeText(this, getResources().getString(R.string.duck), Toast.LENGTH_SHORT).show();
        } else if (item.getItemId()==R.id.home){
            getSupportFragmentManager().popBackStackImmediate();
        } else if (item.getItemId()==R.id.exit){
            MainActivity.this.finish();
        }
        return true;
    }
}
