package com.takefive.plugins.jira.wechat.api.template;

import com.google.gson.Gson;

public class Department {
  private String name;
  private int parentid;
  private int order;
  private int id;
  
  public Department() {}
  
  public Department(String name, int id, int parentId, int order) {
    this.name = name;
    this.id = id;
    this.parentid = parentId;
    this.order = order;
  }
  
  public String getName() {
    return name;
  }
  
  public void setName(String name) {
    this.name = name;
  }
  
  public int getParentId() {
    return parentid;
  }
  
  public void setParentId(int parentId) {
    this.parentid = parentId;
  }
  
  public int getOrder() {
    return order;
  }
  
  public void setOrder(int order) {
    this.order = order;
  }
  
  public int getId() {
    return id;
  }
  
  public void setId(int id) {
    this.id = id;
  }
  
  public String toJson() {
    return new Gson().toJson(this);
  }
}
