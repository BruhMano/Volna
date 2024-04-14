package com.example.test_android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MainActivity extends AppCompatActivity {

    TextView text;

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
            text.setText(response);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        text = findViewById(R.id.texti);
        String url = "http://176.77.93.89/main_screeen.php";
        new VolnaQueryTask().execute(url);

    }
    public void to_cinema(View view){
        Intent intent = new Intent(this, cinema.class);
        startActivity(intent);
    }

}
