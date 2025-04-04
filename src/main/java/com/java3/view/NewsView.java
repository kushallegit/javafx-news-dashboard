package com.java3.view;

import com.java3.NewsService;
import com.java3.model.NewsArticle;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;
import javafx.scene.shape.StrokeLineCap;
import javafx.util.Duration;
import javafx.animation.*;
import java.util.Arrays;
import java.util.ArrayList;


import java.io.IOException;
import java.util.List;

public class NewsView {

    private VBox vbox;
    private boolean isLoading = false;
    private Arc loadingSpinner;

    public Scene createScene() throws IOException {
        vbox = new VBox(10);
        vbox.setStyle("-fx-padding: 20px;");

        List<NewsArticle> articles = NewsService.fetchNews();

        for (NewsArticle article : articles) {
            vbox.getChildren().add(createArticleBox(article));
        }

        ScrollPane scrollPane = new ScrollPane(vbox);
        scrollPane.setFitToWidth(true);

        scrollPane.vvalueProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal.doubleValue() == 1.0 && !isLoading) {
                isLoading = true;
                showLoadingAnimation();
                loadMoreArticles();
            }
        });

        return new Scene(scrollPane, 800, 600);
    }

    private VBox createArticleBox(NewsArticle article) {
        VBox articleBox = new VBox(5);
        articleBox.setStyle("-fx-border-color: #ccc; -fx-border-radius: 5px; -fx-padding: 10px;");

        Label titleLabel = new Label("Title: " + article.getTitle());
        titleLabel.setStyle("-fx-font-size: 16px; -fx-font-weight: bold; -fx-text-fill: #333;");

        Label descriptionLabel = new Label("Description: " + article.getDescription());
        descriptionLabel.setStyle("-fx-font-size: 14px; -fx-text-fill: #555;");

        Label urlLabel = new Label("Read more: " + article.getUrl());
        urlLabel.setStyle("-fx-font-size: 14px; -fx-text-fill: #0066cc;");
        urlLabel.setOnMouseClicked(e -> {
            try {
                java.awt.Desktop.getDesktop().browse(java.net.URI.create(article.getUrl()));
            } catch (IOException ex) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Failed to Open Article");
                alert.setContentText("An error occurred while trying to open the article.");
                alert.showAndWait();
            }
        });

        articleBox.getChildren().addAll(titleLabel, descriptionLabel, urlLabel);

        String imageUrl = article.getImage();
        if (imageUrl != null && !imageUrl.isEmpty()) {
            try {
                Image image = new Image(imageUrl, true);
                ImageView imageView = new ImageView(image);
                imageView.setFitWidth(300);
                imageView.setPreserveRatio(true);
                articleBox.getChildren().add(0, imageView);
            } catch (Exception e) {
                System.out.println("Invalid image URL: " + imageUrl);
            }
        }

        return articleBox;
    }

    private void showLoadingAnimation() {
        if (loadingSpinner != null && vbox.getChildren().contains(loadingSpinner)) return;

        loadingSpinner = new Arc();
        loadingSpinner.setRadiusX(20);
        loadingSpinner.setRadiusY(20);
        loadingSpinner.setStartAngle(0);
        loadingSpinner.setLength(270);
        loadingSpinner.setStroke(Color.BLUE);
        loadingSpinner.setStrokeWidth(5);
        loadingSpinner.setFill(null);
        loadingSpinner.setType(ArcType.OPEN);
        loadingSpinner.setStrokeLineCap(StrokeLineCap.ROUND);

        RotateTransition rotate = new RotateTransition(Duration.seconds(1), loadingSpinner);
        rotate.setByAngle(360);
        rotate.setCycleCount(Animation.INDEFINITE);
        rotate.setInterpolator(Interpolator.LINEAR);
        rotate.play();

        VBox spinnerBox = new VBox(loadingSpinner);
        spinnerBox.setAlignment(Pos.CENTER);
        spinnerBox.setMinHeight(60);
        spinnerBox.setMaxWidth(Double.MAX_VALUE);

        vbox.getChildren().add(spinnerBox);
    }

    private void loadMoreArticles() {
        new Thread(() -> {
            try {
                Thread.sleep(2000); // Simulate network delay

                // ✅ Make sure this line exists:
                List<NewsArticle> newArticles = NewsService.fetchNews();

                // ✅ Now it's defined and can be used here
                Platform.runLater(() -> {
                    removeLoadingAnimation();
                    for (NewsArticle article : newArticles) {
                        vbox.getChildren().add(createArticleBox(article));
                    }
                    isLoading = false;
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }


    private void removeLoadingAnimation() {
        vbox.getChildren().removeIf(node -> node instanceof VBox box && box.getChildren().contains(loadingSpinner));
        loadingSpinner = null;
    }
}
