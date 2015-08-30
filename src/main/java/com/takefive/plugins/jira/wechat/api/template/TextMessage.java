package com.takefive.plugins.jira.wechat.api.template;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

public class TextMessage extends Message {
  
  private String content;
  
  public TextMessage() {
    super();
    msgType = "text";
  }
  
  @Override
  public JSONObject toJsonObject() {
    JSONObject retval = super.toJsonObject();
    Map<String, String> textMap = new HashMap<String, String>();
    textMap.put("content", content);
    try {
      retval.put("text", textMap);
    } catch (JSONException e) {
      // Should not happen
      e.printStackTrace();
    }
    return retval;
  }
  
  @Override
  public String toJson() {
    return toJsonObject().toString();
  }
}
