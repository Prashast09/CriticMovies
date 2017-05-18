package com.example.earthshaker.criticmovies;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;

/**
 * Created by earthshaker on 5/18/17.
 */

public class DashboardBaseFragment extends Fragment {
  private String title;
  public static DashboardBaseFragment newInstance(String title) {
    Bundle args = new Bundle();
    args.putString("title", title);
    DashboardBaseFragment fragment = new DashboardBaseFragment();
    fragment.setArguments(args);
    return fragment;
  }

  @Override public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    this.title = getArguments().getString("title");

  }
}
