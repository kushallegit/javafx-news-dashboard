package com.java3;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import com.google.gson.Gson;
import com.java3.model.NewsArticle;
import com.java3.model.NewsResponse;

import java.io.IOException;
import java.util.List;

/**
 * NewsService is responsible for fetching news data from the GNews API.
 * It uses OkHttp to perform HTTP requests and Gson to parse JSON responses.
 */
public class NewsService {

    // Reusable HTTP client
    private static final OkHttpClient client = new OkHttpClient();

    // Your API key for GNews (Note: use your own key in production)
    private static final String API_KEY = "1e417d82a32cef118105fb1a285d9404";

    // Endpoint URL to fetch top English news headlines
    private static final String API_URL = "https://gnews.io/api/v4/top-headlines?lang=en&token=" + API_KEY;

    /**
     * Fetches a list of top news articles from the GNews API.
     *
     * @return List of NewsArticle objects
     * @throws IOException if the API call fails or the response is invalid
     */
    public static List<NewsArticle> fetchNews() throws IOException {
        // Build the HTTP request
        Request request = new Request.Builder()
                .url(API_URL)
                .build();

        // Send the request and get the response
        try (Response response = client.newCall(request).execute()) {

            // Handle rate-limiting
            if (response.code() == 429) {
                throw new IOException("API limit reached. Please wait for the daily reset.");
            }

            // Handle unsuccessful HTTP response
            if (!response.isSuccessful()) {
                throw new IOException("Unexpected error: " + response.code());
            }

            // Check if response body is empty
            if (response.body() == null) {
                throw new IOException("Empty response body.");
            }

            // Read the JSON response string
            String jsonResponse = response.body().string();

            // Parse the JSON into a NewsResponse object
            Gson gson = new Gson();
            NewsResponse newsResponse = gson.fromJson(jsonResponse, NewsResponse.class);

            // Return the list of articles
            return newsResponse.getArticles();
        }
    }
}
