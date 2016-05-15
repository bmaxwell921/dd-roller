package com.alke.ddroller.fragments;

import com.alke.ddroller.BuildConfig;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.nullValue;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 23)
public class DrawerFragmentFactoryTest {

  private final DrawerFragmentFactory drawerFragmentFactory = new DrawerFragmentFactory();

  @Test (expected = IllegalArgumentException.class)
  public void newDrawerFragment_Negative_IllegalArgumentException() {
    drawerFragmentFactory.newDrawerFragment(-1);
  }

  @Test (expected = IllegalArgumentException.class)
  public void newDrawerFragment_TooLarge_IllegalArgumentException() {
    drawerFragmentFactory.newDrawerFragment(10);
  }

  @Test
  public void newDrawerFragment_Valid() {
    assertThat(drawerFragmentFactory.newDrawerFragment(1), is(notNullValue()));
  }
}
