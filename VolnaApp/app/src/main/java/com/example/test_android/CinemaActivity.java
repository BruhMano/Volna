package com.example.test_android;

import static com.example.test_android.R.color.default_color_error;
import static com.example.test_android.api.UrlConstant.BASE_URL;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.test_android.api.DownloadMovieFactory;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CinemaActivity extends AppCompatActivity {

    private final static String EXTRA_MOVIE = "film";
    private ImageView pictureMovieAboutMovieActivity;

    private TextView nameMovieAboutMovieActivity;
    private TextView ratingMovieAboutMovieActivity;

    private TextView ageMovieAboutMovieActivity;
    private TextView durationMovieAboutMovieActivity;
    private TextView genreMovieAboutMovieActivity;
    private TextView descriptionsMovieAboutMovieActivity;

    private TextView resultDownload;

    private int totalFileSize;

    public static Intent createIntent(Context context, Movie movie) {
        Intent intent = new Intent(context, CinemaActivity.class);
        intent.putExtra(EXTRA_MOVIE, movie);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        setContentView(R.layout.activity_cinema);
        initViews();

        Movie movie = (Movie) getIntent().getSerializableExtra(EXTRA_MOVIE);
        Glide.with(this)
                .load(movie.getVertical_poster())
                .into(pictureMovieAboutMovieActivity);

        nameMovieAboutMovieActivity.setText(movie.getTitle());
        ratingMovieAboutMovieActivity.setText(String.valueOf(movie.getImdb()));
        ageMovieAboutMovieActivity.setText(String.valueOf(movie.getAge()) + "+");
        durationMovieAboutMovieActivity.setText(String.valueOf(movie.getDuration()) + " мин.");
        genreMovieAboutMovieActivity.setText(movie.getGenre());
        descriptionsMovieAboutMovieActivity.setText(movie.getDesc());
    }

    private void initViews() {
        //model = new ViewModelProvider(this).get(AboutFilmsViewModel.class);
        pictureMovieAboutMovieActivity = findViewById(R.id.imageCinema);

        nameMovieAboutMovieActivity = findViewById(R.id.name_film);
        ratingMovieAboutMovieActivity = findViewById(R.id.rating_value);
        ageMovieAboutMovieActivity = findViewById(R.id.age);
        durationMovieAboutMovieActivity = findViewById(R.id.duration);
        genreMovieAboutMovieActivity = findViewById(R.id.genre);
        descriptionsMovieAboutMovieActivity = findViewById(R.id.descriptions);
        resultDownload = findViewById(R.id.resultDownload);

        //imageViewStar = findViewById(R.id.favoriteStar);
        /*reviewRecycler = findViewById(R.id.reviewRecyclerView);
        reviewAdapter = new ReviewAdapter();
        reviewRecycler.setAdapter(reviewAdapter);*/
    }

    public void toVideo(View view){
        Movie movie = (Movie) getIntent().getSerializableExtra(EXTRA_MOVIE);
        String link = BASE_URL + "content/films/x1000.mp4";
        //link.replace("//", "/");
        Log.d(this.getClass().getSimpleName(), "Ulr Film: " + link);

        Intent intent = new Intent(this, VideoActivity.class);
        startActivity(intent);
        intent.putExtra("link",link);
        startActivity(intent);
    }

    @SuppressLint("ResourceAsColor")
    public void downloadFilm(View v) {
        try {
            Movie movie = (Movie) getIntent().getSerializableExtra(EXTRA_MOVIE);
            String link = movie.getLink();
            final String fileName =  link.substring(link.lastIndexOf("/")); //Paths.get(new URL(movie.getLink()).getPath()).getFileName().toString();
            final String urlFile = link;
            Log.d(this.getClass().getSimpleName(), "urlFilm:" + urlFile);
            Call<ResponseBody> call = DownloadMovieFactory.DOWNLOAD_MOVIE_SERVICE.downloadFile(urlFile);
            call.enqueue(new Callback<ResponseBody>() {
                @SuppressLint("ResourceAsColor")
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    try {
                        Log.i(this.getClass().getSimpleName(), "Response came from server: " +
                                response.code());
                        downloadFile(response.body(), fileName);
                        resultDownload.setText("Файл успешно загружен");
                    } catch (Exception e) {
                        Log.e(this.getClass().getSimpleName(), e.getMessage(), e);
                        resultDownload.setText(R.string.errorDowloadFiIe);

                    }
                }
                @SuppressLint("ResourceAsColor")
                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {
                    Log.e(this.getClass().getSimpleName(), t.toString());
                    resultDownload.setText(R.string.errorDowloadFiIe);
                }
            });
        } catch (Exception e) {
            resultDownload.setText(R.string.errorDowloadFiIe);
            Log.e(this.getClass().getSimpleName(), e.getMessage(), e);
        }
    }
    private void downloadFile(ResponseBody body, String fileName) throws IOException {

        int count;
        byte data[] = new byte[1024 * 4];
        long fileSize = body.contentLength();
        InputStream bis = new BufferedInputStream(body.byteStream(), 1024 * 8);

        File outputFile = new File(Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_DOWNLOADS), fileName);

        OutputStream output = Files.newOutputStream(outputFile.toPath());
        long total = 0;
        long startTime = System.currentTimeMillis();
        int timeCount = 1;
        while ((count = bis.read(data)) != -1) {

            total += count;
            totalFileSize = (int) (fileSize / (Math.pow(1024, 2)));
            double current = Math.round(total / (Math.pow(1024, 2)));

            int progress = (int) ((total * 100) / fileSize);

            long currentTime = System.currentTimeMillis() - startTime;

            Download download = new Download();

            download.setTotalFileSize(totalFileSize);

            if (currentTime > 1000 * timeCount) {
                download.setCurrentFileSize((int) current);
                download.setProgress(progress);
                timeCount++;
            }

            output.write(data, 0, count);
        }
        //onDownloadComplete();
        output.flush();
        output.close();
        bis.close();
    }
}