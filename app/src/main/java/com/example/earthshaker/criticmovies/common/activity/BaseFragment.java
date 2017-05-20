package com.example.earthshaker.criticmovies.common.activity;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.earthshaker.criticmovies.activity.DashboardActivity;

/**
 * Created by earthshaker on 5/19/17.
 */

public abstract class BaseFragment extends Fragment {

  public BaseActivity mActivity;

  @Override public void onAttach(Context context) {
    super.onAttach(context);
    mActivity = (DashboardActivity) context;
  }

  protected void setupComponent() {

  }

  protected View setupUI(LayoutInflater inflater, ViewGroup container, int layoutId) {
    View fragmentView = inflater.inflate(layoutId, container, false);
    setupViewHolder(fragmentView);
    return fragmentView;
  }

  abstract protected void setupViewHolder(View view);
}
