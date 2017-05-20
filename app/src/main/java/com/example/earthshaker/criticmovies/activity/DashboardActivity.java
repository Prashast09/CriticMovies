package com.example.earthshaker.criticmovies.activity;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import com.example.earthshaker.criticmovies.R;
import com.example.earthshaker.criticmovies.common.activity.BaseActivity;
import com.example.earthshaker.criticmovies.common.di.master.ComponentFactory;

public class DashboardActivity extends BaseActivity {

  private DashboardViewHolder dashboardViewHolder;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setupUI(R.layout.activity_dashboard, R.id.coordinator_layout);
    Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);
  }

  @Override protected void setupViewHolder(View view) {
    dashboardViewHolder = new DashboardViewHolder(view);
  }

  @Override public boolean onCreateOptionsMenu(Menu menu) {
    return super.onCreateOptionsMenu(menu);
  }


  @Override public void setupComponent() {
    super.setupComponent();
    ComponentFactory.getInstance().getDashboardComponent(this).inject(this);
  }

  @Override protected void onDestroy() {
    super.onDestroy();
    dashboardViewHolder.unregisterEventBus();
  }
}
