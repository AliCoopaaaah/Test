package com.example.test;

import android.content.Intent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

public class MenuOptions extends AppCompatActivity {

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.toolbar_menu, menu);
        return true;
    }//onCreateOptionsMenu

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==R.id.home){
            startActivity(new Intent(this, MainActivity.class));
        } else if (item.getItemId()==R.id.quiz) {
            startActivity(new Intent(this, Facts.class));
        } else if (item.getItemId()==R.id.exit){
            ActivityCompat.finishAffinity(this);
        }
        return true;
    }//onOptionsItemSelected
}
