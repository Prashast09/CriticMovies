package com.example.earthshaker.criticmovies.eventbus;

import com.example.earthshaker.criticmovies.model.ConfigurationResponse;
import com.example.earthshaker.criticmovies.model.MoviesConfig;

/**
 * Created by earthshaker on 5/19/17.
 */

public class AppEvents {
  public static class FetchConfiguration {
    ConfigurationResponse configurationResponse;

    public FetchConfiguration(ConfigurationResponse configuration) {
      this.configurationResponse = configuration;
    }

    public ConfigurationResponse getConfigurationResponse() {
      return configurationResponse;
    }
  }

  public static class FetchMovies {
    MoviesConfig moviesConfig;

    public FetchMovies(MoviesConfig moviesConfig) {

      this.moviesConfig = moviesConfig;
    }

    public MoviesConfig getMoviesConfig() {
      return moviesConfig;
    }
  }

}
