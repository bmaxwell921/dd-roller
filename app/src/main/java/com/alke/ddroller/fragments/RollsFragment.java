package com.alke.ddroller.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alke.ddroller.R;

/**
 * Fragment showing the user their history of rolls.
 */
public class RollsFragment extends Fragment {

  public static RollsFragment newInstance() {
    return new RollsFragment();
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup viewGroup, Bundle bundle) {
    return inflater.inflate(R.layout.fragment_rolls, viewGroup, false);
  }
}
