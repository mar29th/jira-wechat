package com.takefive.plugins.jira.wechat.webwork;

import com.atlassian.jira.web.action.JiraWebActionSupport;
import com.atlassian.sal.api.pluginsettings.PluginSettings;
import com.atlassian.sal.api.pluginsettings.PluginSettingsFactory;

@SuppressWarnings("serial")
public class ConfigureWeChatAccess extends JiraWebActionSupport {
  
  public static final String SUCCESS = "success";
  
  private final PluginSettingsFactory pluginSettingsFactory;
  private final PluginSettings pluginSettings;
  
  public ConfigureWeChatAccess(PluginSettingsFactory pluginSettingsFactory) {
    this.pluginSettingsFactory = pluginSettingsFactory;
    this.pluginSettings = this.pluginSettingsFactory.createGlobalSettings();
  }
  
  public String doDefault() {
    return SUCCESS;
  }
  
  public String doExecute() {
    String corpId = getHttpRequest().getParameter("corpId");
    String corpSecret = getHttpRequest().getParameter("corpSecret");
    pluginSettings.put("jira-wechat.corpId", corpId);
    pluginSettings.put("jira-wechat.corpSecret", corpSecret);
    
    return SUCCESS;
  }
  
  public String getCorpId() {
    return (String) pluginSettings.get("jira-wechat.corpId");
  }
  
  public String getCropSecret() {
    return (String) pluginSettings.get("jira-wechat.corpSecret");
  }
}
