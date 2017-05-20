package com.example.earthshaker.criticmovies.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.example.earthshaker.criticmovies.R;
import com.example.earthshaker.criticmovies.common.SharedPrefUtils;
import com.example.earthshaker.criticmovies.common.di.master.ComponentFactory;
import com.example.earthshaker.criticmovies.model.Movies;
import com.squareup.picasso.Picasso;
import java.util.List;
import javax.inject.Inject;

/**
 * Created by earthshaker on 5/19/17.
 */

public class DashboardFragmentAdapter
    extends RecyclerView.Adapter<DashboardFragmentAdapter.DashboardFragmentAdapterHolder> {

  @Inject SharedPrefUtils sharedPrefUtils;
  @Inject Context context;
  private List<Movies> moviesList;
  private int view;
  private String mTitle;

  public DashboardFragmentAdapter(int view, List<Movies> moviesList, String mTitle) {
    ComponentFactory.getInstance().getDashboardComponent().inject(this);
    this.moviesList = moviesList;
    this.view = view;
    this.mTitle = mTitle;
  }

  @Override
  public DashboardFragmentAdapterHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View v = LayoutInflater.from(parent.getContext()).inflate(view, parent, false);
    return new DashboardFragmentAdapterHolder(v);
  }

  @Override
  public void onBindViewHolder(final DashboardFragmentAdapterHolder holder, final int position) {
    Movies movies = moviesList.get(position);
    holder.setViewData(movies);
  }

  @Override public int getItemCount() {
    return moviesList.size();
  }

  public void setData(List<Movies> movies, String mTitle) {
    this.moviesList = movies;
    this.mTitle = mTitle;
    notifyDataSetChanged();
  }

  class DashboardFragmentAdapterHolder extends RecyclerView.ViewHolder {

    private ImageView movieLogo;
    private TextView movieTitle, movieDesc;

    DashboardFragmentAdapterHolder(View itemView) {
      super(itemView);
      movieLogo = (ImageView) itemView.findViewById(R.id.movie_icon);
      movieTitle = (TextView) itemView.findViewById(R.id.movie_title);
      movieDesc = (TextView) itemView.findViewById(R.id.movie_detail);
    }

    void setViewData(Movies movies) {
      if (movies != null) {

        String type;
        String baseUrl = sharedPrefUtils.getStringDataByKey("baseUrl", null).concat("/");
        if (mTitle.equals("List Views")) {
          type = sharedPrefUtils.getStringDataByKey("logoSize", null);
        } else {
          type = sharedPrefUtils.getStringDataByKey("profileSize", null);
        }
        String imageUrl = baseUrl.concat(type);

        if (movies.getPosterPath() != null) {
          Picasso.with(context)
              .load(imageUrl.concat(movies.getPosterPath()))
              .placeholder(R.mipmap.ic_launcher_round)
              .into(movieLogo);
        }
        movieTitle.setText(movies.getTitle());
        movieDesc.setText(movies.getOverview());
        movieLogo.setOnClickListener(
            l -> Toast.makeText(context, movies.getTitle(), Toast.LENGTH_SHORT).show());
      }
    }
  }
}
