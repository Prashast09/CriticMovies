package com.example.earthshaker.criticmovies.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by earthshaker on 5/19/17.
 */

public class MoviesConfig implements Parcelable {

  public static final Parcelable.Creator<MoviesConfig> CREATOR =
      new Parcelable.Creator<MoviesConfig>() {
        @Override public MoviesConfig createFromParcel(Parcel source) {
          return new MoviesConfig(source);
        }

        @Override public MoviesConfig[] newArray(int size) {
          return new MoviesConfig[size];
        }
      };
  @SerializedName("page") private int page;
  @SerializedName("results") private List<Movies> moviesList;

  public MoviesConfig() {
  }

  protected MoviesConfig(Parcel in) {
    this.page = in.readInt();
    this.moviesList = new ArrayList<Movies>();
    in.readList(this.moviesList, Movies.class.getClassLoader());
  }

  @Override public int describeContents() {
    return 0;
  }

  @Override public void writeToParcel(Parcel dest, int flags) {
    dest.writeInt(this.page);
    dest.writeList(this.moviesList);
  }

  public int getPage() {
    return page;
  }

  public void setPage(int page) {
    this.page = page;
  }

  public List<Movies> getMoviesList() {
    return moviesList;
  }

  public void setMoviesList(List<Movies> moviesList) {
    this.moviesList = moviesList;
  }
}
