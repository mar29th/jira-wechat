package com.takefive.plugins.jira.wechat.listener;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

import com.atlassian.event.api.EventListener;
import com.atlassian.event.api.EventPublisher;
import com.atlassian.jira.event.issue.IssueEvent;
import com.atlassian.sal.api.pluginsettings.PluginSettingsFactory;

public class ProjectCreatedUpdatedListener implements InitializingBean, DisposableBean {
  
  private final EventPublisher eventPublisher;
  
  public ProjectCreatedUpdatedListener(EventPublisher eventPublisher) {
    this.eventPublisher = eventPublisher;
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
