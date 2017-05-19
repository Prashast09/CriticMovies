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
    initializeStetho();
  }

  private void initializeStetho() {

    com.facebook.stetho.Stetho.initialize(
        com.facebook.stetho.Stetho.newInitializerBuilder(getApplicationContext())
            .enableDumpapp(
                com.facebook.stetho.Stetho.defaultDumperPluginsProvider(getApplicationContext()))
            .enableWebKitInspector(
                com.facebook.stetho.Stetho.defaultInspectorModulesProvider(getApplicationContext()))
            .build());
  }
}
