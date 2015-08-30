package com.takefive.plugins.jira.wechat.api.template;

import com.google.gson.Gson;

public class Member {
  private String userid;
  private String name;
  private int[] department;
  private String position;
  private String mobile;
  private int gender;
  private int enable;
  private int status;
  private String email;
  private String weixinid;
  private String avatar_mediaid;
  
  public Member() {}
  
  public String getUserId() {
    return userid;
  }
  
  public void setUserId(String userId) {
    this.userid = userId;
  }
  
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
  
  public int[] getDepartment() {
    return department;
  }
  
  public void setDepartment(int[] department) {
    this.department = department;
  }
  
  public String getPosition() {
    return position;
  }
  
  public void setPosition(String position) {
    this.position = position;
  }
  
  public String getMobile() {
    return mobile;
  }
  
  public void setMobile(String mobile) {
    this.mobile = mobile;
  }
  
  public int getGender() {
    return gender;
  }
  
  public void setGender(int gender) {
    this.gender = gender;
  }
  
  public int getEnable() {
    return enable;
  }
  
  public void setEnable(int enable) {
    this.enable = enable;
  }
  
  public int getStatus() {
    return status;
  }
  
  public String getEmail() {
    return email;
  }
  
  public void setEmail(String email) {
    this.email = email;
  }
  
  public String getWeChatId() {
    return weixinid;
  }
  
  public void setWeChatId(String weChatId) {
    this.weixinid = weChatId;
  }
  
  public String getAvatarMediaId() {
    return avatar_mediaid;
  }
  
  public void setAvatarMediaId(String mediaId) {
    this.avatar_mediaid = mediaId;
  }
  
  
  public String toJson() {
    Gson gson = new Gson();
    return gson.toJson(this);
  }
}
