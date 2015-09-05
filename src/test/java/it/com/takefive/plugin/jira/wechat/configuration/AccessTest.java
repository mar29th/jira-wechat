package it.com.takefive.plugin.jira.wechat.configuration;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.UUID;

import org.junit.Test;

import com.atlassian.sal.api.pluginsettings.PluginSettingsFactory;
import com.takefive.plugins.jira.wechat.configuration.ConfigurationAccess;

public class AccessTest {
  
  private final PluginSettingsFactory pluginSettingsFactory;
  private final ConfigurationAccess access;
  
  public AccessTest(PluginSettingsFactory pluginSettingsFactory) {
    this.pluginSettingsFactory = pluginSettingsFactory;
    this.access = new ConfigurationAccess(this.pluginSettingsFactory);
  }
  
  @Test
  public void testGetMap() {
    assertNull(access.getMap(UUID.randomUUID().toString()));
    
    access.setMap("jira-wechat.testing", new HashMap<String, String>());
    assertNotNull(access.getMap("jira-wechat.testing"));
    
    access.setMap("jira-wechat.testing", null);
    assertNull(access.getMap("jira-wechat.testing"));
  }
}
