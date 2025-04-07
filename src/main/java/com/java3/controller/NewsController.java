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

public class NewsController {

    public List<NewsArticle> fetchNews(String keyword) throws IOException {
        String apiKey = "1e417d82a32cef118105fb1a285d9404";
        String endpoint = "https://gnews.io/api/v4/search?q=" + keyword + "&lang=en&token=" + apiKey;

        URL url = new URL(endpoint);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");

        int responseCode = conn.getResponseCode();
        if (responseCode != 200) {
            throw new IOException("Failed to fetch news: HTTP error code " + responseCode);
        }

        BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        StringBuilder json = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            json.append(line);
        }
        reader.close();

        NewsResponse newsResponse = new Gson().fromJson(json.toString(), NewsResponse.class);
        return newsResponse.getArticles();
    }
}
