package com.example.test;

import androidx.appcompat.widget.Toolbar;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class FavouritesList extends MenuOptions{
    private ArrayList<NasaObject> list;
    private NasaObject object;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourites_list);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Button help = (Button) findViewById(R.id.help);

        list = (ArrayList<NasaObject>)getIntent().getSerializableExtra("list");

        //---commented out because it doesn't work---
        //DBConnection connection = new DBConnection(FavouritesList.this);
        //dbList = connection.getImageInfo();

        MyAdapter adapter = new MyAdapter(FavouritesList.this, list);

        ListView listView = (ListView) findViewById(R.id.listview);
        listView.setAdapter(adapter);

        //make the list interactive
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                object = (NasaObject) listView.getItemAtPosition(position);
                String hdURL = object.getHdURL();

                Intent imageIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(hdURL));
                startActivity(imageIntent);
            }
        });

        //help menu
        help.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popup = new PopupMenu(FavouritesList.this, help);
                popup.getMenuInflater().inflate(R.menu.popup_menu_favourites, popup.getMenu());
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        if(item.getItemId()==R.id.viewImage){
                            Toast.makeText(FavouritesList.this, R.string.fav_help_1, Toast.LENGTH_LONG).show();
                        } else if (item.getItemId()==R.id.home) {
                            Toast.makeText(FavouritesList.this, R.string.home_help, Toast.LENGTH_LONG).show();
                        } else if (item.getItemId()==R.id.facts) {
                            Toast.makeText(FavouritesList.this, R.string.fact_help, Toast.LENGTH_LONG).show();
                        } else if (item.getItemId()==R.id.exit) {
                            Toast.makeText(FavouritesList.this, R.string.exit_help, Toast.LENGTH_LONG).show();
                        }
                        return true;
                    }
                });
                popup.show();
            }
        });
    }//onCreate

    //-------------------------Custom Adapter-------------------------
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

            TextView itemName = (TextView)view.findViewById(R.id.itemName);
            TextView textView = (TextView) view.findViewById(R.id.textview);
            itemName.setText(list.get(i).getName());
            textView.setText(list.get(i).getDate());

            return view;
        }
    }//MyAdapter
}