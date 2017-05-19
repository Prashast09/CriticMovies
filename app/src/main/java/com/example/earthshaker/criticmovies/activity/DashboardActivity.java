package com.example.earthshaker.criticmovies.activity;

import android.os.Bundle;
import android.view.View;
import com.example.earthshaker.criticmovies.R;
import com.example.earthshaker.criticmovies.common.activity.BaseActivity;
import com.example.earthshaker.criticmovies.common.di.master.ComponentFactory;
import de.greenrobot.event.EventBus;

public class DashboardActivity extends BaseActivity {

  private DashboardViewHolder dashboardViewHolder;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setupUI(R.layout.activity_dashboard, R.id.coordinator_layout);
  }

  @Override protected void setupViewHolder(View view) {
    dashboardViewHolder = new DashboardViewHolder(view);
    dashboardViewHolder.registerEventBus();
    setTitle("Movies Dashboard");
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
