package com.example.irctc.domain;

public enum CoachClass {
  FIRST_AC("1A"),
  SECOND_AC("2A"),
  THIRD_AC("3A"),
  SLEEPER("SL"),
  CHAIR_CAR("CC"),
  SECOND_SITTING("2S");

  private final String code;

  CoachClass(String code) {
    this.code = code;
  }

  public String getCode() {
    return code;
  }

  public static CoachClass fromCode(String code) {
    for (CoachClass c : values()) {
      if (c.code.equalsIgnoreCase(code)) {
        return c;
      }
    }
    throw new IllegalArgumentException("Unknown class code: " + code);
  }
}
