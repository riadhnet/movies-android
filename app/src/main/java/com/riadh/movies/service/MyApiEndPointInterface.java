package com.riadh.movies.service;


import com.riadh.movies.app.Constants.API;
import com.riadh.movies.models.MoviesResults;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MyApiEndPointInterface {


    @GET(API.UP_COMING)
    Call<MoviesResults> getLatest(@Query("page") int page);


    @GET(API.SEARCH_MOVIE)
    Call<MoviesResults> searchMovie(@Query("query") String Query, @Query("page") int page);


}
