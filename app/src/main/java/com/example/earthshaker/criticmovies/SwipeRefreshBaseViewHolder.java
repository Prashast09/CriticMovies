package com.example.earthshaker.criticmovies;

import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

/**
 * Created by earthshaker on 5/18/17.
 */

public abstract class SwipeRefreshBaseViewHolder {
  protected SwipeRefreshLayout swipeContainer;
  protected ProgressBar progressBar;
  protected TextView errorTextview;

  public SwipeRefreshBaseViewHolder(View view) {
    swipeContainer = (SwipeRefreshLayout) view.findViewById(R.id.swipeContainer);
    progressBar = (ProgressBar) view.findViewById(R.id.progress_bar);
    errorTextview = (TextView) view.findViewById(R.id.text_error_string);
    setProgSwipeView();
    setUpSwipeToRefresh(swipeContainer);
  }

  protected void setProgSwipeView() {
    progressBar.setVisibility(View.VISIBLE);
    swipeContainer.setVisibility(View.GONE);
  }

  protected void stopRefreshing() {
    swipeContainer.setVisibility(View.VISIBLE);
    swipeContainer.setRefreshing(false);
    progressBar.setVisibility(View.GONE);
    errorTextview.setVisibility(View.GONE);
  }

  protected void startRefreshing() {
    errorTextview.setVisibility(View.GONE);
    progressBar.setVisibility(View.GONE);
    swipeContainer.setVisibility(View.VISIBLE);
    swipeContainer.setRefreshing(true);
  }

  protected void showError(String error) {
    swipeContainer.setVisibility(View.GONE);
    progressBar.setVisibility(View.GONE);
    errorTextview.setVisibility(View.VISIBLE);
    errorTextview.setText(error);
  }

  abstract protected void onRefresh();

  private void setUpSwipeToRefresh(SwipeRefreshLayout swipeContainer) {
    swipeContainer.setOnRefreshListener(this::onRefresh);
  }
}
