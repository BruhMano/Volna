package com.example.test_android;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.google.gson.JsonArray;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    TextView text;
    ImageView img;

    class ImageTask extends AsyncTask<String, Void, Bitmap>{

        @Override
        protected Bitmap doInBackground(String... images) {
            URL url = null;
            try {
                url = new URL(images[0]);
                Bitmap bitmap = BitmapFactory.decodeStream((InputStream)url.getContent());
                return bitmap;
            } catch (MalformedURLException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        protected void onPostExecute(Bitmap  bitmap){
            img.setImageBitmap(bitmap);
        }
    }

    class VolnaQueryTask extends AsyncTask<String, Void, String>{

        @Override
        protected String doInBackground(String... urls) {
            CloseableHttpClient httpClient = HttpClients.createDefault();
            try {
                HttpResponse response = httpClient.execute(new HttpGet(urls[0]));
                HttpEntity entity = response.getEntity();
                String responseString = EntityUtils.toString(entity);
                return responseString;
            } catch (ClientProtocolException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        protected void onPostExecute(String response){
            Gson gson = new Gson();
            JsonArray jsonArray = gson.fromJson(response, JsonArray.class);
            String json = jsonArray.get(0).toString();
            Movie shrek = gson.fromJson(json, Movie.class);
            text.setText(shrek.title);
            new ImageTask().execute("http://176.77.93.89/content/posters/36c57d9f4443e02ed06b6cdd408d78a1.jpg");
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        text = findViewById(R.id.texti);
        img = findViewById(R.id.imageView2);
        String url = "http://176.77.93.89/main_screeen.php";
        new VolnaQueryTask().execute(url);

    }
    public void to_cinema(View view){
        Intent intent = new Intent(this, cinema.class);
        startActivity(intent);
    }

}
