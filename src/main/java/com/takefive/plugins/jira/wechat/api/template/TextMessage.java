package com.takefive.plugins.jira.wechat.api.template;

import java.util.HashMap;
import java.util.Map;

import com.atlassian.jira.util.json.JSONException;
import com.atlassian.jira.util.json.JSONObject;

public class TextMessage extends AbstractMessage {
  
  private String content;
  
  public JSONObject toJson() {
    JSONObject retval = super.toJson();
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
}