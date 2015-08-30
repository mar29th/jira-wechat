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
  
  public boolean hasKey(String key) {
    return pluginSettings.get(key) != null;
  }
  
  public String getString(String key) {
    return (String) pluginSettings.get(key);
  }
  
  @SuppressWarnings("unchecked")
  public Map<String, String> getMap(String key) {
    Map<String, String> retval;
    if (!hasKey(key)) {
      retval = new HashMap<String, String>();
      pluginSettings.put(key, retval);
    }
    else {
      retval = (Map<String, String>) pluginSettings.get(key);
    }
    
    return retval;
  }
  
  public void setMap(String type, Map<String, String> map) {
    pluginSettings.put(type, map);
  }
}
