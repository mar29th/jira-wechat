package com.takefive.plugins.jira.wechat.api;

import com.atlassian.jira.util.json.JSONException;
import com.atlassian.jira.util.json.JSONObject;
import com.atlassian.sal.api.pluginsettings.PluginSettings;
import com.atlassian.sal.api.pluginsettings.PluginSettingsFactory;
import com.ning.http.client.AsyncHttpClient;
import com.ning.http.client.Response;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * Handles active WeChat connection requests (eg. sending messages).
 * 
 * @author lafickens
 *
 */
public class WeChatActiveConnection {

  private AsyncHttpClient client;
  private PluginSettings pluginSettings;
  
  private String corpId;
  private String corpSecret;
  
  private static String accessToken = null;
  private static Calendar expiresAt = null;

  public WeChatActiveConnection(PluginSettingsFactory pluginSettingsFactory) {
    client = new AsyncHttpClient();
    pluginSettings = pluginSettingsFactory.createGlobalSettings();
    corpId = (String) pluginSettings.get("wechat-corpId");
    corpSecret = (String) pluginSettings.get("wechat-corpSecret");
  }

  public String getAuthenticationURL(String corpId, String corpSecret) {
    return String.format("https://qyapi.weixin.qq.com/cgi-bin/gettoken?corpid=%s&corpsecret=%s", corpId, corpSecret);
  }
  
  public String getSendMessageURL(String accessToken) {
    return String.format("https://qyapi.weixin.qq.com/cgi-bin/message/send?access_token=%s", accessToken);
  }

  public String getAccessToken() throws ConnectionException {
    // Check if token exists already and is within expiration.
    if (accessToken != null && expiresAt.after(Calendar.getInstance())) {
      return accessToken;
    }
    
    // If token doesn't exist or has already expired, request a new one.
    Future<Response> f = client.prepareGet(getAuthenticationURL(corpId, corpSecret)).execute();
    try {
      Response r = f.get();
      JSONObject tokenJson = new JSONObject(r.getResponseBody());
      if (tokenJson.has("errcode")) {
        throw new ConnectionException("Access token: WeChat server returns error code: " + tokenJson.getInt("errcode"));
      }
      accessToken = tokenJson.getString("access_token");
      expiresAt = Calendar.getInstance();
      expiresAt.add(Calendar.SECOND, tokenJson.getInt("expires_in"));
      return accessToken;
    } catch (InterruptedException | ExecutionException | IOException e) {
      e.printStackTrace();
      throw new ConnectionException("Connection failure");
    } catch (JSONException e) {
      e.printStackTrace();
      throw new ConnectionException("Corrupt data");
    }
  }
  
  public void sendMessage(String message) throws ConnectionException {
    String token = getAccessToken();
    String url = getSendMessageURL(token);
    Future<Response> f = client.preparePost(url).execute();
    try {
      Response r = f.get();
      JSONObject responseJson = new JSONObject(r.getResponseBody());
      if (responseJson.getInt("errcode") != 0) {
        throw new ConnectionException("Send message: WeChat server returns error code: " + responseJson.getInt("errorcode"));
      }
    } catch (InterruptedException | ExecutionException | IOException e) {
      e.printStackTrace();
      throw new ConnectionException("Connection failure");
    } catch (JSONException e) {
      e.printStackTrace();
      throw new ConnectionException("Corrupt data");
    }
  }
}
