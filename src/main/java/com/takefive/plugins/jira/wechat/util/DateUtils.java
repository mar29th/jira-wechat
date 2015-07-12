package com.takefive.plugins.jira.wechat.util;

public class DateUtils {
  public static long getUnixTime() {
    return System.currentTimeMillis() / 1000;
  }
}
