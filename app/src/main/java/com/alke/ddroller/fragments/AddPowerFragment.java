package com.alke.ddroller.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.alke.ddroller.R;
import com.alke.ddroller.model.Power;

/**
 * Fragment for adding powers.
 */
public class AddPowerFragment extends Fragment {

  public interface PowerSaver {
    void savePower(Power power);
  }

  public static AddPowerFragment newInstance() {
    return new AddPowerFragment();
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup viewGroup, Bundle bundle) {
    View view = inflater.inflate(R.layout.fragment_add_power, viewGroup, false);

    Spinner actionType = (Spinner) view.findViewById(R.id.power_action_type);
    actionType.setAdapter(ArrayAdapter.createFromResource(getActivity(),
        R.array.powers_action_types, android.R.layout.simple_spinner_dropdown_item));

    Spinner yourStat = (Spinner) view.findViewById(R.id.power_your_stat);
    yourStat.setAdapter(ArrayAdapter.createFromResource(getActivity(),
        R.array.powers_your_stats, android.R.layout.simple_spinner_dropdown_item));

    Spinner theirStat = (Spinner) view.findViewById(R.id.power_their_stat);
    theirStat.setAdapter(ArrayAdapter.createFromResource(getActivity(),
        R.array.powers_their_stats, android.R.layout.simple_spinner_dropdown_item));
    return view;
  }

}
