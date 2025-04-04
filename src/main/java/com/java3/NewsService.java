package com.java3;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import com.google.gson.Gson;
import com.java3.model.NewsArticle;  // Import the NewsArticle class
import com.java3.model.NewsResponse;  // Import the NewsResponse class
import java.io.IOException;
import java.util.List;

public class NewsService {
    private static final String API_URL = "https://gnews.io/api/v4/top-headlines?lang=en&token=1e417d82a32cef118105fb1a285d9404";

    public static List<NewsArticle> fetchNews() throws IOException {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(API_URL)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("Unexpected code " + response);
            }

            String jsonResponse = response.body().string();
            Gson gson = new Gson();

            NewsResponse newsResponse = gson.fromJson(jsonResponse, NewsResponse.class);
            return newsResponse.getArticles(); // Return list directly
        }
    }

}
