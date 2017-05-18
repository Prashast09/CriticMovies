package com.example.earthshaker.criticmovies.common.di.subdi;

import com.example.earthshaker.criticmovies.common.activity.BaseActivity;
import com.example.earthshaker.criticmovies.di.DashboardComponent;
import com.example.earthshaker.criticmovies.di.DashboardModule;
import dagger.Subcomponent;

/**
 * Created by earthshaker on 5/18/17.
 */

@HttpScope @Subcomponent(modules = HttpModule.class) public interface HttpComponent {
  void inject(BaseActivity baseActivity);

  DashboardComponent plus(DashboardModule dashboardModule);


}
