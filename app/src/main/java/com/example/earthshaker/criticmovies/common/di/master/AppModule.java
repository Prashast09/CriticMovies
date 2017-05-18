package com.example.earthshaker.criticmovies.common.di.master;

import android.content.Context;
import com.example.earthshaker.criticmovies.MovieTrakrApplication;
import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;

/**
 * Created by earthshaker on 5/18/17.
 */

@Module public class AppModule {

  public MovieTrakrApplication movieTrakrApplication;

  public AppModule(MovieTrakrApplication application) {
    movieTrakrApplication = application;
  }

  @Provides @Singleton Context providesContext() {
    return movieTrakrApplication;
  }
}
