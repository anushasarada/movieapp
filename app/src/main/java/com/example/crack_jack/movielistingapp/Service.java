package com.example.crack_jack.movielistingapp;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by sarada on 12/23/2017.
 */

public interface Service {

    @GET("movie/popular")
    Call<MovieResponse> getPopularMovies(@Query("api_key") String apiKey);

    @GET("movie/top_rated")
    Call<MovieAdapter>getTopRatedMovies(@Query("api_key") String apiKey);
}
