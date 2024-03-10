package com.example.test_android.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.test_android.models.Film;
import com.example.test_android.models.api.ApiFactory;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class MainViewModel extends AndroidViewModel {
    private final CompositeDisposable compositeDisposable = new CompositeDisposable();
    private final MutableLiveData<List<Film>> filmResponseMutableLiveData = new MutableLiveData<>();

    private final MutableLiveData<Boolean> isLoadingMovies = new MutableLiveData<>(false);

    private int page = 1;

    public MainViewModel(@NonNull Application application) {
        super(application);
        loadFilms();
    }

    public LiveData<Boolean> getIsLoadingMovies() {
        return isLoadingMovies;
    }

    public LiveData<List<Film>> getFilmResponseLiveData() {
        return filmResponseMutableLiveData;
    }

    public void loadFilms() {

        Boolean isLoading = isLoadingMovies.getValue();

        if (isLoading != null && isLoading) return;

        Disposable disposable = ApiFactory.apiService.loadMovies(page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(disposable1 -> isLoadingMovies.setValue(true))
                .doAfterTerminate(() -> isLoadingMovies.setValue(false))
                .subscribe(movieResponse -> {
                            List<Film> loadedMovies = filmResponseMutableLiveData.getValue();

                            if (loadedMovies != null) {
                                loadedMovies.addAll(movieResponse.getFilms());
                                filmResponseMutableLiveData.setValue(loadedMovies);
                            } else {
                                filmResponseMutableLiveData.setValue(movieResponse.getFilms());
                            }
                            page++;
                        }, throwable -> System.out.println(throwable.toString())
                );
        compositeDisposable.add(disposable);
    }


    @Override
    protected void onCleared() {
        super.onCleared();
        compositeDisposable.clear();
    }
}
