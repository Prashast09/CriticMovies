package com.example.earthshaker.criticmovies.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;

/**
 * Created by earthshaker on 5/19/17.
 */

public class MoviesConfig {

  @SerializedName("poster_path")
  @Expose
  private String posterPath;

  @SerializedName("overview")
  @Expose
  private String overview;

  @SerializedName("title")
  @Expose
  private String title;

  public String getPosterPath() {
    return posterPath;
  }

  public void setPosterPath(String posterPath) {
    this.posterPath = posterPath;
  }

  public String getOverview() {
    return overview;
  }

  public void setOverview(String overview) {
    this.overview = overview;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

}
