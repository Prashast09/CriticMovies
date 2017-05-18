package com.example.earthshaker.criticmovies.common.service;

import io.reactivex.Observable;
import java.util.List;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by earthshaker on 5/18/17.
 */

public interface MovieApiService {

  @GET("/repos/{owner}/{repo}/contributors") Observable<List<Contributor>> contributors(
      @Path("owner") String owner, @Path("repo") String repo);

  @GET("/repos/{owner}/{repo}/contributors")
  List<Contributor> getContributors(@Path("owner") String owner, @Path("repo") String repo);

  @GET("/users/{user}")
  Observable<User> user(@Path("user") String user);

  @GET("/users/{user}")
  User getUser(@Path("user") String user);
}
