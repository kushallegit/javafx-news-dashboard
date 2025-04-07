package com.java3.model;

import java.util.List;

/**
 * NewsResponse represents the full response from the GNews API.
 * It contains the total number of articles and a list of NewsArticle objects.
 */
public class NewsResponse {

    // Total number of articles available from the API (not just in this response)
    private int totalArticles;

    // List of articles returned in this API response
    private List<NewsArticle> articles;

    /**
     * Default constructor required for deserialization (used by Gson).
     */
    public NewsResponse() {}

    /**
     * Returns the total number of articles found.
     * @return totalArticles
     */
    public int getTotalArticles() {
        return totalArticles;
    }

    public void setTotalArticles(int totalArticles) {
        this.totalArticles = totalArticles;
    }

    /**
     * Returns the list of news articles returned by the API.
     * @return articles List of NewsArticle objects
     */
    public List<NewsArticle> getArticles() {
        return articles;
    }

    public void setArticles(List<NewsArticle> articles) {
        this.articles = articles;
    }

    /**
     * Returns a string representation of the response object.
     * Useful for debugging.
     */
    @Override
    public String toString() {
        return "NewsResponse{" +
                "totalArticles=" + totalArticles +
                ", articles=" + articles +
                '}';
    }
}
