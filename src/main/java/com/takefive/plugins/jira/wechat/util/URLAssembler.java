package com.takefive.plugins.jira.wechat.util;

import com.atlassian.jira.component.ComponentAccessor;
import com.atlassian.jira.config.properties.APKeys;

public class URLAssembler {
  
  private static String baseUrl;
  
  static {
    baseUrl = ComponentAccessor.getApplicationProperties().getString(APKeys.JIRA_BASEURL);
  }
  
  public static String toIssueURL(String issueKey) {
    return baseUrl + "/browse/" + issueKey;
  }
}
