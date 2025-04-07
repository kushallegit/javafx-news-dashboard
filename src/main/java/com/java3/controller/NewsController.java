package com.java3.controller;

import com.google.gson.Gson;
import com.java3.model.NewsArticle;
import com.java3.model.NewsResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

/**
 * NewsController handles the logic for fetching news articles
 * based on a search keyword using the GNews API.
 */
public class NewsController {

    /**
     * Fetches news articles related to a given keyword from the GNews API.
     *
     * @param keyword The search term for querying news articles
     * @return A list of NewsArticle objects related to the keyword
     * @throws IOException If an error occurs during the API call or response parsing
     */
    public List<NewsArticle> fetchNews(String keyword) throws IOException {
        // API key (free tier) — consider externalizing for better security
        String apiKey = "1e417d82a32cef118105fb1a285d9404";

        // Build the endpoint URL with the search query and language filter
        String endpoint = "https://gnews.io/api/v4/search?q=" + keyword + "&lang=en&token=" + apiKey;

        // Create a URL object and open a connection
        URL url = new URL(endpoint);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");

        // Check for successful response
        int responseCode = conn.getResponseCode();
        if (responseCode != 200) {
            throw new IOException("Failed to fetch news: HTTP error code " + responseCode);
        }

        // Read the JSON response
        BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        StringBuilder json = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            json.append(line);
        }
        reader.close();

        // Parse JSON into NewsResponse object using Gson
        NewsResponse newsResponse = new Gson().fromJson(json.toString(), NewsResponse.class);

        // Return the list of articles from the response
        return newsResponse.getArticles();
    }
}
