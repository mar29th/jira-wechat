package com.takefive.plugins.jira.wechat.api;

import com.atlassian.jira.util.json.JSONException;
import com.atlassian.jira.util.json.JSONObject;
import com.atlassian.sal.api.pluginsettings.PluginSettings;
import com.atlassian.sal.api.pluginsettings.PluginSettingsFactory;
import com.ning.http.client.AsyncHttpClient;
import com.ning.http.client.Response;

import java.io.IOException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * Created by lafickens on 7/3/15.
 */
public class WeChatActiveConnection {

  private AsyncHttpClient client;
  private PluginSettings pluginSettings;

  public WeChatActiveConnection(PluginSettingsFactory pluginSettingsFactory) {
    this.client = new AsyncHttpClient();
    this.pluginSettings = pluginSettingsFactory.createGlobalSettings();
  }

  public String getAuthenticationURL(String corpId, String corpSecret) {
    return String.format("https://qyapi.weixin.qq.com/cgi-bin/gettoken?corpid=%s&corpsecret=%s", corpId, corpSecret);
  }

  public String getAccessToken() {
    String corpId = (String) pluginSettings.get("wechat-corpId");
    String corpSecret = (String) pluginSettings.get("wechat-corpSecret");
    Future<Response> f = client.prepareGet(getAuthenticationURL(corpId, corpSecret)).execute();
    try {
      Response r = f.get();
      JSONObject tokenJson = new JSONObject(r.getResponseBody());
      String token = tokenJson.getString("access_token");
      int expiresAfter = tokenJson.getInt("expires_in");
      pluginSettings.put("wechat-access-token", token);
      pluginSettings.put("wechat-access-token-expires-at", System.currentTimeMillis() / 1000 + expiresAfter);
      return token;
    } catch (InterruptedException | ExecutionException | IOException e) {
      e.printStackTrace();
      throw new RuntimeException("Connection failure");
    } catch (JSONException e) {
      e.printStackTrace();
      throw new RuntimeException("Corrupt data");
    }
  }
  
  public void send(String message) {
    
  }
}
