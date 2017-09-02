package com.riadh.movies.service;


import android.support.annotation.NonNull;

import com.riadh.movies.ui.BaseActivity;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public abstract class MyCallbackApi<T, A extends BaseActivity> implements Callback<T> {

    private A activity;

    public MyCallbackApi(A context) {
        this.activity = context;
        before();
    }


    public abstract void onSuccess(Response<T> response);

    private void onUnexpectedError(Call<T> call, Throwable t) {
        activity.handleUnexpectedError(t);
    }


    public void before() {

    }

    public void after() {

    }

    protected void onApiError(Response<T> response) {

        try {
            activity.handleAPIErrors(response.errorBody());

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    protected void handleNotFoundError(Response<T> response) {
    }

    protected void handleUnAuthorizedRequest(Response<T> response) {
        activity.handleUnAuthorizedRequest(response);
    }


    private void onNetworkError(Call<T> call) {
        activity.onNetworkError(new NetworkRetryRequest<>(call, this));
    }


    @Override
    public void onResponse(@NonNull Call<T> call, @NonNull Response<T> response) {
        if (activity == null) {
            return;
        }
        after();
        // activity.updateHeaderData(response.headers());

        if (response.code() == 401 || response.code() == 203) {
            handleUnAuthorizedRequest(response);
            return;
        }

        if (response.code() == 404) {
            onApiError(response);
            return;
        }


        if (!response.isSuccessful() || response.code() == 422 || response.code() == 503) {
            onApiError(response);
            return;
        }

        onSuccess(response);

    }


    @Override
    public void onFailure(@NonNull Call<T> call, Throwable t) {
        if (activity == null) {
            return;
        }
        after();

        if (t instanceof IOException) {
            onNetworkError(call);
            return;
        }

        onUnexpectedError(call, t);
    }

}
