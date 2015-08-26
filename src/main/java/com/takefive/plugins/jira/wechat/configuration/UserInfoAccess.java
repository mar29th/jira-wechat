package com.takefive.plugins.jira.wechat.configuration;

import java.util.HashMap;
import java.util.Map;

import com.atlassian.sal.api.pluginsettings.PluginSettings;
import com.atlassian.sal.api.pluginsettings.PluginSettingsFactory;
import com.google.gson.Gson;
import com.takefive.plugins.jira.wechat.configuration.template.UserInfo;

public class UserInfoAccess extends ConfigurationAccess {
  
  public UserInfoAccess(PluginSettingsFactory pluginSettingsFactory) {
    super(pluginSettingsFactory);
  }
  
  public boolean hasUserInfo(String username) {
    return getMap(ConfigurationConstants.USERINFO).get(username) != null;
  }
  
  public UserInfo getUserInfo(String username) {
    if (hasUserInfo(username)) {
      return null;
    }
    else {
      return new Gson().fromJson(getMap(ConfigurationConstants.USERINFO).get(username),UserInfo.class);
    }
  }
  
  public UserInfo getOrCreateUserInfo(String username) {
    UserInfo retval;
    if (hasUserInfo(username)) {
      retval = new UserInfo(username);
    }
    else {
      return new Gson().fromJson(getMap(ConfigurationConstants.USERINFO).get(username), UserInfo.class);
    }
    return retval;
  }
  
  public void setUserInfo(String username, UserInfo userInfo) {
    Map<String, String> userInfoMap = getMap(ConfigurationConstants.USERINFO);
    if (userInfo == null) {
      userInfoMap.remove(username);
    }
    else {
      userInfoMap.put(username, userInfo.toJson());
    }
    setMap(ConfigurationConstants.USERINFO, userInfoMap);
  }
}
