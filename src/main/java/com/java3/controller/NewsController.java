package com.java3.controller;

import com.google.gson.Gson;
import com.java3.model.NewsArticle;
import com.java3.model.NewsResponse;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Scanner;

public class NewsController {
    public List<NewsArticle> fetchNews(String keyword) throws IOException {
        String apiKey = "1e417d82a32cef118105fb1a285d9404";
        String endpoint = "https://gnews.io/api/v4/search?q=" + keyword + "&lang=en&token=" + apiKey;

        URL url = new URL(endpoint);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");

        Scanner scanner = new Scanner(conn.getInputStream());
        StringBuilder json = new StringBuilder();
        while (scanner.hasNext()) {
            json.append(scanner.nextLine());
        }

        scanner.close();

        // Parse the response into NewsResponse (which contains the list of articles)
        NewsResponse newsResponse = new Gson().fromJson(json.toString(), NewsResponse.class);

        // Return the list of articles
        return newsResponse.getArticles();
    }
}