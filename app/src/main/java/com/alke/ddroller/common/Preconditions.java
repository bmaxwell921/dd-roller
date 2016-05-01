package com.alke.ddroller.common;

/**
 * Copy of Guava's Precondition class to avoid bringing in dependency on Guava.
 */
public class Preconditions {

  /**
   * Ensures that an object reference passed as a parameter to the calling method is not null.
   * @param reference an object reference
   * @return the non-null reference that was validated
   * @throws NullPointerException if reference is null
   */
  public static <T> T checkNotNull(T reference) {
    if (reference == null) {
      throw new NullPointerException();
    }
    return reference;
  }
}
