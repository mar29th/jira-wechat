package com.takefive.plugins.jira.wechat.rest;

import javax.xml.bind.annotation.XmlElement;

public class GeneralContent {
  
  @XmlElement
  protected int status;
  
  @XmlElement
  protected String comment;
  
  public GeneralContent() {
    status = StatusType.SUCCESS;
    comment = StatusType.SUCCESS_COMMENT;
  }
  
  public GeneralContent(int status, String comment) {
    this.status = status;
    this.comment = comment;
  }
  
  public int getStatus() {
    return status;
  }
  
  public void setStatus(int status) {
    this.status = status;
  }
  
  public String getComment() {
    return comment;
  }
  
  public void setComment(String comment) {
    this.comment = comment;
  }
}
