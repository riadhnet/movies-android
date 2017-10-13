package com.riadh.movies.service;

import retrofit2.Call;
import retrofit2.Callback;


public class NetworkRetryRequest<T> {

    private Call<T> call;
    private Callback<T> callback;

    NetworkRetryRequest(Call<T> tCall, Callback<T> tCallback) {
        call = tCall;
        callback = tCallback;
    }

    public void retry() {

        if (callback instanceof MyCallbackApi)
            ((MyCallbackApi) callback).before();

        call.clone().enqueue(callback);
    }
}
