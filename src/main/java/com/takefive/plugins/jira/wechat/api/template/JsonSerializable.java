package com.takefive.plugins.jira.wechat.api.template;

import org.json.JSONObject;

public interface JsonSerializable {
  
  public JSONObject toJsonObject();
  
  public String toJson();
}
