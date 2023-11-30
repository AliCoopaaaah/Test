package com.example.test;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import androidx.appcompat.widget.Toolbar;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class FavouritesList extends AppCompatActivity {
    ArrayList<NasaObject> list;
    ListView listView;
    String date;
    String hdURL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        setContentView(R.layout.activity_favourites_list);
        listView = (ListView) findViewById(R.id.listview);
        list = getIntent().getParcelableArrayListExtra("list");

        MyAdapter adapter = new MyAdapter(this, list);
        listView.setAdapter(adapter);
    }//onCreate

    //-------------------------Menu Options-------------------------
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.toolbar_menu, menu);
        return true;
    }//onCreateOptionsMenu

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==R.id.savedImages){
        }
        return true;
    }//onOptionsItemSelected

    //-------------------------Adapter-------------------------
    class MyAdapter extends BaseAdapter {
        Context context;
        ArrayList<NasaObject> list;

        public MyAdapter(Context context, ArrayList<NasaObject> list) {
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
            textView.setText(list.get(i).getDate());

            return view;
        }
    }//MyAdapter
}
