package com.example.test;

import android.content.Context;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import java.util.ArrayList;

public class MenuOptions extends AppCompatActivity {

    ArrayList<String> list;
    //private ArrayList<NasaObject> list;
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.toolbar_menu, menu);
        return true;
    }//onCreateOptionsMenu

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        DatabaseConnection connection = new DatabaseConnection(this);
        list = connection.getImageDate();

        if(item.getItemId()==R.id.home){
            startActivity(new Intent(this, MainActivity.class));
        } else if (item.getItemId()==R.id.savedImages) {
            Intent intent = new Intent(this, FavouritesList.class);
            intent.putExtra("list", list);
            startActivity(intent);
        } else if (item.getItemId()==R.id.exit){
            ActivityCompat.finishAffinity(this);
        }
        return true;
    }//onOptionsItemSelected
}
