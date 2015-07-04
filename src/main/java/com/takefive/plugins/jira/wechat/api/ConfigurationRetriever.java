package com.takefive.plugins.jira.wechat.api;

import com.atlassian.sal.api.pluginsettings.PluginSettings;
import com.atlassian.sal.api.pluginsettings.PluginSettingsFactory;
import com.atlassian.sal.api.transaction.TransactionCallback;
import com.atlassian.sal.api.transaction.TransactionTemplate;
import com.atlassian.sal.api.user.UserManager;
import com.atlassian.sal.api.user.UserProfile;
import com.takefive.plugins.jira.wechat.configuration.Configuration;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by lafickens on 7/3/15.
 */

@Path("/configuration")
public class ConfigurationRetriever {

    private final UserManager userManager;
    private final PluginSettingsFactory pluginSettingsFactory;
    private final TransactionTemplate transactionTemplate;

    public ConfigurationRetriever(UserManager userManager,
                                  PluginSettingsFactory pluginSettingsFactory,
                                  TransactionTemplate transactionTemplate) {
        this.userManager = userManager;
        this.pluginSettingsFactory = pluginSettingsFactory;
        this.transactionTemplate = transactionTemplate;
    }

    @SuppressWarnings("unchecked")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response get(@Context HttpServletRequest request) {
        UserProfile user =  userManager.getRemoteUser(request);
        if (user.getUsername() == null || !userManager.isSystemAdmin(user.getUserKey())) {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
        return Response.ok(transactionTemplate.execute(new TransactionCallback() {
            @Override
            public Object doInTransaction() {
                PluginSettings pluginSettings = pluginSettingsFactory.createGlobalSettings();
                String corpId = (String) pluginSettings.get("wechat-corpId");
                if (corpId == null)
                    corpId = "N/A";
                String corpSecret = (String) pluginSettings.get("wechat-corpSecret");
                if (corpSecret == null)
                    corpSecret = "N/A";
                Configuration config = new Configuration();
                config.setCorpId(corpId);
                config.setCorpSecret(corpSecret);
                return config;
            }
        })).build();
    }

    @SuppressWarnings("unchecked")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public Response put(final Configuration config, @Context HttpServletRequest request) {
        UserProfile user =  userManager.getRemoteUser(request);
        if (user.getUsername() == null || !userManager.isSystemAdmin(user.getUserKey())) {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
        transactionTemplate.execute(new TransactionCallback() {
            @Override
            public Object doInTransaction() {
                PluginSettings pluginSettings = pluginSettingsFactory.createGlobalSettings();
                pluginSettings.put("wechat-corpId", config.getCorpId());
                pluginSettings.put("wechat-corpSecret", config.getCorpSecret());
                return null;
            }
        });
        return Response.noContent().build();
    }
}
