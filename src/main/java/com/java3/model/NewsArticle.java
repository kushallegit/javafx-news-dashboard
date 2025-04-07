package com.java3.model;

/**
 * NewsArticle represents a single news item from the GNews API response.
 * It contains key attributes such as title, description, content, and more.
 */
public class NewsArticle {
    private String title;
    private String description;
    private String content;
    private String url;
    private String image;
    private String publishedAt;

    /**
     * Default constructor for serialization (Gson uses this).
     */
    public NewsArticle() {}

    /**
     * Parameterized constructor for creating a NewsArticle manually.
     */
    public NewsArticle(String title, String description, String content,
                       String url, String image, String publishedAt) {
        this.title = title;
        this.description = description;
        this.content = content;
        this.url = url;
        this.image = image;
        this.publishedAt = publishedAt;
    }

    // Getters and setters

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getPublishedAt() {
        return publishedAt;
    }

    public void setPublishedAt(String publishedAt) {
        this.publishedAt = publishedAt;
    }

    /**
     * Useful for logging or debugging.
     */
    @Override
    public String toString() {
        return "NewsArticle{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", url='" + url + '\'' +
                ", publishedAt='" + publishedAt + '\'' +
                '}';
    }
}
