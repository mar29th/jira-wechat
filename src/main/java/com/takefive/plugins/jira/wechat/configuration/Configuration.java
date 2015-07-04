package com.takefive.plugins.jira.wechat.configuration;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by lafickens on 7/3/15.
 */

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Configuration {
  
  @XmlElement private String corpId;
  @XmlElement private String corpSecret;

  public String getCorpId() {
    return corpId;
  }

  public void setCorpId(String corpId) {
    this.corpId = corpId;
  }

  public String getCorpSecret() {
    return corpSecret;
  }

  public void setCorpSecret(String corpSecret) {
    this.corpSecret = corpSecret;
  }
}
