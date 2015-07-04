package com.takefive.plugins.jira.wechat.api;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/callback")
public class WeChatCallbackHandler {
  
  @POST
  @GET
  @Consumes(MediaType.APPLICATION_XML)
  public Response resolve(@Context HttpServletRequest request) {
    return null;
  }
}