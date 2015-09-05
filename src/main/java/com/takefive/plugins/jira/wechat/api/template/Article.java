package com.takefive.plugins.jira.wechat.api.template;

public class Article {
  
  private String title;
  private String description;
  private String url;
  private String picurl;
  
  public Article() {
    
  }
  
  public Article(String title, String description) {
    this.title = title;
    this.description = description;
    this.url = "";
    this.picurl = "";
  }
  
  public Article(String title, String description, String url) {
    this.title = title;
    this.description = description;
    this.url = url;
    this.picurl = "";
  }
  
  public Article(String title, String description, String url, String picUrl) {
    this.title = title;
    this.description = description;
    this.url = url;
    this.picurl = picUrl;
  }
  
  public String getTitle() {
    return title;
  }
  
  public void setTitle(String title) {
    this.title = title;
  }
  
  public String getDescription() {
    return description;
  }
  
  public void setDescription(String description) {
    this.description = description;
  }
  
  public String getUrl() {
    return url;
  }
  
  public void setUrl(String url) {
    this.url = url;
  }
  
  public String getPicUrl() {
    return picurl;
  }
  
  public void setPicIrl(String picUrl) {
    this.picurl = picUrl;
  }
}
