package com.takefive.plugins.jira.wechat.webwork;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.atlassian.jira.web.action.JiraWebActionSupport;
import com.atlassian.sal.api.pluginsettings.PluginSettings;
import com.atlassian.sal.api.pluginsettings.PluginSettingsFactory;
import com.atlassian.sal.api.user.UserManager;
import com.atlassian.sal.api.user.UserProfile;
import com.takefive.plugins.jira.wechat.api.ConnectionException;
import com.takefive.plugins.jira.wechat.api.WeChatActiveConnection;
import com.takefive.plugins.jira.wechat.api.template.Member;
import com.takefive.plugins.jira.wechat.util.SettingsConstants;

@SuppressWarnings("serial")
public class ConfigureWeChatId extends JiraWebActionSupport {
  
  private static final String SUCCESS = "success";
  private static final String ERROR = "error";
  
  private final PluginSettingsFactory pluginSettingsFactory;
  private final PluginSettings pluginSettings;
  private final UserManager userManager;
  
  public ConfigureWeChatId(PluginSettingsFactory pluginSettingsFactory, UserManager userManager) {
    this.pluginSettingsFactory = pluginSettingsFactory;
    this.pluginSettings = this.pluginSettingsFactory.createGlobalSettings();
    this.userManager = userManager;
  }
  
  public String doDefault() {
    return SUCCESS;
  }
  
  @SuppressWarnings("unchecked")
  public String doExecute() {
    String weChatId = getHttpRequest().getParameter("weChatId");
    String username = userManager.getRemoteUsername();
    if (username == null) {
      addErrorMessage("User profile error. Please try again later.");
      return ERROR;
    }
    
    Member member = new Member();
    member.setWeChatId(weChatId);
    WeChatActiveConnection activeConnection = new WeChatActiveConnection(pluginSettingsFactory);
    
    // Get storage
    Map<String, String> weChatIdMap;
    Map<String, String> weChatUserIdMap;
    if (pluginSettings.get("jira-wechat.user.wechatid") == null) {
      weChatIdMap = new HashMap<String, String>();
    }
    else {
      weChatIdMap = (Map<String, String>) pluginSettings.get(SettingsConstants.WECHAT_ID);
    }
    if (pluginSettings.get("jira-wechat.user.userid") == null) {
      weChatUserIdMap = new HashMap<String, String>();
    }
    else {
      weChatUserIdMap = (Map<String, String>) pluginSettings.get(SettingsConstants.USERID);
    }
    
    String userId;
    if (!weChatUserIdMap.containsKey(username)) {
      userId = "jira-" + UUID.randomUUID().toString();
      member.setUserId(userId);
      try {
        activeConnection.addMember(member);
      } catch (ConnectionException e) {
        e.printStackTrace();
      }
    }
    else {
      userId = weChatUserIdMap.get(username);
      member.setUserId(userId);
      try {
        activeConnection.updateMember(member);
      } catch (ConnectionException e) {
        e.printStackTrace();
      }
    }
    
    // Store info
    weChatIdMap.put(username, weChatId);
    weChatUserIdMap.put(username, userId);
    pluginSettings.put("jira-wechat.user.wechatid", weChatIdMap);
    pluginSettings.put("jira-wechat.user.userid", weChatUserIdMap);
    
    return SUCCESS;
  }
  
  @SuppressWarnings("unchecked")
  public String getWeChatId() {
    Map<String, String> weChatIdMap = (Map<String, String>) pluginSettings.get(SettingsConstants.WECHAT_ID);
    String retval = weChatIdMap.get(userManager.getRemoteUsername());
    return retval == null ? "" : retval;
  }
  
  @SuppressWarnings("unchecked")
  public String getWeChatUserId() {
    Map<String, String> weChatUserIdMap = (Map<String, String>) pluginSettings.get(SettingsConstants.USERID);
    String retval = weChatUserIdMap.get(userManager.getRemoteUsername());
    return retval == null ? "" : retval;
  }
}
