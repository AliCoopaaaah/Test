package com.example.test;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class FavouritesList extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourites_list);

        ArrayList<String> list = (ArrayList<String>) getIntent().getSerializableExtra("list");

        ImageView imageview = (ImageView)findViewById(R.id.image);
        ListView listView = (ListView) findViewById(R.id.listview);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.list_item, list);

        listView.setAdapter(adapter);

    }//onCreate

}
