package com.alke.ddroller;


import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.alke.ddroller.fragments.DrawerFragmentFactory;

/**
 * Fragment used for managing interactions for and presentation of a navigation drawer.
 * See the <a href="https://developer.android.com/design/patterns/navigation-drawer.html#Interaction">
 * design guidelines</a> for a complete explanation of the behaviors implemented here.
 */
public class NavigationDrawerFragment extends Fragment {

  /**
   * Remember the position of the selected item.
   */
  private static final String STATE_SELECTED_POSITION = "selected_navigation_drawer_position";

  /**
   * Per the design guidelines, you should show the drawer on launch until the user manually
   * expands it. This shared preference tracks this.
   */
  private static final String PREF_USER_LEARNED_DRAWER = "navigation_drawer_learned";

  /**
   * A pointer to the current callbacks instance (the Activity).
   */
  private NavigationDrawerCallbacks callbacks;

  /**
   * Helper component that ties the action bar to the navigation drawer.
   */
  private ActionBarDrawerToggle drawerToggle;

  private DrawerLayout drawerLayout;
  private ListView drawerListView;
  private View fragmentContainerView;

  // Default to the rolls view
  private int currentSelectedPosition = 1;
  private boolean fromSavedInstanceState;
  private boolean userLearnedDrawer;

  private DrawerFragmentFactory drawerFragmentFactory;
  private String[] fragmentTitles;

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    drawerFragmentFactory = new DrawerFragmentFactory();
    fragmentTitles = getResources().getStringArray(R.array.navigation_drawer_options);
    // Read in the flag indicating whether or not the user has demonstrated awareness of the
    // drawer. See PREF_USER_LEARNED_DRAWER for details.
    SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(getActivity());
    userLearnedDrawer = sp.getBoolean(PREF_USER_LEARNED_DRAWER, false);

    if (savedInstanceState != null) {
      currentSelectedPosition = savedInstanceState.getInt(STATE_SELECTED_POSITION);
      fromSavedInstanceState = true;
    }

    // Select either the default item (0) or the last selected item.
    selectItem(currentSelectedPosition);
  }

  @Override
  public void onActivityCreated(Bundle savedInstanceState) {
    super.onActivityCreated(savedInstanceState);
    // Indicate that this fragment would like to influence the set of actions in the action bar.
    setHasOptionsMenu(true);
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    drawerListView = (ListView) inflater.inflate(
        R.layout.fragment_navigation_drawer, container, false);
    drawerListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
      @Override
      public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        selectItem(position);
      }
    });
    drawerListView.setAdapter(new ArrayAdapter<>(
        getActionBar().getThemedContext(),
        android.R.layout.simple_list_item_activated_1,
        android.R.id.text1,
        fragmentTitles));
    drawerListView.setItemChecked(currentSelectedPosition, true);
    return drawerListView;
  }

  private void selectItem(int position) {
    Fragment newFragment = drawerFragmentFactory.newDrawerFragment(position);
    // TODO: Remove after all fragments are implemented
    if (newFragment == null) {
      Toast.makeText(getActivity(), R.string.unimplemented, Toast.LENGTH_SHORT).show();
    } else {
      currentSelectedPosition = position;
    }
    getActionBar().setTitle(fragmentTitles[currentSelectedPosition]);
    if (drawerListView != null) {
      drawerListView.setItemChecked(currentSelectedPosition, true);
    }
    if (drawerLayout != null) {
      drawerLayout.closeDrawer(fragmentContainerView);
    }
    if (callbacks != null && newFragment != null) {
      callbacks.navigateToFragment(fragmentTitles[currentSelectedPosition], newFragment);
    }
  }

  public boolean isDrawerOpen() {
    return drawerLayout != null && drawerLayout.isDrawerOpen(fragmentContainerView);
  }

  /**
   * Users of this fragment must call this method to set up the navigation drawer interactions.
   *
   * @param fragmentId The android:id of this fragment in its activity's layout.
   * @param drawerLayout The DrawerLayout containing this fragment's UI.
   */
  public void setUp(int fragmentId, DrawerLayout drawerLayout) {
    fragmentContainerView = getActivity().findViewById(fragmentId);
    this.drawerLayout = drawerLayout;

    // set a custom shadow that overlays the main content when the drawer opens
    this.drawerLayout.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START);
    // set up the drawer's list view with items and click listener

    ActionBar actionBar = getActionBar();
    actionBar.setDisplayHomeAsUpEnabled(true);
    actionBar.setHomeButtonEnabled(true);

    // ActionBarDrawerToggle ties together the the proper interactions
    // between the navigation drawer and the action bar app icon.
    drawerToggle = new ActionBarDrawerToggle(
        getActivity(),                    /* host Activity */
        NavigationDrawerFragment.this.drawerLayout,                    /* DrawerLayout object */
        R.drawable.ic_drawer,             /* nav drawer image to replace 'Up' caret */
        R.string.navigation_drawer_open,  /* "open drawer" description for accessibility */
        R.string.navigation_drawer_close  /* "close drawer" description for accessibility */
    ) {
      @Override
      public void onDrawerClosed(View drawerView) {
        super.onDrawerClosed(drawerView);
        if (!isAdded()) {
          return;
        }

        getActivity().invalidateOptionsMenu(); // calls onPrepareOptionsMenu()
      }

      @Override
      public void onDrawerOpened(View drawerView) {
        super.onDrawerOpened(drawerView);
        if (!isAdded()) {
          return;
        }

        if (!userLearnedDrawer) {
          // The user manually opened the drawer; store this flag to prevent auto-showing
          // the navigation drawer automatically in the future.
          userLearnedDrawer = true;
          SharedPreferences sp = PreferenceManager
              .getDefaultSharedPreferences(getActivity());
          sp.edit().putBoolean(PREF_USER_LEARNED_DRAWER, true).apply();
        }

        getActivity().invalidateOptionsMenu(); // calls onPrepareOptionsMenu()
      }
    };

    // If the user hasn't 'learned' about the drawer, open it to introduce them to the drawer,
    // per the navigation drawer design guidelines.
    if (!userLearnedDrawer && !fromSavedInstanceState) {
      this.drawerLayout.openDrawer(fragmentContainerView);
    }

    // Defer code dependent on restoration of previous instance state.
    this.drawerLayout.post(new Runnable() {
      @Override
      public void run() {
        drawerToggle.syncState();
      }
    });

    this.drawerLayout.setDrawerListener(drawerToggle);
  }

  @Override
  public void onAttach(Activity activity) {
    super.onAttach(activity);
    try {
      callbacks = (NavigationDrawerCallbacks) activity;
    } catch (ClassCastException e) {
      throw new ClassCastException("Activity must implement NavigationDrawerCallbacks.");
    }
  }

  @Override
  public void onDetach() {
    super.onDetach();
    callbacks = null;
  }

  @Override
  public void onSaveInstanceState(Bundle outState) {
    super.onSaveInstanceState(outState);
    outState.putInt(STATE_SELECTED_POSITION, currentSelectedPosition);
  }

  @Override
  public void onConfigurationChanged(Configuration newConfig) {
    super.onConfigurationChanged(newConfig);
    // Forward the new configuration the drawer toggle component.
    drawerToggle.onConfigurationChanged(newConfig);
  }

  @Override
  public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
    // If the drawer is open, show the global app actions in the action bar. See also
    // showGlobalContextActionBar, which controls the top-left area of the action bar.
    if (drawerLayout != null && isDrawerOpen()) {
      inflater.inflate(R.menu.global, menu);
      showGlobalContextActionBar();
    }
    super.onCreateOptionsMenu(menu, inflater);
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    if (drawerToggle.onOptionsItemSelected(item)) {
      return true;
    }

    return super.onOptionsItemSelected(item);
  }

  /**
   * Per the navigation drawer design guidelines, updates the action bar to show the global app
   * 'context', rather than just what's in the current screen.
   */
  private void showGlobalContextActionBar() {
    ActionBar actionBar = getActionBar();
    actionBar.setDisplayShowTitleEnabled(true);
    actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
    actionBar.setTitle(isDrawerOpen() ? getResources().getString(R.string.app_name)
        : fragmentTitles[currentSelectedPosition]);
  }

  private ActionBar getActionBar() {
    return getActivity().getActionBar();
  }

  /**
   * Callbacks interface that all activities using this fragment must implement.
   */
  public interface NavigationDrawerCallbacks {
    /**
     * Called when an item in the navigation drawer is selected.
     *
     * @param fragmentTitle the title to show in the actionBar for the new fragment.
     * @param fragment the fragment to show.
     */
    void navigateToFragment(String fragmentTitle, Fragment fragment);
  }
}
