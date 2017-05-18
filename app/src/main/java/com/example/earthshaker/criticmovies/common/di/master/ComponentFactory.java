package com.example.earthshaker.criticmovies.common.di.master;

import com.example.earthshaker.criticmovies.MovieTrakrApplication;
import com.example.earthshaker.criticmovies.common.activity.BaseActivity;
import com.example.earthshaker.criticmovies.common.di.subdi.HttpComponent;
import com.example.earthshaker.criticmovies.common.di.subdi.HttpModule;
import com.example.earthshaker.criticmovies.di.DashboardComponent;
import com.example.earthshaker.criticmovies.di.DashboardModule;

/**
 * Created by earthshaker on 5/18/17.
 */

public class ComponentFactory {

  private static ComponentFactory componentFactory;

  private TrakrComponent trakrComponent;

  private HttpComponent httpComponent;

  private DashboardComponent dashboardComponent;

  public static ComponentFactory getInstance() {
    if (componentFactory == null) {
      componentFactory = new ComponentFactory();
    }
    return componentFactory;
  }

  public void initializeComponent(MovieTrakrApplication movieTrakrApplication) {
    trakrComponent =
        DaggerTrakrComponent.builder().appModule(new AppModule(movieTrakrApplication)).build();
  }

  public TrakrComponent getTrakrComponent() {
    return trakrComponent;
  }

  public HttpComponent getHttpComponent() {
    if (httpComponent == null) {
      httpComponent = getTrakrComponent().plus(new HttpModule());
    }
    return httpComponent;
  }

  public DashboardComponent getDashboardComponent(BaseActivity activity) {
    if (dashboardComponent == null) {
      dashboardComponent = getHttpComponent().plus(new DashboardModule(activity));
    }
    return dashboardComponent;
  }

  public DashboardComponent getDashboardComponent() {
    return dashboardComponent;
  }
}
