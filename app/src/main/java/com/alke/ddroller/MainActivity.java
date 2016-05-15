package com.alke.ddroller;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.view.MenuItem;
import android.widget.Toast;

import com.alke.ddroller.fragments.AddPowerFragment;
import com.alke.ddroller.model.Power;

/**
 * The MainActivity for the app. This Activity is used to simply display the proper fragment,
 * based on what option the user selects in the navigation drawer.
 */
public class MainActivity extends Activity
    implements NavigationDrawerFragment.NavigationDrawerCallbacks, AddPowerFragment.PowerSaver {

  /**
   * Fragment managing the behaviors, interactions and presentation of the navigation drawer.
   */
  private NavigationDrawerFragment navigationDrawerFragment;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    navigationDrawerFragment = (NavigationDrawerFragment)
        getFragmentManager().findFragmentById(R.id.navigation_drawer);

    // Set up the drawer.
    navigationDrawerFragment.setUp(
        R.id.navigation_drawer,
        (DrawerLayout) findViewById(R.id.drawer_layout));
  }

  @Override
  public void navigateToFragment(String fragmentTitle, Fragment fragment) {
    // update the main content by replacing fragments
    FragmentManager fragmentManager = getFragmentManager();
    fragmentManager.beginTransaction()
        .replace(R.id.container, fragment)
        .commit();
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    // Handle action bar item clicks here. The action bar will
    // automatically handle clicks on the Home/Up button, so long
    // as you specify a parent activity in AndroidManifest.xml.
    int id = item.getItemId();

    //noinspection SimplifiableIfStatement
    if (id == R.id.action_settings) {
      Toast.makeText(this, "Got it", Toast.LENGTH_SHORT).show();
      return true;
    }

    return super.onOptionsItemSelected(item);
  }

  @Override
  public void savePower(Power power) {
    if (!power.isValid()) {
      Toast.makeText(this, "That's not valid, idiot!", Toast.LENGTH_SHORT).show();
      return;
    }
    // TODO save to db, kill the fragment
  }
}
