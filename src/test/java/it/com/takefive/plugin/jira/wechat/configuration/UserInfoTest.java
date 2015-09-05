package it.com.takefive.plugin.jira.wechat.configuration;

import static org.junit.Assert.*;

import java.util.UUID;

import org.junit.Test;

import com.atlassian.sal.api.pluginsettings.PluginSettingsFactory;
import com.takefive.plugins.jira.wechat.configuration.UserInfoAccess;
import com.takefive.plugins.jira.wechat.configuration.template.UserInfo;

public class UserInfoTest {
  
  private final PluginSettingsFactory pluginSettingsFactory;
  private final UserInfoAccess access;
  
  public UserInfoTest(PluginSettingsFactory pluginSettingsFactory) {
    this.pluginSettingsFactory = pluginSettingsFactory;
    this.access = new UserInfoAccess(this.pluginSettingsFactory);
  }

  @Test
  public void testNotExist() {
    assertNull(access.getUserInfo(UUID.randomUUID().toString()));
  }
  
  @Test
  public void testAddDeleteUser() {
    String username = UUID.randomUUID().toString();
    UserInfo userInfo = new UserInfo(username);
    access.setUserInfo(username, userInfo);
    
    assertNotNull(access.getUserInfo(username));
  }
}
