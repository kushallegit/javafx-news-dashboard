package com.java3.model;

import java.util.List;

public class NewsResponse {
    private int totalArticles;
    private List<NewsArticle> articles;

    public NewsResponse() {}

    public int getTotalArticles() {
        return totalArticles;
    }

    public void setTotalArticles(int totalArticles) {
        this.totalArticles = totalArticles;
    }

    public List<NewsArticle> getArticles() {
        return articles;
    }

    public void setArticles(List<NewsArticle> articles) {
        this.articles = articles;
    }

    @Override
    public String toString() {
        return "NewsResponse{" +
                "totalArticles=" + totalArticles +
                ", articles=" + articles +
                '}';
    }
}
