package com.example.earthshaker.criticmovies.model;

import android.os.Parcelable;
import com.google.gson.annotations.SerializedName;

/**
 * Created by earthshaker on 5/19/17.
 */

public class ConfigurationResponse {

  @SerializedName("images") private Images images;

  public Images getImages() {
    return images;
  }

  public void setImages(Images images) {
    this.images = images;
  }
}
