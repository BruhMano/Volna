package com.example.test_android.viewmodels;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.test_android.Movie;
import com.example.test_android.api.PhpFactory;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class MainHonorViewModel extends AndroidViewModel {
    private final CompositeDisposable compositeDisposable = new CompositeDisposable();
    private final MutableLiveData<List<Movie>> movieResponseMutableLiveData = new MutableLiveData<>();
    private final MutableLiveData<Boolean> isLoadingMovies = new MutableLiveData<>(false);

    private int page = 1;

    public MainHonorViewModel(@NonNull Application application) {
        super(application);
        loadFilms();
    }

    public LiveData<Boolean> getIsLoadingMovies() {
        return isLoadingMovies;
    }

    public LiveData<List<Movie>> getFilmResponseLiveData() {
        return movieResponseMutableLiveData;
    }

    public void loadFilms() {

        Boolean isLoading = isLoadingMovies.getValue();

        //if (isLoading != null && isLoading) return;

        Disposable disposable = PhpFactory.PHP_SERVICE.loadMovies()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(disposable1 -> isLoadingMovies.setValue(true))
                .doAfterTerminate(() -> isLoadingMovies.setValue(false))
                .subscribe(movieResponse -> {
                            List<Movie> loadedMovies = movieResponseMutableLiveData.getValue();
                            //Log.i(this.getClass().getSimpleName(), loadedMovies.toString());
                            if (loadedMovies != null) {
                                loadedMovies.addAll(movieResponse.getFilms());
                                movieResponseMutableLiveData.setValue(loadedMovies);
                            } else {
                                movieResponseMutableLiveData.setValue(movieResponse.getFilms());
                            }
                            page++;
                        }, throwable -> Log.e(this.getClass().getSimpleName(),throwable.toString())
                );
        compositeDisposable.add(disposable);
    }


    @Override
    protected void onCleared() {
        super.onCleared();
        compositeDisposable.clear();
    }
}
