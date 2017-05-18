package com.example.earthshaker.criticmovies.common.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import com.example.earthshaker.criticmovies.common.di.master.ComponentFactory;

/**
 * Created by earthshaker on 5/18/17.
 */

public abstract class BaseActivity extends AppCompatActivity {

  @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    ComponentFactory.getInstance().getHttpComponent().inject(this);
  }

  @Override public void onBackPressed() {
    super.onBackPressed();
  }

  protected void setupUI(int layoutId, int viewHolderId) {
    setContentView(layoutId);
    setupComponent();
    setupViewHolder(findViewById(viewHolderId));
  }

  abstract protected void setupViewHolder(View view);

  public void setupComponent() {
    ComponentFactory.getInstance().getHttpComponent().inject(this);
  }

}
