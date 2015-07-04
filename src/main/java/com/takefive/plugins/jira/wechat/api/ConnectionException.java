package com.takefive.plugins.jira.wechat.api;

public class ConnectionException extends Exception {

  /**
   * Serial version ID
   */
  private static final long serialVersionUID = 1L;
  
  public ConnectionException(String msg) {
    super(msg);
  }
}
