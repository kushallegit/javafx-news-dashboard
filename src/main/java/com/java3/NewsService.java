package com.java3;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import com.google.gson.Gson;
import com.java3.model.NewsArticle;
import com.java3.model.NewsResponse;

import java.io.IOException;
import java.util.List;

public class NewsService {

    private static final OkHttpClient client = new OkHttpClient();
    private static final String API_KEY = "1e417d82a32cef118105fb1a285d9404"; // API key
    private static final String API_URL = "https://gnews.io/api/v4/top-headlines?lang=en&token=" + API_KEY;

    public static List<NewsArticle> fetchNews() throws IOException {
        Request request = new Request.Builder()
                .url(API_URL)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (response.code() == 429) {
                throw new IOException("API limit reached. Please wait for the daily reset.");
            }

            if (!response.isSuccessful()) {
                throw new IOException("Unexpected error: " + response.code());
            }

            if (response.body() == null) {
                throw new IOException("Empty response body.");
            }

            String jsonResponse = response.body().string();
            Gson gson = new Gson();

            NewsResponse newsResponse = gson.fromJson(jsonResponse, NewsResponse.class);
            return newsResponse.getArticles();
        }
    }
}
