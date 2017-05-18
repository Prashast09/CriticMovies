package com.example.earthshaker.criticmovies.di;

import android.app.FragmentManager;
import com.example.earthshaker.criticmovies.common.activity.BaseActivity;
import dagger.Module;
import dagger.Provides;
import javax.inject.Named;

/**
 * Created by earthshaker on 5/18/17.
 */
@Module public class DashboardModule {
  BaseActivity baseActivity;

  public DashboardModule(BaseActivity activity) {
    this.baseActivity = activity;
  }

  @Provides @Named("dashboardFragmentManager") public FragmentManager getFragmentManager() {
    return baseActivity.getFragmentManager();
  }
}
