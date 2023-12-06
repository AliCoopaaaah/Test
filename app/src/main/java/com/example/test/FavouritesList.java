package com.example.test;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class FavouritesList extends MenuOptions{
    ArrayList<String> list;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourites_list);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Intent intent = getIntent();
        list = intent.getStringArrayListExtra("list");

        listView = (ListView) findViewById(R.id.listview);
        Button help = (Button) findViewById(R.id.help);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(FavouritesList.this, android.R.layout.simple_list_item_1, android.R.id.text1, list);
        listView.setAdapter(adapter);

        help.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popup = new PopupMenu(FavouritesList.this, help);
                popup.getMenuInflater().inflate(R.menu.popup_menu_favourites, popup.getMenu());
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        if(item.getItemId()==R.id.viewImage){
                            Toast.makeText(FavouritesList.this, "Select a date from the list to view the corresponding image", Toast.LENGTH_LONG).show();
                        } else if (item.getItemId()==R.id.home) {
                            Toast.makeText(FavouritesList.this, "Select the 'HOME' button (spaceship icon) from the toolbar to return to the homepage", Toast.LENGTH_LONG).show();
                        }
                        return true;
                    }
                });
                popup.show();
            }
        });
    }//onCreate

    //-------------------------Adapter-------------------------
    class MyAdapter extends BaseAdapter {
        Context context;
        ArrayList<String> list;

        public MyAdapter(Context context, ArrayList<String> list) {
            this.context = context;
            this.list = list;
        }

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            if (view == null) {
                LayoutInflater inflater = getLayoutInflater();
                view = inflater.inflate(R.layout.list_item, null);
            }

            TextView textView = (TextView) view.findViewById(R.id.textview);
            textView.setText(list.get(i));

            return view;
        }
    }//MyAdapter
}
