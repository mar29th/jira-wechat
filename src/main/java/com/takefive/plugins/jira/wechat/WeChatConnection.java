package com.takefive.plugins.jira.wechat;

import com.ning.http.client.AsyncHttpClient;
import com.ning.http.client.Response;

import java.util.concurrent.Future;

/**
 * Created by lafickens on 7/3/15.
 */
public class WeChatConnection {

    private AsyncHttpClient client;

    public WeChatConnection() {
        client = new AsyncHttpClient();
    }

    public String getAuthenticationURL(String corpId, String corpSecret) {
        return String.format("https://qyapi.weixin.qq.com/cgi-bin/gettoken?corpid=%s&corpsecret=%s", corpId, corpSecret);
    }

    public void getAccessToken() {
        Future<Response> future = client.prepareGet(getAuthenticationURL())
    }
}
