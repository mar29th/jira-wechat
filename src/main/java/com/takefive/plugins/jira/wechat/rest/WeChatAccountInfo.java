package com.takefive.plugins.jira.wechat.rest;

import com.atlassian.plugins.rest.common.security.AnonymousAllowed;
import com.atlassian.sal.api.pluginsettings.PluginSettings;
import com.atlassian.sal.api.pluginsettings.PluginSettingsFactory;
import com.atlassian.sal.api.user.UserManager;
import com.takefive.plugins.jira.wechat.api.ConnectionException;
import com.takefive.plugins.jira.wechat.api.WeChatActiveConnection;
import com.takefive.plugins.jira.wechat.api.template.Member;
import com.takefive.plugins.jira.wechat.configuration.UserInfoAccess;
import com.takefive.plugins.jira.wechat.configuration.template.UserInfo;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


@Path("/roster")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class WeChatAccountInfo {
  
  private final PluginSettings pluginSettings;
  private final WeChatActiveConnection connection;
  private final UserInfoAccess userInfoAccess;
  
  public WeChatAccountInfo(PluginSettingsFactory pluginSettingsFactory, UserManager userManager) {
    this.pluginSettings = pluginSettingsFactory.createGlobalSettings();
    this.connection = new WeChatActiveConnection(pluginSettingsFactory);
    this.userInfoAccess = new UserInfoAccess(pluginSettingsFactory);
  }
  
  @Path("/{username}")
  @GET
  @AnonymousAllowed
  @Produces(MediaType.APPLICATION_JSON)
  public Response searchInfo(@PathParam("username") String username) {
    if (userInfoAccess.hasUserInfo(username)) {
      UserInfo userInfo = userInfoAccess.getUserInfo(username);
      return Response.ok(new WeChatAccountInfoModel(username, userInfo.getWeChatId(), userInfo.getUserId()),
                         MediaType.APPLICATION_JSON).build();
    }
    else {
      return Response.ok(new NotFoundError()).build();
    }
  }
  
  @Path("/{username}")
  @POST
  @AnonymousAllowed
  public Response updateInfo(@PathParam("username") String username, WeChatAccountInfoModel model) {
    // Connect to WeChat server
    Member member = new Member();
    String userId = userInfoAccess.getUserInfo(username).getUserId();
    member.setUserId(userId);
    member.setWeChatId(model.getWeChatId());
    try {
      connection.updateMember(member);
    } catch (ConnectionException e) {
      e.printStackTrace();
      return Response.serverError().build();
    }
    
    // Store info
    UserInfo userInfo = userInfoAccess.getOrCreateUserInfo(username);
    userInfo.setUserId(model.getWeChatId());
    userInfo.setWeChatId(model.getWeChatId());
    userInfoAccess.setUserInfo(username, userInfo);
    return Response.ok(new GeneralContent()).build();
  }
  
  @Path("/{username}")
  @DELETE
  @AnonymousAllowed
  public Response deleteInfo(@PathParam("username") String username) {
    if (!userInfoAccess.hasUserInfo(username)) {
      return Response.ok(new GeneralContent()).build();
    }
    
    UserInfo userInfo = userInfoAccess.getUserInfo(username);
    
    // Connect to WeChat server
    try {
      connection.deleteMember(userInfo.getUserId());
    } catch (ConnectionException e) {
      e.printStackTrace();
      return Response.serverError().build();
    }
    
    // Remove info
    userInfoAccess.setUserInfo(username, null);
    
    return Response.ok(new GeneralContent()).build();
  }
}