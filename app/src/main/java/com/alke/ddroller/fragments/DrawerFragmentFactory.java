package com.alke.ddroller.fragments;

import android.app.Fragment;
import android.support.annotation.Nullable;

/**
 * Factory used to create the proper fragment, based on which drawer option was clicked.
 */
public class DrawerFragmentFactory {

  /**
   * Returns a new {@link Fragment} for the given drawerOption.
   *
   * @param position the index of the {@link com.alke.ddroller.NavigationDrawerFragment} option that
   * was selected.
   * @return a new Fragment, or {@code null} for unimplemented fragments.
   * @throws IllegalArgumentException if drawerOption doesn't correspond to any known fragment.
   */
  @Nullable
  public Fragment newDrawerFragment(int position) {
    if (position == 0) {
      return null;
    }
    if (position == 1) {
      return RollsFragment.newInstance();
    }
    if (position == 2) {
      return null;
    }
    if (position == 3) {
      return null;
    }
    if (position == 4) {
      return null;
    }
    if (position == 5) {
      return AddPowerFragment.newInstance();
    }
    throw new IllegalArgumentException("Unrecognized drawer option");
  }
}
