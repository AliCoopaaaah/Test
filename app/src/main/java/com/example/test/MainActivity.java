package com.example.test;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ImageView;

import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    ImageView image;
    Bitmap bitmap;
    InputStream input1;
    String date;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        image = findViewById(R.id.image);
        NASAImages nasa = new NASAImages();
        nasa.execute();
    }//onCreate

    public void URLConnection() throws IOException {
        HttpURLConnection connection;
        String url = "https://api.nasa.gov/planetary/apod?api_key=hm97X7cDaP9hZN1E1oGdbS7QVSJbI3W4jdzTzKva";
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
            new NASAImages().execute();
        }
    }//NASAImages
}
