package com.example.test_android;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
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
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    JsonArray JSONresponse;
    Bitmap btmp;
    JSONObject jsonObj;

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
            btmp = bitmap;
        }
    }

    class VolnaGetTask extends AsyncTask<String,Void,JSONObject>{

        @Override
        protected JSONObject doInBackground(String... args) {
            CloseableHttpClient httpClient = HttpClients.createDefault();
            try {
                HttpResponse response = httpClient.execute(new HttpGet("http://176.77.109.225/content/information/"+args[0]));
                HttpEntity entity = response.getEntity();
                String responseString = EntityUtils.toString(entity, "iso-8859-1");
                JSONObject jsonObject = new JSONObject(responseString);

                return jsonObject;
            } catch (ClientProtocolException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (JSONException e) {
                throw new RuntimeException(e);
            }
        }
        protected void onPostExecute(JSONObject jsonObject){
            jsonObj = jsonObject;
            try {
                new ImageTask().execute(jsonObject.getString("horizontal_poster"));
            } catch (JSONException e) {
                throw new RuntimeException(e);
            }
        }
    }

//    class VolnaQueryPostTask extends AsyncTask<String, Void, String>{
//
//        @Override
//        protected String doInBackground(String... queries) {
//            CloseableHttpClient httpClient = HttpClients.createDefault();
//            try {
//                HttpPost httppost = new HttpPost("http://176.77.109.225/main_screeen.php");
//                List<NameValuePair> params = new ArrayList<NameValuePair>(2);
//                params.add(new BasicNameValuePair("query", queries[0]));
//                httppost.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));
//                HttpResponse response = httpClient.execute(httppost);
//                HttpEntity entity = response.getEntity();
//                String responseString = EntityUtils.toString(entity);
//                return responseString;
//            } catch (ClientProtocolException e) {
//                throw new RuntimeException(e);
//            } catch (IOException e) {
//                throw new RuntimeException(e);
//            }
//        }
//        protected void onPostExecute(String response){
//            Gson gson = new Gson();
//            JSONresponse = gson.fromJson(response, JsonArray.class);
//            int[][] ids =  {{R.id.img1, R.id.title1, R.id.desc1},
//                           {R.id.img2, R.id.title2, R.id.desc2},
//                           {R.id.img3, R.id.title3, R.id.desc3}};
//            for(int i = 0;i<3;++i) {
//                String json = JSONresponse.get(i).toString();
//                TextView text = findViewById(ids[i][1]);
//                ImageView img = findViewById(ids[i][0]);
//                TextView description = findViewById(ids[i][2]);
//                Movie movie = gson.fromJson(json, Movie.class);
//                new VolnaGetTask().execute(movie.info);
//                text.setText(movie.title);
//                description.setText(movie.title);
////                try {
////                    description.setText(jsonObj.getString("year")+" "+jsonObj.getString("ageRating")+"+ ");
////                } catch (JSONException e) {
////                    throw new RuntimeException(e);
////                }
//                //new ImageTask().execute("http://176.77.109.225/content/posters/36c57d9f4443e02ed06b6cdd408d78a1.jpg");
//            }
//        }
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String query = "SELECT * FROM video LIMIT 3";
        //new VolnaQueryPostTask().execute(query);
    }
    public void to_movieExample(View view){
        Intent intent = new Intent(this, CinemaActivity.class);
        startActivity(intent);
    }
    public void to_movie2(View view){
        String json = JSONresponse.get(1).toString();
        Intent intent = new Intent(this, CinemaActivity.class);
        intent.putExtra("json",json);
        startActivity(intent);
    }
    public void to_movie3(View view){
        String json = JSONresponse.get(2).toString();
        Intent intent = new Intent(this, CinemaActivity.class);
        intent.putExtra("json",json);
        startActivity(intent);
    }
}
