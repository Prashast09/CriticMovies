package com.example.earthshaker.criticmovies;

import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.view.View;
import com.example.earthshaker.criticmovies.adapter.DashboardViewPagerAdapter;
import com.example.earthshaker.criticmovies.common.di.master.ComponentFactory;
import javax.inject.Inject;
import javax.inject.Named;

/**
 * Created by earthshaker on 5/18/17.
 */

public class DashboardViewHolder {

  @Inject @Named("dashboardFragmentManager") FragmentManager fragmentManager;
  private DashboardViewPagerAdapter dashboardViewPagerAdapter;
  private TabLayout tabLayout;
  private ViewPager viewPager;

  public DashboardViewHolder(View view) {

    tabLayout = (TabLayout) view.findViewById(R.id.tab_layout);
    viewPager = (ViewPager) view.findViewById(R.id.view_pager);
    setupComponent();
    setViewPager();
  }

  private void setViewPager() {
    dashboardViewPagerAdapter = new DashboardViewPagerAdapter(fragmentManager);
    dashboardViewPagerAdapter.clearFragments();

    tabLayout.addTab(tabLayout.newTab().setText("Linear View"));
    dashboardViewPagerAdapter.addFragment();
  }

  private void setupComponent() {
    ComponentFactory.getInstance().getDashboardComponent().inject(this);
  }
}
