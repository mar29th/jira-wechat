package com.takefive.plugins.jira.wechat.rest;

import java.util.Map;

import com.atlassian.plugins.rest.common.security.AnonymousAllowed;
import com.atlassian.sal.api.pluginsettings.PluginSettings;
import com.atlassian.sal.api.pluginsettings.PluginSettingsFactory;
import com.atlassian.sal.api.user.UserManager;
import com.takefive.plugins.jira.wechat.util.SettingsConstants;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


@Path("/roster")
public class WeChatAccountInfo {
  
  private final PluginSettings pluginSettings;
  private final UserManager userManager;
  private final String remoteUsername;
  
  public WeChatAccountInfo(PluginSettingsFactory pluginSettingsFactory, UserManager userManager) {
    this.pluginSettings = pluginSettingsFactory.createGlobalSettings();
    this.userManager = userManager;
    this.remoteUsername = userManager.getRemoteUsername();
  }
  
  @SuppressWarnings("unchecked")
  @POST
  @AnonymousAllowed
  @Produces(MediaType.APPLICATION_JSON)
  public Response searchInfo(@FormParam("username") String username) {
    Map<String, String> idMap = (Map<String, String>) pluginSettings.get(SettingsConstants.WECHAT_ID);
    Map<String, String> userIdMap = (Map<String, String>) pluginSettings.get(SettingsConstants.USERID);
    return Response.ok(new WeChatAccountInfoModel(username, idMap.get(username), userIdMap.get(username)), MediaType.APPLICATION_JSON).build();
  }
  
  @SuppressWarnings("unchecked")
  @POST
  @AnonymousAllowed
  @Consumes(MediaType.APPLICATION_JSON)
  public Response updateInfo(@QueryParam(value = "username") String username, WeChatAccountInfoModel model) {
    Map<String, String> idMap = (Map<String, String>) pluginSettings.get(SettingsConstants.WECHAT_ID);
    Map<String, String> userIdMap = (Map<String, String>) pluginSettings.get(SettingsConstants.USERID);
    idMap.put(username, model.getWeChatId());
    userIdMap.put(username, model.getUserId());
    pluginSettings.put(SettingsConstants.WECHAT_ID, idMap);
    pluginSettings.put(SettingsConstants.USERID, userIdMap);
    return Response.ok().build();
  }
}