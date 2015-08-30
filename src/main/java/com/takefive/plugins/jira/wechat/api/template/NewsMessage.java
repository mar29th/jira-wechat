package com.takefive.plugins.jira.wechat.api.template;

import java.util.ArrayList;

import org.json.JSONObject;

import com.google.gson.Gson;

public class NewsMessage extends Message {
  private ArrayList<Article> articles;
  
  public NewsMessage() {
    articles = new ArrayList<Article>();
    msgType = "news";
  }
  
  public void addArticle(Article article) {
    articles.add(article);
  }
  
  public ArrayList<Article> getArticles() {
    return articles;
  }
  
  public void deleteArticle(int index) {
    articles.remove(index);
  }
  
  public JSONObject toJsonObject() {
    JSONObject retval = super.toJsonObject();
    retval.append("news", new Gson().toJson(articles));
    return retval;
  }
  
  public String toJson() {
    return toJsonObject().toString();
  }
}
