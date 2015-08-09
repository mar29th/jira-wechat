package com.takefive.plugins.jira.wechat.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

import com.atlassian.event.api.EventListener;
import com.atlassian.event.api.EventPublisher;
import com.atlassian.jira.event.issue.IssueEvent;
import com.atlassian.jira.event.type.EventType;
import com.atlassian.sal.api.message.I18nResolver;
import com.atlassian.sal.api.pluginsettings.PluginSettingsFactory;
import com.atlassian.sal.api.user.UserManager;
import com.takefive.plugins.jira.wechat.api.WeChatActiveConnection;

public class IssueCreatedUpdatedListener implements InitializingBean, DisposableBean {
  
  private final Logger logger = LoggerFactory.getLogger(IssueCreatedUpdatedListener.class);
  
  private final EventPublisher eventPublisher;
  private final PluginSettingsFactory pluginSettingsFactory;
  private final UserManager userManager;
  
  private WeChatActiveConnection activeConnection;
  
  public IssueCreatedUpdatedListener(EventPublisher eventPublisher, PluginSettingsFactory pluginSettingsFactory, UserManager userManager) {
    this.eventPublisher = eventPublisher;
    this.pluginSettingsFactory = pluginSettingsFactory;
    this.userManager = userManager;
    activeConnection = new WeChatActiveConnection(this.pluginSettingsFactory);
  }
  
  @EventListener
  public void onIssueCreatedUpdated(IssueEvent event) {
    long eventTypeId = event.getEventTypeId();
    if (eventTypeId == EventType.ISSUE_CREATED_ID)
      onIssueCreated(event);
    else if (eventTypeId == EventType.ISSUE_UPDATED_ID)
      onIssueUpdated(event);
    else if (eventTypeId == EventType.ISSUE_CLOSED_ID)
      onIssueClosed(event);
  }

  @Override
  public void afterPropertiesSet() throws Exception {
    eventPublisher.register(this);
  }
  
  @Override
  public void destroy() throws Exception {
    eventPublisher.unregister(this);
  }
  
  private void onIssueCreated(IssueEvent event) {
    logger.debug("Issue created");
    
  }
  
  private void onIssueUpdated(IssueEvent event) {
    logger.debug("Issue updated");
  }
  
  private void onIssueClosed(IssueEvent event) {
    logger.debug("Issue closed");
  }
}
