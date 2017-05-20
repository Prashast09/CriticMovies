package com.example.earthshaker.criticmovies.activity;

import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.view.View;
import com.example.earthshaker.criticmovies.R;
import com.example.earthshaker.criticmovies.adapter.DashboardViewPagerAdapter;
import com.example.earthshaker.criticmovies.common.SharedPrefUtils;
import com.example.earthshaker.criticmovies.common.di.master.ComponentFactory;
import com.example.earthshaker.criticmovies.common.service.MovieApiService;
import com.example.earthshaker.criticmovies.eventbus.AppEvents;
import com.example.earthshaker.criticmovies.fragment.DashboardBaseFragment;
import com.example.earthshaker.criticmovies.model.ConfigurationResponse;
import de.greenrobot.event.EventBus;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import javax.inject.Inject;
import javax.inject.Named;

import static android.util.DisplayMetrics.DENSITY_HIGH;
import static android.util.DisplayMetrics.DENSITY_LOW;
import static android.util.DisplayMetrics.DENSITY_MEDIUM;

/**
 * Created by earthshaker on 5/18/17.
 */

public class DashboardViewHolder {

  @Inject @Named("dashboardFragmentManager") FragmentManager fragmentManager;
  @Inject @Named("httpService") MovieApiService movieApiService;
  @Inject @Named("deviceDensity") DisplayMetrics displayMetrics;
  @Inject SharedPrefUtils sharedPrefUtils;

  private CompositeDisposable compositeDisposable = new CompositeDisposable();
  private DashboardViewPagerAdapter dashboardViewPagerAdapter;
  private TabLayout tabLayout;
  private ViewPager viewPager;

  public DashboardViewHolder(View view) {
    tabLayout = (TabLayout) view.findViewById(R.id.tabs);
    viewPager = (ViewPager) view.findViewById(R.id.viewpager);
    setupComponent();
    registerEventBus();
    setViewPager();
    getMoviesFromServer();
  }

  public void onEventMainThread(AppEvents.FetchConfiguration event) {
    sharedPrefUtils.putString("baseUrl", event.getConfigurationResponse().getImages().getBaseUrl());
    int logoLength = event.getConfigurationResponse().getImages().getLogoSizes().size();
    int profileLength = event.getConfigurationResponse().getImages().getProfileSizes().size();
    if (displayMetrics.densityDpi == DENSITY_LOW) {
      sharedPrefUtils.putString("logoSize",
          event.getConfigurationResponse().getImages().getLogoSizes().get(0));
      sharedPrefUtils.putString("profileSize",
          event.getConfigurationResponse().getImages().getProfileSizes().get(0));
    }
    if (displayMetrics.densityDpi == DENSITY_MEDIUM) {
      sharedPrefUtils.putString("logoSize",
          event.getConfigurationResponse().getImages().getLogoSizes().get(1));
      sharedPrefUtils.putString("profileSize",
          event.getConfigurationResponse().getImages().getProfileSizes().get(1));
    }

    if (displayMetrics.densityDpi == DENSITY_HIGH) {
      sharedPrefUtils.putString("logoSize",
          event.getConfigurationResponse().getImages().getLogoSizes().get(2));
      sharedPrefUtils.putString("profileSize",
          event.getConfigurationResponse().getImages().getProfileSizes().get(2));
    } else {
      sharedPrefUtils.putString("logoSize",
          event.getConfigurationResponse().getImages().getLogoSizes().get(logoLength - 1));
      sharedPrefUtils.putString("profileSize",
          event.getConfigurationResponse().getImages().getProfileSizes().get(profileLength - 1));
    }
  }

  public void registerEventBus() {
    EventBus.getDefault().register(this);
  }

  public void unregisterEventBus() {
    EventBus.getDefault().unregister(this);
    compositeDisposable.dispose();
  }

  private void setViewPager() {
    if (dashboardViewPagerAdapter == null) {
      dashboardViewPagerAdapter = new DashboardViewPagerAdapter(fragmentManager);
    }
    dashboardViewPagerAdapter.clearFragments();

    dashboardViewPagerAdapter.addFragment(DashboardBaseFragment.newInstance("List Views"),
        "List Views");

    dashboardViewPagerAdapter.addFragment(DashboardBaseFragment.newInstance("Grid View"),
        "Grid View");
    viewPager.setAdapter(dashboardViewPagerAdapter);

    tabLayout.setupWithViewPager(viewPager);
    /*TabLayout.Tab tab = tabLayout.getTabAt(0);
    if (tab != null) tab.select();*/

  }

  private void setupComponent() {
    ComponentFactory.getInstance().getDashboardComponent().inject(this);
  }

  private void getMoviesFromServer() {
    compositeDisposable.add(movieApiService.getConfiguration("ed75ba81d3a8253393684108406b8e26")
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribeWith(new DisposableObserver<ConfigurationResponse>() {

          @Override public void onComplete() {
          }

          @Override public void onError(Throwable e) {
          }

          @Override public void onNext(ConfigurationResponse configuration) {
            EventBus.getDefault().post(new AppEvents.FetchConfiguration(configuration));
          }
        }));
  }
}
