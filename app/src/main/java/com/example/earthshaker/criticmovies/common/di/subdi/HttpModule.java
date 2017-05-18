package com.example.earthshaker.criticmovies.common.di.subdi;

import android.text.TextUtils;
import com.example.earthshaker.criticmovies.common.service.MovieApiService;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import dagger.Module;
import dagger.Provides;
import javax.inject.Named;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static java.lang.String.format;

/**
 * Created by earthshaker on 5/18/17.
 */

@Module public class HttpModule {

  @Provides @Named("httpService") public static MovieApiService createMovieService() {
    Retrofit.Builder builder =
        new Retrofit.Builder().addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://developers.themoviedb.org/3");

    return builder.build().create(MovieApiService.class);
  }
}
