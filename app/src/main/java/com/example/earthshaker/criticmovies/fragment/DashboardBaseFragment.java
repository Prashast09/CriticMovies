package com.example.earthshaker.criticmovies.fragment;

import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import com.example.earthshaker.criticmovies.R;
import com.example.earthshaker.criticmovies.common.activity.BaseFragment;
import de.greenrobot.event.EventBus;
import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by earthshaker on 5/18/17.
 */

public class DashboardBaseFragment extends BaseFragment {
  private String title;
  private DashboardFragmentHolder dashboardFragmentHolder;

  @Override public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setHasOptionsMenu(true);
    this.title = getArguments().getString("title");

  }

  @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    View view = setupUI(inflater,container,R.layout.fragment_dashboard);
    return view;
  }

  @Override protected void setupViewHolder(View view) {
    dashboardFragmentHolder = new DashboardFragmentHolder(view, title);
  }

  @Override public void onDetach() {
    super.onDetach();
   dashboardFragmentHolder.unRegisterEventBus();
  }

  @Override public void onPrepareOptionsMenu(Menu menu) {
    super.onPrepareOptionsMenu(menu);
  }

  @Override public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
    inflater.inflate(R.menu.menu_dashboard, menu);
    setupOptionMenu(menu);
    super.onCreateOptionsMenu(menu, inflater);
  }

  @Override protected void setupComponent() {
    super.setupComponent();
  }


  public static DashboardBaseFragment newInstance(String title) {
    Bundle args = new Bundle();
    args.putString("title", title);
    DashboardBaseFragment fragment = new DashboardBaseFragment();
    fragment.setArguments(args);
    return fragment;
  }

  private void setupOptionMenu(Menu menu) {
    if (mActivity == null) return;
    final MenuItem searchItem = menu.findItem(R.id.action_search_movies);
    final SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
    SearchManager searchManager =
        (SearchManager) mActivity.getSystemService(Context.SEARCH_SERVICE);
    dashboardFragmentHolder.setupSearch(searchItem, searchView, searchManager);
  }
}
