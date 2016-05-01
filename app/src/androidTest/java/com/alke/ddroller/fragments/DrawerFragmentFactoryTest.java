package com.alke.ddroller.fragments;

import org.junit.Test;

import static org.junit.Assert.assertNotNull;

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

  @Test (expected = IllegalArgumentException.class)
  public void newDrawerFragment_Valid() {
    assertNotNull(drawerFragmentFactory.newDrawerFragment(1));
  }
}
