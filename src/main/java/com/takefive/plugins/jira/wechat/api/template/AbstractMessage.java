package com.takefive.plugins.jira.wechat.api.template;

import java.util.ArrayList;

import org.apache.commons.lang.StringUtils;

import com.atlassian.jira.util.json.JSONException;
import com.atlassian.jira.util.json.JSONObject;

public abstract class AbstractMessage {
  
  public static final String DELIMITER = "|";
  
  private ArrayList<String> toUser;
  private ArrayList<String> toParty;
  private ArrayList<String> toTag;
  private String msgType;
  private int agentId;
  private int safe;
  
  public AbstractMessage() {
    toUser = new ArrayList<String>();
    toParty = new ArrayList<String>();
    toTag = new ArrayList<String>();
  }
  
  public void addUser(String userId) {
    if (!toUser.contains(userId))
      toUser.add(userId);
  }
  
  public void deleteUser(String userId) {
    toUser.remove(userId);
  }
  
  public void addParty(String partyId) {
    if (!toParty.contains(partyId))
      toParty.add(partyId);
  }
  
  public void deleteParty(String partyId) {
    toParty.remove(partyId);
  }
  
  public void addTag(String tagId) {
    if (!toTag.contains(tagId))
      toTag.add(tagId);
  }
  
  public void deleteTag(String tagId) {
    toTag.remove(tagId);
  }
  
  public void setAgentId(int id) {
    agentId = id;
  }
  
  public void setSafe(boolean isSafe) {
    if (isSafe)
      safe = 1;
    else
      safe = 0;
  }
  
  public JSONObject toJson() {
    String toUserStr = StringUtils.join(toUser, DELIMITER);
    String toPartyStr = StringUtils.join(toParty, DELIMITER);
    String toTagStr = StringUtils.join(toTag, DELIMITER);
    try {
      JSONObject retval = new JSONObject().put("touser", toUserStr)
                                          .put("toparty", toPartyStr)
                                          .put("totag", toTagStr)
                                          .put("msgtype", msgType)
                                          .put("agentid", agentId)
                                          .put("safe", safe);
      return retval;
    } catch (JSONException e) {
      // Should not happen
      e.printStackTrace();
      return null;
    }
  }
}