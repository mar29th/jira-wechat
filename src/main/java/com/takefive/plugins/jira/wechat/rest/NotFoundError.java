package com.takefive.plugins.jira.wechat.rest;

public class NotFoundError extends GeneralContent {
  public NotFoundError() {
    super(StatusType.NOT_FOUND, StatusType.NOT_FOUND_COMMENT);
  }
}
