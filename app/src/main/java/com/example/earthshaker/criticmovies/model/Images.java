package com.example.earthshaker.criticmovies.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.SerializedName;
import java.util.List;

/**
 * Created by earthshaker on 5/19/17.
 */

public class Images implements Parcelable {
  public static final Parcelable.Creator<Images> CREATOR = new Parcelable.Creator<Images>() {
    @Override public Images createFromParcel(Parcel source) {
      return new Images(source);
    }

    @Override public Images[] newArray(int size) {
      return new Images[size];
    }
  };
  @SerializedName("base_url") private String baseUrl;
  @SerializedName("logo_sizes") private List<String> logoSizes;
  @SerializedName("profile_sizes") private List<String> profileSizes;

  public Images() {
  }

  protected Images(Parcel in) {
    this.baseUrl = in.readString();
    this.logoSizes = in.createStringArrayList();
    this.profileSizes = in.createStringArrayList();
  }

  @Override public int describeContents() {
    return 0;
  }

  @Override public void writeToParcel(Parcel dest, int flags) {
    dest.writeString(this.baseUrl);
    dest.writeStringList(this.logoSizes);
    dest.writeStringList(this.profileSizes);
  }

  public String getBaseUrl() {
    return baseUrl;
  }

  public void setBaseUrl(String baseUrl) {
    this.baseUrl = baseUrl;
  }

  public List<String> getLogoSizes() {
    return logoSizes;
  }

  public void setLogoSizes(List<String> logoSizes) {
    this.logoSizes = logoSizes;
  }

  public List<String> getProfileSizes() {
    return profileSizes;
  }

  public void setProfileSizes(List<String> profileSizes) {
    this.profileSizes = profileSizes;
  }
}
