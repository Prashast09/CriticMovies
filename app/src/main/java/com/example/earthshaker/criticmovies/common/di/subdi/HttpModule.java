package com.example.earthshaker.criticmovies.common.di.subdi;

import com.example.earthshaker.criticmovies.common.service.MovieApiService;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import dagger.Module;
import dagger.Provides;
import javax.inject.Named;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by earthshaker on 5/18/17.
 */

@Module public class HttpModule {

  @HttpScope @Provides @Named("httpService") public MovieApiService createMovieService() {
    Retrofit.Builder builder =
        new Retrofit.Builder().addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("http://api.themoviedb.org/3/");

    OkHttpClient client = new OkHttpClient.Builder().addInterceptor(chain -> {
      Request request = chain.request();
      Request newReq = request.newBuilder().build();
      return chain.proceed(newReq);
    }).build();

    return builder.client(client).build().create(MovieApiService.class);
  }

 /* @HttpScope @Provides @Named("httpService") public MovieApiService createMovieService() {
    OkHttpClient.Builder builder = new OkHttpClient.Builder().retryOnConnectionFailure(false);
    if (BuildConfig.DEBUG) {
      builder.addNetworkInterceptor(new com.facebook.stetho.okhttp3.StethoInterceptor());
    }

    Retrofit.Builder retrofit = new Retrofit.Builder();

    retrofit.client(builder.build())
        .callbackExecutor(Runnable::run)
        .baseUrl("https://api.themoviedb.org/3/")
        .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create());
    Retrofit restadapter = retrofit.build();
    return restadapter.create(MovieApiService.class);
  }*/
}
