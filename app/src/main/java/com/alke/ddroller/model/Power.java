package com.alke.ddroller.model;

import android.support.annotation.Nullable;

import java.util.regex.Pattern;

/**
 * Object representing a power.
 * TODO: Refactor this into a protobuf
 */
public class Power {

  // TODO: Check for specific dice size?
  private static final Pattern HIT_PATTERN = Pattern.compile("\\dd\\d");

  @Nullable
  public final String name;
  @Nullable
  public final String actionType;
  @Nullable
  public final String weaponType;
  @Nullable
  public final String target;
  @Nullable
  public final String yourStat;
  @Nullable
  public final String theirStat;
  @Nullable
  public final String hit;

  public Power(@Nullable String name, @Nullable String actionType, @Nullable String weaponType,
      @Nullable String target, @Nullable String yourStat, @Nullable String theirStat,
      @Nullable String hit) {
    this.name = name;
    this.actionType = actionType;
    this.weaponType = weaponType;
    this.target = target;
    this.yourStat = yourStat;
    this.theirStat = theirStat;
    this.hit = hit;
  }

  public boolean isValid() {
    return name != null && hitIsValid();
  }

  private boolean hitIsValid() {
    return hit != null && HIT_PATTERN.matcher(hit).matches();
  }
}
