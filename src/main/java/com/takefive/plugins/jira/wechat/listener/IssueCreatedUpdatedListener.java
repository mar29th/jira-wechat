package com.takefive.plugins.jira.wechat.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

import com.atlassian.event.api.EventListener;
import com.atlassian.event.api.EventPublisher;
import com.atlassian.jira.event.issue.IssueEvent;
import com.atlassian.jira.event.type.EventType;
import com.atlassian.jira.issue.Issue;
import com.atlassian.sal.api.message.I18nResolver;
import com.atlassian.sal.api.pluginsettings.PluginSettingsFactory;
import com.atlassian.sal.api.user.UserManager;
import com.takefive.plugins.jira.wechat.api.ConnectionException;
import com.takefive.plugins.jira.wechat.api.WeChatActiveConnection;
import com.takefive.plugins.jira.wechat.api.template.Article;
import com.takefive.plugins.jira.wechat.api.template.NewsMessage;
import com.takefive.plugins.jira.wechat.configuration.UserInfoAccess;
import com.takefive.plugins.jira.wechat.configuration.template.UserInfo;
import com.takefive.plugins.jira.wechat.util.URLAssembler;

public class IssueCreatedUpdatedListener implements InitializingBean, DisposableBean {
  
  private final Logger logger = LoggerFactory.getLogger(IssueCreatedUpdatedListener.class);
  
  private final EventPublisher eventPublisher;
  private final PluginSettingsFactory pluginSettingsFactory;
  private final UserManager userManager;
  private final I18nResolver i18nResolver;
  private final WeChatActiveConnection activeConnection;
  private final UserInfoAccess userInfoAccess;
  
  private final String username;
  
  public IssueCreatedUpdatedListener(EventPublisher eventPublisher,
      PluginSettingsFactory pluginSettingsFactory, UserManager userManager, I18nResolver i18nResolver) {
    this.eventPublisher = eventPublisher;
    this.pluginSettingsFactory = pluginSettingsFactory;
    this.userManager = userManager;
    this.i18nResolver = i18nResolver;
    this.activeConnection = new WeChatActiveConnection(this.pluginSettingsFactory);
    this.username = this.userManager.getRemoteUsername();
    this.userInfoAccess = new UserInfoAccess(this.pluginSettingsFactory);
  }
  
  @EventListener
  public void onIssueEvent(IssueEvent event) {
    logger.debug("Issue event called");
    long eventTypeId = event.getEventTypeId();
    String title = "";
    String description = "";
    if (eventTypeId == EventType.ISSUE_CREATED_ID) {
      logger.debug("Issue created");
      title = i18nResolver.getText("issue-created.title");
      description = i18nResolver.getText("issue-created.description");
    }
    else if (eventTypeId == EventType.ISSUE_UPDATED_ID) {
      logger.debug("Issue updated");
      title = i18nResolver.getText("issue-updated.title");
      description = i18nResolver.getText("issue-updated.description");
    }
    else if (eventTypeId == EventType.ISSUE_CLOSED_ID) {
      logger.debug("Issue closed");
      title = i18nResolver.getText("issue-closed.title");
      description = i18nResolver.getText("issue-closed.description");
    }
    Issue issue = event.getIssue();
    NewsMessage message = new NewsMessage();
    message.addArticle(new Article(title,
                                   description,
                                   URLAssembler.toIssueURL(issue.getKey())));
    UserInfo userInfo = userInfoAccess.getUserInfo(username);
    message.addRecipient(userInfo.getUserId());
    try {
      activeConnection.sendMessage(message);
    } catch (ConnectionException e) {
      logger.error("Connection error: " + e.getMessage());
      e.printStackTrace();
    }
  }

  @Override
  public void afterPropertiesSet() throws Exception {
    eventPublisher.register(this);
  }
  
  @Override
  public void destroy() throws Exception {
    eventPublisher.unregister(this);
  }
}