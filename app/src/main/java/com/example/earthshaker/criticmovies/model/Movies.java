package com.example.earthshaker.criticmovies.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by earthshaker on 5/20/17.
 */

public class Movies implements Parcelable {

  public static final Parcelable.Creator<Movies> CREATOR = new Parcelable.Creator<Movies>() {
    @Override public Movies createFromParcel(Parcel source) {
      return new Movies(source);
    }

    @Override public Movies[] newArray(int size) {
      return new Movies[size];
    }
  };
  @SerializedName("poster_path") @Expose private String posterPath;
  @SerializedName("overview") @Expose private String overview;
  @SerializedName("title") @Expose private String title;

  public Movies() {
  }

  protected Movies(Parcel in) {
    this.posterPath = in.readString();
    this.overview = in.readString();
    this.title = in.readString();
  }

  @Override public int describeContents() {
    return 0;
  }

  @Override public void writeToParcel(Parcel dest, int flags) {
    dest.writeString(this.posterPath);
    dest.writeString(this.overview);
    dest.writeString(this.title);
  }

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
