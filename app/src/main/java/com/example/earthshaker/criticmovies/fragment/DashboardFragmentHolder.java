package com.example.earthshaker.criticmovies.fragment;

import android.app.SearchManager;
import android.content.ComponentName;
import android.content.Context;
import android.graphics.Color;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import com.example.earthshaker.criticmovies.R;
import com.example.earthshaker.criticmovies.adapter.DashboardFragmentAdapter;
import com.example.earthshaker.criticmovies.common.di.master.ComponentFactory;
import com.example.earthshaker.criticmovies.common.service.MovieApiService;
import com.example.earthshaker.criticmovies.eventbus.AppEvents;
import com.example.earthshaker.criticmovies.model.Movies;
import com.example.earthshaker.criticmovies.model.MoviesConfig;
import de.greenrobot.event.EventBus;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Named;

/**
 * Created by earthshaker on 5/19/17.
 */

public class DashboardFragmentHolder {

  @Inject Context context;
  @Inject @Named("componentName") ComponentName componentName;
  @Inject @Named("httpService") MovieApiService movieApiService;
  private String mTitle;
  private CompositeDisposable compositeDisposable = new CompositeDisposable();
  private DashboardFragmentAdapter dashboardFragmentAdapter;
  private RecyclerView movieRecyclerView;
  private SwipeRefreshLayout swipeContainer;
  private ProgressBar progressBar;
  private int adapterView;
  private List<Movies> moviesList;
  private int pastVisiblesItems, visibleItemCount, totalItemCount, mPage = 1;

  public DashboardFragmentHolder(View view, String title) {
    movieRecyclerView = (RecyclerView) view.findViewById(R.id.movieListView);
    swipeContainer = (SwipeRefreshLayout) view.findViewById(R.id.swipeContainer);
    progressBar = (ProgressBar) view.findViewById(R.id.progress_bar);
    mTitle = title;
    initializeData();
    moviesList = new ArrayList<>();
    registerEventBus();
    dashboardFragmentAdapter = new DashboardFragmentAdapter(adapterView, moviesList, mTitle);
    setupSwipeToRefresh();
    setView();
    getDefaultMoviesFromServer();
    setupRecyclerView();
  }

  public void registerEventBus() {
    EventBus.getDefault().register(this);
  }

  public void unRegisterEventBus() {
    EventBus.getDefault().unregister(this);
    compositeDisposable.dispose();
  }

  public void onEventMainThread(AppEvents.FetchMovies event) {
    stopRefreshingData();
    if (mPage == 1) moviesList.clear();
    moviesList.addAll(event.getMoviesConfig().getMoviesList());
    setDataToRecyclerView(moviesList);
  }

  void setupSearch(MenuItem searchItem, SearchView searchView, SearchManager searchManager) {
    setupComponent();
    searchView.setSearchableInfo(searchManager.getSearchableInfo(componentName));
    searchView.setQueryHint("Search Movies");
    ImageView closeButton = (ImageView) searchView.findViewById(R.id.search_close_btn);

    closeButton.setOnClickListener(v -> {
      searchView.clearFocus();
      searchView.setQuery("", false);
      searchView.setIconified(true);
      searchItem.collapseActionView();
      setDataToRecyclerView(moviesList);
    });

    searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
      @Override public boolean onQueryTextSubmit(String query) {
        return false;
      }

      @Override public boolean onQueryTextChange(String query) {
        if (!query.isEmpty() && query.length() > 3) {
          searchMovies(query);
        }
        return false;
      }
    });
  }

  private void setDataToRecyclerView(List<Movies> moviesList) {
    if (moviesList != null && moviesList.size() > 0) {
      swipeContainer.setVisibility(View.VISIBLE);
      dashboardFragmentAdapter.setData(moviesList, mTitle);
    } else {
      swipeContainer.setVisibility(View.GONE);
      progressBar.setVisibility(View.GONE);
    }
  }

  private void setupRecyclerView() {

    if (mTitle.equals("List Views")) {
      LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
      linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
      movieRecyclerView.setLayoutManager(linearLayoutManager);
    } else {
      GridLayoutManager gridLayoutManager = new GridLayoutManager(context, 2);
      movieRecyclerView.setLayoutManager(gridLayoutManager);
    }

    movieRecyclerView.setAdapter(dashboardFragmentAdapter);

    movieRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
      @Override public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        if (dy > 0) //check for scroll down
        {
          visibleItemCount = recyclerView.getLayoutManager().getChildCount();
          totalItemCount = recyclerView.getLayoutManager().getItemCount();
          pastVisiblesItems =
              ((LinearLayoutManager) recyclerView.getLayoutManager()).findFirstVisibleItemPosition();
          if ((visibleItemCount + pastVisiblesItems) >= totalItemCount) {
            getMoviesPageWiseFromServer(mPage + 1);
          }
        }
      }
    });
  }

  private void setView() {
    progressBar.setVisibility(View.GONE);
    swipeContainer.setVisibility(View.VISIBLE);
    swipeContainer.setColorSchemeColors(Color.RED, Color.BLUE);
  }

  private void initializeData() {
    adapterView = mTitle.equalsIgnoreCase("List Views") ? R.layout.item_horizontal_card
        : R.layout.item_vertical_card;
  }

  private void showRefreshingData() {
    swipeContainer.setRefreshing(true);
  }

  private void stopRefreshingData() {
    swipeContainer.setRefreshing(false);
  }

  private void setupSwipeToRefresh() {
    swipeContainer.setOnRefreshListener(this::getDefaultMoviesFromServer);
  }

  private void getDefaultMoviesFromServer() {
    setupComponent();
    showRefreshingData();
    getMoviesPageWiseFromServer(1);
  }

  private void setupComponent() {
    ComponentFactory.getInstance().getDashboardComponent().inject(this);
  }

  private void getMoviesPageWiseFromServer(int page) {
    compositeDisposable.add(movieApiService.getMovies("ed75ba81d3a8253393684108406b8e26", page)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribeWith(new DisposableObserver<MoviesConfig>() {

          @Override public void onComplete() {
          }

          @Override public void onError(Throwable e) {
          }

          @Override public void onNext(MoviesConfig moviesConfigList) {
            EventBus.getDefault().post(new AppEvents.FetchMovies(moviesConfigList));
            mPage = moviesConfigList.getPage();
          }
        }));
  }

  private void searchMovies(String query) {
    compositeDisposable.add(movieApiService.searchMovies("ed75ba81d3a8253393684108406b8e26", query)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribeWith(new DisposableObserver<MoviesConfig>() {

          @Override public void onComplete() {
          }

          @Override public void onError(Throwable e) {
          }

          @Override public void onNext(MoviesConfig moviesConfigList) {
            EventBus.getDefault().post(new AppEvents.FetchMovies(moviesConfigList));
            mPage = moviesConfigList.getPage();
          }
        }));
  }
}
