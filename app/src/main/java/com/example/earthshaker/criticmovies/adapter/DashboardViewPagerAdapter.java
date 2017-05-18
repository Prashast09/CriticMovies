package com.example.earthshaker.criticmovies.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.SparseArray;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by earthshaker on 5/18/17.
 */

public class DashboardViewPagerAdapter extends FragmentStatePagerAdapter {

  private final List<Fragment> mFragmentList = new ArrayList<>();
  private final List<String> mFragmentTitleList = new ArrayList<>();
  private int position = 0;
  private SparseArray<Fragment> registeredFragments = new SparseArray<>();

  public DashboardViewPagerAdapter(FragmentManager fragmentManager) {
    super(fragmentManager);
  }

  @Override public Fragment getItem(int position) {
    return mFragmentList.get(position);
  }

  @Override public int getCount() {
    return mFragmentList.size();
  }

  @Override public CharSequence getPageTitle(int position) {
    return mFragmentTitleList.get(position);
  }

  public void addFragment(Fragment fragment, String title) {
    mFragmentList.add(fragment);
    mFragmentTitleList.add(title);
    registeredFragments.put(position, fragment);
    position++;
  }

  public void clearFragments() {
    mFragmentList.clear();
    mFragmentTitleList.clear();
    registeredFragments.clear();
    position = 0;
  }

  public Fragment getRegisteredFragment(int position) {
    return registeredFragments.get(position);
  }
}
