package com.takefive.plugins.jira.wechat.webwork;

import com.atlassian.jira.web.action.JiraWebActionSupport;
import com.atlassian.sal.api.pluginsettings.PluginSettings;
import com.atlassian.sal.api.pluginsettings.PluginSettingsFactory;
import com.atlassian.sal.api.user.UserManager;

public class ConfigureUserRoster extends JiraWebActionSupport {
  
  /**
   * Default serial version ID
   */
  private static final long serialVersionUID = 1L;

  private static final String SUCCESS = "success";
  private static final String ERROR = "error";
  
  private final PluginSettingsFactory pluginSettingsFactory;
  private final UserManager userManager;
  private PluginSettings pluginSettings;
  
  public ConfigureUserRoster(PluginSettingsFactory pluginSettingsFactory, UserManager userManager) {
    this.pluginSettingsFactory = pluginSettingsFactory;
    this.pluginSettings = this.pluginSettingsFactory.createGlobalSettings();
    this.userManager = userManager;
  }
  
  public String doDefault() {
    return SUCCESS;
  }
  
  public String doRefreshRoster() {
    return SUCCESS;
  }
  
}
