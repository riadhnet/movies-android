package com.riadh.movies.app;


import android.app.Application;
import android.os.StrictMode;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.riadh.movies.service.MyApiEndPointInterface;

import org.androidannotations.annotations.EApplication;
import org.androidannotations.annotations.sharedpreferences.Pref;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@EApplication
public class MyApplication extends Application {

    @Pref
    public static Prefs_ prefs;

    private static MyApiEndPointInterface apiService = null;

    public static MyApiEndPointInterface getApiService() {
        if (apiService == null) {
            HttpLoggingInterceptor logInterceptor = new HttpLoggingInterceptor();
            logInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            Interceptor headerInterceptor = new Interceptor() {
                @Override
                public Response intercept(Chain chain) throws IOException {
                    Request original = chain.request();
                    Request.Builder requestBuilder = original.newBuilder();
                    requestBuilder.addHeader(Constants.HEADER.CONTENT_TYPE, Constants.HEADER.APPLICATION_JSON);
                    Request request = requestBuilder.build();
                    return chain.proceed(request);
                }
            };

            final OkHttpClient okHttpClient = new OkHttpClient.Builder().addInterceptor(logInterceptor)
                    .addInterceptor(headerInterceptor)
                    .readTimeout(120, TimeUnit.SECONDS)
                    .writeTimeout(120, TimeUnit.SECONDS)
                    .connectTimeout(120, TimeUnit.SECONDS)
                    .build();

            Gson gson = new GsonBuilder()
                    .create();

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(Constants.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .client(okHttpClient)
                    .build();

            apiService = retrofit.create(MyApiEndPointInterface.class);

        }
        return apiService;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());
    }


}
