package com.example.earthshaker.criticmovies;

import android.app.Application;
import com.example.earthshaker.criticmovies.common.di.master.ComponentFactory;

/**
 * Created by earthshaker on 5/18/17.
 */

public class MovieTrakrApplication extends Application {
  @Override public void onCreate() {
    super.onCreate();
    ComponentFactory.getInstance().initializeComponent(this);
  }
}
