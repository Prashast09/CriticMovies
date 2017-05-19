package com.example.earthshaker.criticmovies.eventbus;

import com.example.earthshaker.criticmovies.model.ConfigurationResponse;
import com.example.earthshaker.criticmovies.model.MoviesConfig;
import java.util.List;

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
    List<MoviesConfig> moviesConfigs;

    public FetchMovies(List<MoviesConfig> moviesConfigs) {
      this.moviesConfigs = moviesConfigs;
    }

    public List<MoviesConfig> getMoviesConfigs() {
      return moviesConfigs;
    }
  }
}
