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
import com.example.earthshaker.criticmovies.model.MoviesConfig;
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
  private List<MoviesConfig> moviesConfigs;
  private int view;
  private String mTitle;

  public DashboardFragmentAdapter(int view, List<MoviesConfig> moviesConfigList, String mTitle) {
    moviesConfigs = moviesConfigList;
    this.view = view;
    this.mTitle = mTitle;
  }

  @Override
  public DashboardFragmentAdapterHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View v = LayoutInflater.from(parent.getContext()).inflate(view, parent, false);
    return new DashboardFragmentAdapterHolder(v);
  }

  @Override public void onBindViewHolder(DashboardFragmentAdapterHolder holder, int position) {
    final MoviesConfig moviesConfig = moviesConfigs.get(position);
    holder.setViewData(moviesConfig);
  }

  @Override public int getItemCount() {
    return 0;
  }

  public void setData(List<MoviesConfig> moviesConfigs, String mTitle) {
    this.moviesConfigs = moviesConfigs;
    this.mTitle = mTitle;
    notifyDataSetChanged();
  }

  class DashboardFragmentAdapterHolder extends RecyclerView.ViewHolder {

    private ImageView movieLogo;
    private TextView movieTitle, movieDesc;

    public DashboardFragmentAdapterHolder(View itemView) {
      super(itemView);
      movieLogo = (ImageView) itemView.findViewById(R.id.movie_icon);
      movieTitle = (TextView) itemView.findViewById(R.id.movie_title);
      movieDesc = (TextView) itemView.findViewById(R.id.movie_detail);
    }

    public void setViewData(MoviesConfig moviesConfig) {
      String type;
      String baseUrl = sharedPrefUtils.getStringDataByKey("baseUrl", null).concat("/");
      if (mTitle.equals("List Views")) {
        type = sharedPrefUtils.getStringDataByKey("logoSize", null);
      } else {
        type = sharedPrefUtils.getStringDataByKey("profileSize", null);
      }
      String imageUrl = baseUrl.concat(type);

      Picasso.with(context).load(imageUrl.concat(moviesConfig.getPosterPath())).into(movieLogo);
      movieTitle.setText(moviesConfig.getTitle());
      movieDesc.setText(moviesConfig.getOverview());
      movieLogo.setOnClickListener(
          l -> Toast.makeText(context, moviesConfig.getTitle(), Toast.LENGTH_SHORT).show());
    }
  }
}
