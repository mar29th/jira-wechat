package com.takefive.plugins.jira.wechat.api;

public class URLHelper {
  public static String getAuthenticationURL(String corpId, String corpSecret) {
    return String.format("https://qyapi.weixin.qq.com/cgi-bin/gettoken?corpid=%s&corpsecret=%s", corpId, corpSecret);
  }
  
  public static String getSendMessageURL(String accessToken) {
    return String.format("https://qyapi.weixin.qq.com/cgi-bin/message/send?access_token=%s", accessToken);
  }
  
  public static String getAddMemberURL(String accessToken) {
    return String.format("https://qyapi.weixin.qq.com/cgi-bin/user/create?access_token=%s", accessToken);
  }
  
  public static String getUpdateMemberURL(String accessToken) {
    return String.format("https://qyapi.weixin.qq.com/cgi-bin/user/update?access_token=%s", accessToken);
  }
  
  public static String getDeleteMemberURL(String accessToken, String userId) {
    return String.format("https://qyapi.weixin.qq.com/cgi-bin/user/delete?access_token=%s&userid=%s", accessToken, userId);
  }
  
  public static String getDeleteMembersURL(String accessToken) {
    return String.format("https://qyapi.weixin.qq.com/cgi-bin/user/batchdelete?access_token=%s", accessToken);
  }
  
  public static String getRetrieveMemberURL(String accessToken, String userId) {
    return String.format("https://qyapi.weixin.qq.com/cgi-bin/user/get?access_token=%s&userid=%s", accessToken, userId);
  }
  
  public static String getAddDepartmentURL(String accessToken) {
    return String.format("https://qyapi.weixin.qq.com/cgi-bin/department/create?access_token=%s", accessToken);
  }
  
  public static String getUpdateDepartmentURL(String accessToken) {
    return String.format("https://qyapi.weixin.qq.com/cgi-bin/user/update?access_token=%s", accessToken);
  }
  
  public static String getDeleteDepartmentURL(String accessToken, int departmentId) {
    return String.format("https://qyapi.weixin.qq.com/cgi-bin/department/delete?access_token=%s&id=%s", accessToken, departmentId);
  }
  
  public static String getRetrieveDepartmentListURL(String accessToken, boolean forSelectedDepartment, int departmentId) {
      String retval = String.format("https://qyapi.weixin.qq.com/cgi-bin/department/list?access_token=%s", accessToken);
      if (forSelectedDepartment)
        retval.concat("&id=" + departmentId);
      return retval;
  }
  
  public static String getInviteFollowURL(String accessToken) {
    return String.format("https://qyapi.weixin.qq.com/cgi-bin/invite/send?access_token=%s", accessToken);
  }
}
