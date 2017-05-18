package com.example.earthshaker.criticmovies.di;

import com.example.earthshaker.criticmovies.DashboardActivity;
import com.example.earthshaker.criticmovies.DashboardViewHolder;
import dagger.Subcomponent;

/**
 * Created by earthshaker on 5/18/17.
 */

@Subcomponent(modules = DashboardModule.class) @DashboardScope public interface DashboardComponent {
  void inject(DashboardActivity dashboardActivity);

  void inject(DashboardViewHolder dashboardViewHolder);
}
