package com.example.test;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
public class MainActivity extends AppCompatActivity {

    ArrayList<SWCharacter> list;
    ListView lv;
    myAdapter adapter;
    InputStream input;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lv = (ListView) findViewById(R.id.listview);

        list = new ArrayList<>();

        //adapter set up
        adapter = new myAdapter(this, list);
        lv.setAdapter(adapter);

        getStarWars starWars = new getStarWars();
        starWars.execute();//order 66
        adapter.notifyDataSetChanged();
    }//onCreate

    class SWCharacter {
        private String name;
        private String height;
        private String mass;

        //constructors
        public SWCharacter() {
        }

        public SWCharacter(String name, String height, String mass) {
            this.name = name;
            this.height = height;
            this.mass = mass;
        }

        //setters
        public void setName(String name) {
            this.name = name;
        }

        public void setHeight(String height) {
            this.height = height;
        }

        public void setMass(String mass) {
            this.mass = mass;
        }

        //getters
        public String getName() {
            return name;
        }

        public String getHeight() {
            return height;
        }

        public String getMass() {
            return mass;
        }
    }//SWCharacter

    class myAdapter extends BaseAdapter {
        Context context;
        ArrayList<SWCharacter> thisList;

        public myAdapter(Context context, ArrayList<SWCharacter> thisList) {
            this.context = context;
            this.thisList = thisList;
        }

        @Override
        public int getCount() {
            return thisList.size();
        }

        @Override
        public Object getItem(int position) {
            return thisList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return (long) position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            View newView = convertView;
            LayoutInflater inflater = getLayoutInflater();

            if (newView == null) {
                newView = inflater.inflate(R.layout.characters, parent, false);
            }

            //get the character name from the list
            SWCharacter thisCharacter = (SWCharacter) getItem(position);
            String name = thisCharacter.getName();

            TextView tv = newView.findViewById(R.id.textview);
            tv.setText(name);

            return newView;
        }
    }//myAdapter

    public void URLConnection() throws IOException {
        HttpURLConnection urlConnection;
        String url = "https://swapi.dev/api/people/?format=json";
        urlConnection = (HttpURLConnection) new URL(url).openConnection();
        urlConnection.setDoInput(true);
        urlConnection.connect();
        input = urlConnection.getInputStream();
    }//URLConnection


    class getStarWars extends AsyncTask<String, String, SWCharacter > {

        private SWCharacter character;

        @Override
        protected SWCharacter  doInBackground(String... strings) {

            try {
                URLConnection();
                BufferedReader reader = new BufferedReader(new InputStreamReader(input));
                StringBuffer buffer = new StringBuffer();
                String line = "";

                while ((line = reader.readLine()) != null) {
                    buffer.append(line);
                }

                String streamResults = buffer.toString();

                JSONObject json1 = new JSONObject(streamResults);
                JSONArray array1 = json1.getJSONArray("results");

                for (int i = 0; i < array1.length(); i++) {
                    JSONObject jsonObject = array1.getJSONObject(i);
                    character = new SWCharacter();

                    character.setName(jsonObject.getString("name"));
                    //character.setHeight(jsonObject.getString("height"));
                    //character.setMass(jsonObject.getString("mass"));

                    list.add(character);
                }
                return character;

            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(String... values) {
            super.onProgressUpdate(values);
        }
    }//getStarWars
}