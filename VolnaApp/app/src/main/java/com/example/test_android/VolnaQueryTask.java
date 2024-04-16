package com.example.test_android;

import android.os.AsyncTask;

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
import java.util.ArrayList;
import java.util.List;

class VolnaQueryTask extends AsyncTask<String, Void, String> {

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
}