package com.takefive.plugins.jira.wechat.configuration.template;

import com.google.gson.Gson;

public class UserInfo {
  
  private String username;
  
  private String userId;
  
  private String weChatId = "";
  
  public UserInfo(String username) {
    this.username = username;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getUserId() {
    return userId;
  }

  public void setUserId(String userId) {
    this.userId = userId;
  }

  public String getWeChatId() {
    return weChatId;
  }

  public void setWeChatId(String weChatId) {
    this.weChatId = weChatId;
  }
  
  public String toJson() {
    return new Gson().toJson(this);
  }
}
