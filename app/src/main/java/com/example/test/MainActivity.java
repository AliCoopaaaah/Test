package com.example.test;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.ActionBar;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

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
import java.util.List;

public class MainActivity extends MenuOptions{
    private ImageView image;
    private Bitmap bitmap;
    private InputStream input1;

    //dates to be appended to the URL
    private int URLyear = 1996;
    private int URLmonth = 3;
    private int URLday = 25;
    private String URLdate;

    //ArrayList and String variables to be sent between activities
    private String name;
    private String savedURL;
    private String hdURL = "https://apod.nasa.gov/apod/image/2303/_GHR3094-venerelunafirma.jpg";
    private ArrayList<NasaObject> list;
    private NasaObject object;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        TextView datePicked = (TextView) findViewById(R.id.datePicked);
        Button dateButton = (Button) findViewById(R.id.dateButton);
        Button show = (Button) findViewById(R.id.show);
        Button save = (Button) findViewById(R.id.save);
        image = (ImageView) findViewById(R.id.image);
        EditText editText = (EditText) findViewById(R.id.edittext);
        Button viewFavourites = (Button) findViewById(R.id.viewFavourites);
        Button help = (Button) findViewById(R.id.help);

        list = new ArrayList<NasaObject>();

        //get date from user
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
                                URLmonth = monthOfYear + 1;
                                URLday = dayOfMonth;
                                datePicked.setText(thisyear+"-"+(monthOfYear+1)+"-"+dayOfMonth);
                            }
                        },
                        year, month, day);
                datePickerDialog.show();
            }
        });

        //show the image
        show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                URLdate = (URLyear+"-"+URLmonth+"-"+URLday).toString();
                NASAImages nasa = new NASAImages();
                nasa.execute();
            }
        });

        //save URL to list of favourites
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(editText.getText().toString().equals("")){
                    name = "Saved Image";
                }else {
                    name = editText.getText().toString();
                }
                editText.getText().clear();
                URLdate = (URLyear+"-"+URLmonth+"-"+URLday).toString();
                object = new NasaObject(URLdate, name, hdURL);
                list.add(object);

                //---commented out because it doesn't work---
                //DatabaseConnection connection = new DatabaseConnection(MainActivity.this);
                //connection.addImage(URLdate, name, hdURL);

                Toast.makeText(MainActivity.this, R.string.saved_to_favourites, Toast.LENGTH_SHORT).show();
            }
        });

        //change activity to favourites lsit
        viewFavourites.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, FavouritesList.class);
                intent.putExtra("list", list);
                startActivity(intent);
            }
        });

        //help menu
        help.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popup = new PopupMenu(MainActivity.this, help);
                popup.getMenuInflater().inflate(R.menu.popup_menu, popup.getMenu());
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        if(item.getItemId()==R.id.imageHelp){
                            Toast.makeText(MainActivity.this, R.string.main_help_1, Toast.LENGTH_LONG).show();
                        } else if (item.getItemId()==R.id.saveTo) {
                            Toast.makeText(MainActivity.this, R.string.main_help_2, Toast.LENGTH_LONG).show();
                        } else if (item.getItemId()==R.id.viewlist){
                            Toast.makeText(MainActivity.this, R.string.main_help_3, Toast.LENGTH_LONG).show();
                        }else if (item.getItemId()==R.id.facts){
                            Toast.makeText(MainActivity.this, R.string.fact_help, Toast.LENGTH_LONG).show();
                        } else if (item.getItemId()==R.id.exit){
                            Toast.makeText(MainActivity.this, R.string.exit_help, Toast.LENGTH_LONG).show();
                        }
                        return true;
                    }
                });
                popup.show();
            }
        });

    }//onCreate

    //-------------------------Saved Instances-------------------------
    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putParcelableArrayList("list", list);
        super.onSaveInstanceState(outState);
    }//onSavedInstanceState

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
       list = savedInstanceState.getParcelableArrayList("list");
       super.onRestoreInstanceState(savedInstanceState);
        list = savedInstanceState.getParcelableArrayList("list");
    }//onRestoreInstanceState

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

                hdURL = jsonObject.getString("hdurl");
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
