package com.example.earthshaker.criticmovies.common;

import android.content.Context;
import android.content.SharedPreferences;
import javax.inject.Inject;

/**
 * Created by earthshaker on 5/19/17.
 */

public class SharedPrefUtils {
  @Inject Context context;

  @Inject public SharedPrefUtils() {
  }

  public void putString(String key, String value) {
    final SharedPreferences.Editor editor =
        context.getSharedPreferences("trakrPreference", Context.MODE_PRIVATE).edit();
    editor.putString(key, value);
    editor.apply();
  }

  public String getStringDataByKey(String key, String defaultValue) {
    final SharedPreferences preferences =
        context.getSharedPreferences("trakrPreference", Context.MODE_PRIVATE);

    return preferences.getString(key, defaultValue);
  }
}
