package com.example.test_android;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    JsonArray JSONresponse;

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
            //img.setImageBitmap(bitmap);
        }
    }

    class VolnaQueryTask extends AsyncTask<String, Void, String>{

        @Override
        protected String doInBackground(String... urls) {
            CloseableHttpClient httpClient = HttpClients.createDefault();
            try {
                HttpPost httppost = new HttpPost(urls[0]);
                List<NameValuePair> params = new ArrayList<NameValuePair>(2);
                params.add(new BasicNameValuePair("query", "SELECT * FROM video LIMIT 3"));
                httppost.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));
                HttpResponse response = httpClient.execute(httppost);
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
            JSONresponse = gson.fromJson(response, JsonArray.class);
            int[][] ids =  {{R.id.img1, R.id.title1, R.id.desc1},
                           {R.id.img2, R.id.title2, R.id.desc2},
                           {R.id.img3, R.id.title3, R.id.desc3}};
            for(int i = 0;i<3;++i) {
                String json = JSONresponse.get(i).toString();
                TextView text = findViewById(ids[i][1]);
                ImageView img = findViewById(ids[i][0]);
                TextView desc = findViewById(ids[i][2]);
                Movie shrek = gson.fromJson(json, Movie.class);
                text.setText(shrek.title);
                desc.setText(shrek.title);
                
                //new ImageTask().execute("http://176.77.109.225/content/posters/36c57d9f4443e02ed06b6cdd408d78a1.jpg");
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String url = "http://176.77.109.225/main_screeen.php";
        new VolnaQueryTask().execute(url);
    }
    public void to_movie(View view){
        String json = JSONresponse.get(0).toString();
        Intent intent = new Intent(this, cinema.class);
        intent.putExtra("json",json);
        startActivity(intent);
    }
}
