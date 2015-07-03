package com.takefive.plugins.jira.wechat;

import com.atlassian.event.api.EventListener;
import com.atlassian.event.api.EventPublisher;
import com.atlassian.jira.event.issue.IssueEvent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by lafickens on 7/3/15.
 */
public class IssueChangedListener {
    private static final Logger LOGGER = LoggerFactory.getLogger(IssueChangedListener.class);

    public IssueChangedListener(EventPublisher eventPublisher) {
        eventPublisher.register(this);
    }

    @EventListener
    public void onIssueEvent(IssueEvent issueEvent) {

    }
}
