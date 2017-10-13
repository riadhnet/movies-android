package com.riadh.movies.ui;

import android.os.Bundle;
import android.support.annotation.NonNull;

import com.riadh.movies.app.MyApplication;
import com.riadh.movies.models.Genres;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * Splash screen responsible of loading the app.
 */
public class SplashActivity extends BaseActivity {

    MyApplication app;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        app = (MyApplication) getApplication();

        if (app.getGenres() != null) {
            HomeActivity_.intent(this).start();
        }

        MyApplication.getApiService().getGenre().enqueue(new Callback<Genres>() {


            @Override
            public void onResponse(@NonNull Call<Genres> call, @NonNull Response<Genres> response) {
                if (response.isSuccessful()) {
                    app.setGenres(response.body());
                }
                HomeActivity_.intent(SplashActivity.this).start();
            }

            @Override
            public void onFailure(@NonNull Call<Genres> call, @NonNull Throwable t) {

            }
        });


        finish();
    }


}
