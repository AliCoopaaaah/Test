package com.example.test;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentTransaction;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.Toast;

public class Facts extends MenuOptions {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_facts);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Button help = (Button)findViewById(R.id.help);

        FactsFragment facts = new FactsFragment();

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame, facts);
        transaction.commit();

        //help menu
        help.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popup = new PopupMenu(Facts.this, help);
                popup.getMenuInflater().inflate(R.menu.popup_menu_favourites, popup.getMenu());
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        if(item.getItemId()==R.id.fact_help){
                            Toast.makeText(Facts.this, R.string.fact_help_1, Toast.LENGTH_LONG).show();
                        } else if (item.getItemId()==R.id.home) {
                            Toast.makeText(Facts.this, R.string.home_help, Toast.LENGTH_LONG).show();
                        } else if (item.getItemId()==R.id.exit) {
                            Toast.makeText(Facts.this, R.string.exit_help, Toast.LENGTH_LONG).show();
                        }
                        return true;
                    }
                });
                popup.show();
            }
        });
    }
}