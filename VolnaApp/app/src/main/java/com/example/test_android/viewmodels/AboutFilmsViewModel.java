package com.example.test_android.viewmodels;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.test_android.models.Film;
import com.example.test_android.models.api.ApiFactory;
import com.example.test_android.models.db.FilmDao;
import com.example.test_android.models.db.FilmDatabase;
import com.example.test_android.models.reviews.Review;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class AboutFilmsViewModel extends AndroidViewModel {
    private final static String TAG = "AboutFilmsViewModel";
    private final FilmDao movieDao;
    CompositeDisposable compositeDisposable = new CompositeDisposable();
    MutableLiveData<List<Review>> reviewLiveData = new MutableLiveData<>();

    public AboutFilmsViewModel(@NonNull Application application) {

        super(application);
        movieDao = FilmDatabase.getInstance(application).movieDao();
    }

    public LiveData<List<Review>> getReviewLiveData() {
        return reviewLiveData;
    }


    public LiveData<Film> getFavoriteFilm(int id) {
        return movieDao.getFavoriteFilmById(id);
    }

    public void insertMovieDb(Film movie) {
        Disposable disposable = movieDao.insertFilm(movie)
                .subscribeOn(Schedulers.io())
                .subscribe();

        compositeDisposable.add(disposable);
    }

    public void removeMovieDb(int movieId) {
        Disposable disposable = movieDao.removeFilm(movieId)
                .subscribeOn(Schedulers.io())
                .subscribe();

        compositeDisposable.add(disposable);
    }

    public void loadReviews(int movieId) {
        Disposable disposable = ApiFactory.apiService.loadReviewResponse(movieId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(reviewResponse -> reviewLiveData.setValue(reviewResponse.getReviews()),
                        throwable -> Log.d(TAG, throwable.toString()));
        compositeDisposable.add(disposable);
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        compositeDisposable.clear();
    }
}
