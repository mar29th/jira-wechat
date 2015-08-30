package com.takefive.plugins.jira.wechat.webwork;

import com.atlassian.jira.web.action.JiraWebActionSupport;
import com.atlassian.sal.api.pluginsettings.PluginSettings;
import com.atlassian.sal.api.pluginsettings.PluginSettingsFactory;
import com.takefive.plugins.jira.wechat.configuration.ConfigurationConstants;

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
    String agentId = getHttpRequest().getParameter("agentId");
    pluginSettings.put(ConfigurationConstants.CORP_ID, corpId);
    pluginSettings.put(ConfigurationConstants.CORP_SECRET, corpSecret);
    pluginSettings.put(ConfigurationConstants.AGENT_ID, agentId);
    
    return SUCCESS;
  }
  
  public String getCorpId() {
    return (String) pluginSettings.get(ConfigurationConstants.CORP_ID);
  }
  
  public String getCropSecret() {
    return (String) pluginSettings.get(ConfigurationConstants.CORP_SECRET);
  }
  
  public String getAgentId() {
    return (String) pluginSettings.get(ConfigurationConstants.AGENT_ID);
  }
}
