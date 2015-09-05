package com.takefive.plugins.jira.wechat.webwork;

import com.atlassian.jira.web.action.JiraWebActionSupport;
import com.atlassian.sal.api.pluginsettings.PluginSettingsFactory;
import com.atlassian.sal.api.user.UserManager;
import com.takefive.plugins.jira.wechat.api.ConnectionException;
import com.takefive.plugins.jira.wechat.api.WeChatActiveConnection;
import com.takefive.plugins.jira.wechat.api.template.Member;
import com.takefive.plugins.jira.wechat.configuration.UserInfoAccess;
import com.takefive.plugins.jira.wechat.configuration.template.UserInfo;

@SuppressWarnings("serial")
public class ConfigureWeChatId extends JiraWebActionSupport {
  
  private static final String SUCCESS = "success";
  private static final String ERROR = "error";
  
  private final PluginSettingsFactory pluginSettingsFactory;
  private final UserManager userManager;
  private final UserInfoAccess userInfoAccess;
  
  public ConfigureWeChatId(PluginSettingsFactory pluginSettingsFactory, UserManager userManager) {
    this.pluginSettingsFactory = pluginSettingsFactory;
    this.userManager = userManager;
    this.userInfoAccess = new UserInfoAccess(this.pluginSettingsFactory);
  }
  
  public String doDefault() {
    return SUCCESS;
  }
  
  public String doExecute() {
    String weChatId = getHttpRequest().getParameter("weChatId");
    String username = userManager.getRemoteUsername();
    if (username == null) {
      addErrorMessage("User profile error. Please try again later.");
      return ERROR;
    }
    
    WeChatActiveConnection activeConnection = new WeChatActiveConnection(pluginSettingsFactory);
    UserInfo userInfo = userInfoAccess.getOrCreateUserInfo(username);
    userInfo.setWeChatId(weChatId);
    
    // Connect to WeChat server
    Member member = new Member();
    member.setUserId(userInfo.getUserId());
    member.setWeChatId(weChatId);
    
    if (userInfoAccess.hasUserInfo(username)) {
      try {
        activeConnection.updateMember(member);
      } catch (ConnectionException e) {
        e.printStackTrace();
        this.addErrorMessage("Error when synchronizing with WeChat.");
        return ERROR;
      }
    }
    else {
      try {
        activeConnection.addMember(member);
      } catch (ConnectionException e) {
        e.printStackTrace();
        this.addErrorMessage("Error when synchronizing with WeChat.");
        return ERROR;
      }
    }
    
    // Set updated
    userInfoAccess.setUserInfo(username, userInfo);
    
    return SUCCESS;
  }
  
  public String getWeChatId() {
    if (!userInfoAccess.hasUserInfo(userManager.getRemoteUsername()))
      return "";
    return userInfoAccess.getUserInfo(userManager.getRemoteUsername()).getWeChatId();
  }
  
  public String getWeChatUserId() {
    if (!userInfoAccess.hasUserInfo(userManager.getRemoteUsername()))
      return "";
    return userInfoAccess.getUserInfo(userManager.getRemoteUsername()).getUserId();
  }
}
