package com.alke.ddroller.common;

import org.junit.Test;

import static org.junit.Assert.assertSame;

public class PreconditionsTest {

  @Test(expected = NullPointerException.class)
  public void checkNotNull_Null_NullPointerException() {
    Preconditions.checkNotNull(null);
  }

  @Test
  public void checkNotNull_NotNull_Success() {
    Object o = new Object();
    assertSame(o, Preconditions.checkNotNull(o));
  }
}
