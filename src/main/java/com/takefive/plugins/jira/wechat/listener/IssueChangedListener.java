package com.takefive.plugins.jira.wechat.listener;

import com.atlassian.event.api.EventListener;
import com.atlassian.event.api.EventPublisher;
import com.atlassian.jira.event.issue.IssueEvent;
import com.atlassian.jira.event.type.EventType;
import com.atlassian.sal.api.message.I18nResolver;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

/**
 * Created by lafickens on 7/3/15.
 */
public class IssueChangedListener implements InitializingBean, DisposableBean {
  
  private EventPublisher eventPublisher;
  private I18nResolver i18nResolver;

  public IssueChangedListener(EventPublisher eventPublisher, I18nResolver i18nResolver) {
    this.eventPublisher = eventPublisher;
    this.i18nResolver = i18nResolver;
  }
  
  @Override
  public void afterPropertiesSet() throws Exception {
    eventPublisher.register(this);
  }

  @Override
  public void destroy() throws Exception {
    eventPublisher.unregister(this);
  }
  
  @EventListener
  public void onIssueEvent(IssueEvent issueEvent) {
    
  }
}
