package com.takefive.plugins.jira.wechat.api.template;

import java.util.ArrayList;

import org.apache.commons.lang.StringUtils;
import org.json.JSONException;
import org.json.JSONObject;

public abstract class Message implements JsonSerializable {
  
  public static final String DELIMITER = "|";
  
  protected ArrayList<String> toUser;
  protected ArrayList<String> toParty;
  protected ArrayList<String> toTag;

  protected String msgType;
  protected int agentId;
  protected int safe;
  
  public Message() {
    toUser = new ArrayList<String>();
    toParty = new ArrayList<String>();
    toTag = new ArrayList<String>();
  }
  
  public void addRecipient(String userId) {
    if (!toUser.contains(userId))
      toUser.add(userId);
  }
  
  public void deleteRecipient(String userId) {
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
  
  public String getMsgType() {
    return msgType;
  }

  public int getAgentId() {
    return agentId;
  }

  public int getSafe() {
    return safe;
  }
  
  public void setMsgType(String type) {
    msgType = type;
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
  
  public JSONObject toJsonObject() {
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
  
  public String toJson() {
    return toJsonObject().toString();
  }
}