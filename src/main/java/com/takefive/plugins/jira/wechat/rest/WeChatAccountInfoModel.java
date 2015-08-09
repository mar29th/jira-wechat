package com.takefive.plugins.jira.wechat.rest;

import javax.xml.bind.annotation.*;
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class WeChatAccountInfoModel {
  
  @XmlElement
  private String username;

  @XmlElement
  private String userId;
  
  @XmlElement
  private String weChatId;

  public WeChatAccountInfoModel() {
    
  }

  public WeChatAccountInfoModel(String username, String userId, String weChatId) {
    this.username = username;
    this.userId = userId;
    this.weChatId = weChatId;
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

  public void setuserId(String userId) {
    this.userId = userId;
  }

  public String getWeChatId() {
    return weChatId;
  }

  public void setWeChatId(String weChatId) {
    this.weChatId = weChatId;
  }
}