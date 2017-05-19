package com.example.earthshaker.criticmovies.di;

import android.content.ComponentName;
import android.support.v4.app.FragmentManager;
import android.util.DisplayMetrics;
import com.example.earthshaker.criticmovies.common.activity.BaseActivity;
import dagger.Module;
import dagger.Provides;
import javax.inject.Named;

/**
 * Created by earthshaker on 5/18/17.
 */
@Module public class DashboardModule {
  private BaseActivity baseActivity;

  public DashboardModule(BaseActivity activity) {
    this.baseActivity = activity;
  }

  @Provides @DashboardScope @Named("dashboardFragmentManager")
  public FragmentManager getFragmentManager() {
    return baseActivity.getSupportFragmentManager();
  }

  @Provides @DashboardScope @Named("componentName") public ComponentName getComponentName() {
    return baseActivity.getComponentName();
  }

  @Provides @DashboardScope @Named("deviceDensity") public DisplayMetrics getDisplayMetrics() {
    return baseActivity.getResources().getDisplayMetrics();
  }
}
