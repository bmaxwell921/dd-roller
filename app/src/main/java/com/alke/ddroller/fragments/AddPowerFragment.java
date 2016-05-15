package com.alke.ddroller.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.alke.ddroller.R;
import com.alke.ddroller.model.Power;

/**
 * Fragment for adding powers.
 */
public class AddPowerFragment extends Fragment implements View.OnClickListener {

  public interface PowerSaver {
    /**
     * Validates that the given power is properly configured, and saves it.
     *
     * @param power the power to save.
     */
    void savePower(Power power);
  }

  public static AddPowerFragment newInstance() {
    return new AddPowerFragment();
  }

  private EditText powerName;
  private Spinner actionType;
  private EditText weaponType;
  private EditText target;
  private Spinner yourStat;
  private Spinner theirStat;
  private EditText hit;

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup viewGroup, Bundle bundle) {
    View view = inflater.inflate(R.layout.fragment_add_power, viewGroup, false);

    powerName = (EditText) view.findViewById(R.id.power_name);

    actionType = (Spinner) view.findViewById(R.id.power_action_type);
    actionType.setAdapter(ArrayAdapter.createFromResource(getActivity(),
        R.array.powers_action_types, android.R.layout.simple_spinner_dropdown_item));

    weaponType = (EditText) view.findViewById(R.id.power_weapon_type);

    target = (EditText) view.findViewById(R.id.power_target);

    yourStat = (Spinner) view.findViewById(R.id.power_your_stat);
    yourStat.setAdapter(ArrayAdapter.createFromResource(getActivity(),
        R.array.powers_your_stats, android.R.layout.simple_spinner_dropdown_item));

    theirStat = (Spinner) view.findViewById(R.id.power_their_stat);
    theirStat.setAdapter(ArrayAdapter.createFromResource(getActivity(),
        R.array.powers_their_stats, android.R.layout.simple_spinner_dropdown_item));

    hit = (EditText) view.findViewById(R.id.power_hit);
    view.findViewById(R.id.power_save).setOnClickListener(this);

    return view;
  }

  @Override
  public void onClick(View v) {
    if (v.getId() == R.id.power_save) {
      ((PowerSaver) getActivity()).savePower(buildPower());
    }
  }

  private Power buildPower() {
    return new Power(powerName.getText().toString(), actionType.getSelectedItem().toString(),
        weaponType.getText().toString(), target.getText().toString(),
        yourStat.getSelectedItem().toString(), theirStat.getSelectedItem().toString(),
        hit.getText().toString());
  }
}
