package com.example.earthshaker.criticmovies.di;

import com.example.earthshaker.criticmovies.activity.DashboardActivity;
import com.example.earthshaker.criticmovies.activity.DashboardViewHolder;
import com.example.earthshaker.criticmovies.fragment.DashboardBaseFragment;
import com.example.earthshaker.criticmovies.fragment.DashboardFragmentHolder;
import dagger.Subcomponent;

/**
 * Created by earthshaker on 5/18/17.
 */

@DashboardScope @Subcomponent(modules = DashboardModule.class) public interface DashboardComponent {
  void inject(DashboardActivity dashboardActivity);

  void inject(DashboardViewHolder dashboardViewHolder);

  void inject(DashboardFragmentHolder dashboardFragmentHolder);

  void inject(DashboardBaseFragment dashboardBaseFragment);
}
