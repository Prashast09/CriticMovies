package com.example.earthshaker.criticmovies.common.service;

import com.example.earthshaker.criticmovies.model.ConfigurationResponse;
import com.example.earthshaker.criticmovies.model.MoviesConfig;
import io.reactivex.Observable;
import java.util.List;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by earthshaker on 5/18/17.
 */

public interface MovieApiService {


  @GET("configuration") Observable<ConfigurationResponse> getConfiguration(
      @Query("api_key") String apiKey);

  @GET("discover/movie") Observable<List<MoviesConfig>> getMovies(@Query("api_key") String apiKey,
      @Query("page") Integer page);
}
