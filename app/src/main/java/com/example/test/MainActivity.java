package com.example.test;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentTransaction;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    ImageView image;
    Bitmap bitmap;
    InputStream input1;

    //dates to be appended to the URL
    int URLyear = 1996;
    int URLmonth = 3;
    int URLday = 25;
    String URLdate;

    //ArrayList and String variables to be sent between activities
    String savedURL;
    String hdURL;
    NasaObject object;
    private ArrayList<NasaObject> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        TextView datePicked = (TextView) findViewById(R.id.datePicked);
        Button dateButton = (Button) findViewById(R.id.dateButton);
        Button show = (Button) findViewById(R.id.show);
        image = (ImageView) findViewById(R.id.image);

        list = new ArrayList<>();

        dateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar c = Calendar.getInstance();

                int year = c.get(Calendar.YEAR);
                int month = c.get(Calendar.MONTH);
                int day = c.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        MainActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int thisyear, int monthOfYear, int dayOfMonth) {
                                URLyear = thisyear;
                                URLmonth = monthOfYear;
                                URLday = dayOfMonth;
                                datePicked.setText(thisyear+"-"+(monthOfYear+1)+"-"+dayOfMonth);
                            }
                        },
                        year, month, day);
                datePickerDialog.show();
            }
        });

        show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                URLdate = (URLyear+"-"+(URLmonth+1)+"-"+URLday).toString();
                NASAImages nasa = new NASAImages();
                nasa.execute();
            }
        });

        Button newActivity = (Button) findViewById(R.id.test);
        Button save = (Button) findViewById(R.id.save);
        newActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, FavouritesList.class);
                intent.putExtra("list", list);
                startActivity(intent);
            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //adds the current string value of a URL to list
                URLdate = (URLyear+"-"+(URLmonth+1)+"-"+URLday).toString();
                hdURL = "FSGAFHJAOFWefkpeaerp";
                object = new NasaObject(hdURL, URLdate);
                list.add(object);
                Toast.makeText(MainActivity.this, "Image Link Saved to Favourites", Toast.LENGTH_SHORT).show();
            }
        });
    }//onCreate

    //-------------------------Saved Instances-------------------------
    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putParcelableArrayList("list", list);
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
       list = savedInstanceState.getParcelableArrayList("list");
       super.onRestoreInstanceState(savedInstanceState);
    }

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

    //-------------------------URL Connection and AsyncTask-------------------------
    public void URLConnection() throws IOException {
        HttpURLConnection connection;
        //change the date to a date variable
        String url = "https://api.nasa.gov/planetary/apod?api_key=hm97X7cDaP9hZN1E1oGdbS7QVSJbI3W4jdzTzKva&date="+URLdate;
        connection = (HttpURLConnection) new URL(url).openConnection();
        connection.connect();
        input1 = connection.getInputStream();
    }//URLConnection()

    class NASAImages extends AsyncTask<String, Integer, Bitmap> {
        HttpURLConnection connection;
        BufferedReader reader;
        JSONObject jsonObject;
        String json;
        String url;
        URL urlValue;
        InputStream input2;
        @Override
        protected Bitmap doInBackground(String... strings) {

            try{
                URLConnection();
                reader = new BufferedReader(new InputStreamReader(input1));
                json = reader.readLine();
                jsonObject = new JSONObject(json);
                urlValue = new URL(jsonObject.getString("url"));
                connection = (HttpURLConnection) urlValue.openConnection();
                connection.setDoInput(true);
                connection.connect();
                input2 = connection.getInputStream();
                bitmap = BitmapFactory.decodeStream(input2);

                savedURL = urlValue.toString();
            } catch(MalformedURLException e){
                throw new RuntimeException(e);
            } catch (IOException e){
                throw new RuntimeException(e);
            } catch (JSONException e){
                throw new RuntimeException(e);
            }
            return bitmap;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
            if(image != null){
                image.setImageBitmap(bitmap);
            }
        }
    }//NASAImages
}
