package com.takefive.plugins.jira.wechat.configuration;

import java.util.HashMap;
import java.util.Map;

import com.atlassian.sal.api.pluginsettings.PluginSettings;
import com.atlassian.sal.api.pluginsettings.PluginSettingsFactory;

public class ConfigurationAccess {
  
  protected PluginSettingsFactory pluginSettingsFactory;
  protected PluginSettings pluginSettings;
  
  public ConfigurationAccess(PluginSettingsFactory pluginSettingsFactory) {
    this.pluginSettingsFactory = pluginSettingsFactory;
    this.pluginSettings = this.pluginSettingsFactory.createGlobalSettings();
  }
  
  @SuppressWarnings("unchecked")
  protected Map<String, String> getMap(String type) {
    Map<String, String> retval;
    if (pluginSettings.get(type) == null) {
      retval = new HashMap<String, String>();
      pluginSettings.put(type, retval);
    }
    else {
      retval = (Map<String, String>) pluginSettings.get(type);
    }
    
    return retval;
  }
  
  protected void setMap(String type, Map<String, String> map) {
    pluginSettings.put(type, map);
  }
}
