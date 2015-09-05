package it.com.takefive.plugin.jira.wechat.api;

import static org.junit.Assert.*;

import org.junit.Test;

import com.atlassian.sal.api.pluginsettings.PluginSettingsFactory;
import com.takefive.plugins.jira.wechat.api.ConnectionException;
import com.takefive.plugins.jira.wechat.api.WeChatActiveConnection;

public class WeChatActiveConnectionTest {
  
  private final PluginSettingsFactory pluginSettingsFactory;
  private final WeChatActiveConnection connection;
  
  public WeChatActiveConnectionTest(PluginSettingsFactory pluginSettingsFactory) {
    this.pluginSettingsFactory = pluginSettingsFactory;
    this.connection = new WeChatActiveConnection(this.pluginSettingsFactory);
  }
  
  @Test
  public void testGetToken() {
    try {
      connection.getAccessToken();
    } catch (ConnectionException e) {
      e.printStackTrace();
      fail(e.getMessage());
    }
  }
  
  /*
  @Test
  public void testMemberAddDelete() {
    String userId = UUID.randomUUID().toString();
    Member member = new Member();
    member.setUserId(userId);
    member.setWeChatId("lafickens");
    try {
      connection.addMember(member);
      connection.deleteMember(member);
    } catch (ConnectionException e) {
      e.printStackTrace();
      fail(e.getMessage());
    }
  }
  */
}