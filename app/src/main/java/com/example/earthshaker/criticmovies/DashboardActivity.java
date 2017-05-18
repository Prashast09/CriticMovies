package com.example.earthshaker.criticmovies;

import android.os.Bundle;
import android.view.View;
import com.example.earthshaker.criticmovies.common.activity.BaseActivity;
import com.example.earthshaker.criticmovies.common.di.master.ComponentFactory;
import de.greenrobot.event.EventBus;

public class DashboardActivity extends BaseActivity {


  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setupUI(R.layout.activity_dashboard,R.id.coordinator_layout);
    EventBus.getDefault().post(this);
  }

  @Override protected void setupViewHolder(View view) {
    DashboardViewHolder dashboardViewHolder = new DashboardViewHolder(view);
  }

  @Override public void setupComponent() {
    super.setupComponent();
    ComponentFactory.getInstance().getDashboardComponent(this).inject(this);
  }
}
