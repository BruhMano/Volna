package com.example.test_android;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class VideoDownloader extends AsyncTask<String, Void, Boolean> {

    private Context context;
    private String linkName;

    public VideoDownloader(Context context, String linkName) {
        this.context = context;
        this.linkName = linkName;
    }

    @Override
    protected Boolean doInBackground(String... params) {


        String videoUrl = params[0];
        try {
            URL url = new URL(videoUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.connect();

            // Определяем путь для сохранения видео
            String fileName = linkName + ".mp4";
            //File directory = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getPath());


            File directory = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), "volna");
            if (!directory.exists()) {
                directory.mkdirs();
            }

            File file = new File(directory, fileName);

            InputStream input = new BufferedInputStream(url.openStream());
            OutputStream output = new FileOutputStream(file);

            byte[] data = new byte[1024];
            int count;
            while ((count = input.read(data)) != -1) {
                output.write(data, 0, count);
            }

            output.flush();
            output.close();
            input.close();
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    protected void onPostExecute(Boolean result) {
        ImageView imageView = ((Activity) context).findViewById(R.id.download_video);


        if (result) {
            Toast.makeText(context, "Видео успешно загружено", Toast.LENGTH_SHORT).show();
            imageView.setImageResource(R.drawable.play_download);  //замена картинки после загрузки
        } else {
            Toast.makeText(context, "Ошибка при загрузке видео", Toast.LENGTH_SHORT).show();
        }
    }
}
