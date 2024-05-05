package com.example.test_android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

public class CinemaActivity extends AppCompatActivity {

    String link;
    String link_name;

    class ImageTask extends AsyncTask<String, Void, Bitmap>{

        @Override
        protected Bitmap doInBackground(String... args) {
            URL url = null;
            try {
                url = new URL("http://176.77.109.225/content/posters/"+args[0]);
                Bitmap bitmap = BitmapFactory.decodeStream((InputStream)url.getContent());
                return bitmap;
            } catch (MalformedURLException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        protected void onPostExecute(Bitmap bitmap){
            ImageView img = findViewById(R.id.imageCinema);
            img.setImageBitmap(bitmap);
        }
    }

    class VolnaGetTask extends AsyncTask<String,Void,JSONObject> {

        @Override
        protected JSONObject doInBackground(String... args) {
            CloseableHttpClient httpClient = HttpClients.createDefault();
            try {
                HttpResponse response = httpClient.execute(new HttpGet("http://176.77.109.225/content/information/"+args[0]));
                HttpEntity entity = response.getEntity();
                String responseString = EntityUtils.toString(entity);
                Log.d("MainActivity",responseString);
                JSONObject json = new JSONObject(responseString);
                return json;
            } catch (ClientProtocolException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (JSONException e) {
                throw new RuntimeException(e);
            }
        }
        protected void onPostExecute(JSONObject json){
            TextView rating = findViewById(R.id.rating_value);
            TextView age = findViewById(R.id.age);
            TextView dur = findViewById(R.id.duration);
            TextView desc = findViewById(R.id.descriptions);
            try {
                rating.setText(json.getString("imdbRating"));
                age.setText(json.getString("ageRating")+"+ ");
                dur.setText(json.getString("duration")+" мин");
                desc.setText(json.getString("description"));
                new ImageTask().execute(json.getString("vertical_poster"));
            } catch (JSONException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cinema);
//        Bundle extras = getIntent().getExtras();
//        String value = "";
//        if (extras != null) {
//            value = extras.getString("json");
//        }
//        try {
//            JSONObject json = new JSONObject(value);
//            link = json.getString("link");
//            TextView title = findViewById(R.id.name_film);
//            title.setText(json.getString("title"));
//            new VolnaGetTask().execute(json.getString("info"));
//        } catch (JSONException e) {
//            throw new RuntimeException(e);
//        }

    }
    public void to_video(View view){
        Intent intent = new Intent(this, VideoActivity.class);
        startActivity(intent);
        intent.putExtra("link",link);
        startActivity(intent);
    }

    public void download(View view) throws JSONException {

        Bundle extras = getIntent().getExtras();
        String value = "";
        if (extras != null) {
            value = extras.getString("json");
        }
        JSONObject json = new JSONObject(value);
        link_name = json.getString("link");

        link = ("http://176.77.109.225/content/films/" + link_name);

        new VideoDownloader(this, link_name).execute(link);
    }

}