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

import java.io.IOException;
import java.util.List;

/**
 * NewsView is the main UI class responsible for rendering the news articles on screen.
 * It builds the scene using JavaFX components, handles infinite scrolling,
 * displays a loading spinner, and shows error messages.
 */
public class NewsView {

    private VBox vbox;               // Main container for articles
    private boolean isLoading = false; // To prevent multiple loads at the same time
    private Arc loadingSpinner;     // Animated loading spinner

    /**
     * Creates and returns the main Scene for the application.
     * It fetches and displays news articles, and listens for scroll to load more.
     *
     * @return Scene containing the news layout
     */
    public Scene createScene() {
        vbox = new VBox(10); // 10px spacing between article boxes
        vbox.setStyle("-fx-padding: 20px;");

        try {
            // Initial load of news articles
            List<NewsArticle> articles = NewsService.fetchNews();
            for (NewsArticle article : articles) {
                vbox.getChildren().add(createArticleBox(article));
            }
        } catch (IOException e) {
            // Show error if API fails or limit is reached
            String header = "Failed to Load News";
            String content = "It seems you've reached the request limit. Please try again later.";
            showError(header, content);
        }

        // Wrap the VBox in a ScrollPane for vertical scrolling
        ScrollPane scrollPane = new ScrollPane(vbox);
        scrollPane.setFitToWidth(true); // Make VBox stretch full width

        // Infinite scroll: When user scrolls to bottom, load more articles
        scrollPane.vvalueProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal.doubleValue() == 1.0 && !isLoading) {
                isLoading = true;
                showLoadingAnimation(); // Show loading spinner
                loadMoreArticles();     // Simulate loading new content
            }
        });

        // Return the fully constructed scene
        return new Scene(scrollPane, 800, 600);
    }

    /**
     * Creates a styled VBox container for a single news article,
     * including image, title, description, and a "Read more" link.
     *
     * @param article NewsArticle object containing data to display
     * @return VBox containing the article content
     */
    private VBox createArticleBox(NewsArticle article) {
        VBox articleBox = new VBox(5); // 5px spacing between children
        articleBox.getStyleClass().add("article-card"); // Add CSS style

        // Create and style the title label
        Label titleLabel = new Label(article.getTitle());
        titleLabel.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");

        // Create and style the description label
        Label descriptionLabel = new Label(article.getDescription());
        descriptionLabel.setStyle("-fx-font-size: 14px;");

        // Create a clickable "Read more" label that opens the article URL
        Label urlLabel = new Label("Read more");
        urlLabel.getStyleClass().add("read-more"); // Add CSS class for blue clickable link
        urlLabel.setOnMouseClicked(e -> {
            try {
                java.awt.Desktop.getDesktop().browse(java.net.URI.create(article.getUrl()));
            } catch (IOException ex) {
                showError("Failed to Open Article", "Could not open article in browser.");
            }
        });

        // Add the title, description, and link to the article box
        articleBox.getChildren().addAll(titleLabel, descriptionLabel, urlLabel);

        // Zoom effect on mouse hover
        articleBox.setOnMouseEntered(e -> {
            articleBox.setScaleX(1.02);
            articleBox.setScaleY(1.02);
        });
        articleBox.setOnMouseExited(e -> {
            articleBox.setScaleX(1.0);
            articleBox.setScaleY(1.0);
        });

        // Load and display the article image (if available)
        String imageUrl = article.getImage();
        if (imageUrl != null && !imageUrl.isEmpty()) {
            try {
                Image image = new Image(imageUrl, true); // Load image asynchronously
                ImageView imageView = new ImageView(image);
                imageView.setFitWidth(300);
                imageView.setPreserveRatio(true);
                imageView.getStyleClass().add("news-image"); // Apply styling

                // Add the image at the top of the article box
                articleBox.getChildren().add(0, imageView);
            } catch (Exception e) {
                System.out.println("Invalid image URL: " + imageUrl); // Safe fail if image breaks
            }
        }

        return articleBox;
    }
    /**
     * Displays an animated loading spinner at the bottom of the VBox.
     * This is triggered when new content is being loaded (e.g., infinite scroll).
     */
    private void showLoadingAnimation() {
        // If a spinner already exists, do nothing to avoid duplicates
        if (loadingSpinner != null && vbox.getChildren().contains(loadingSpinner)) return;

        // Create a new arc shape to serve as the loading spinner
        loadingSpinner = new Arc();
        loadingSpinner.setRadiusX(20); // Horizontal radius
        loadingSpinner.setRadiusY(20); // Vertical radius
        loadingSpinner.setStartAngle(0); // Starting angle of the arc
        loadingSpinner.setLength(270); // Length of the arc (in degrees)
        loadingSpinner.setStroke(Color.BLUE); // Spinner color
        loadingSpinner.setStrokeWidth(5); // Thickness
        loadingSpinner.setFill(null); // No fill, only stroke
        loadingSpinner.setType(ArcType.OPEN); // Open arc (not pie-shaped)
        loadingSpinner.setStrokeLineCap(StrokeLineCap.ROUND); // Rounded edges

        // Animate the arc to rotate infinitely
        RotateTransition rotate = new RotateTransition(Duration.seconds(1), loadingSpinner);
        rotate.setByAngle(360); // Rotate full circle
        rotate.setCycleCount(Animation.INDEFINITE); // Loop forever
        rotate.setInterpolator(Interpolator.LINEAR); // Smooth, even rotation
        rotate.play();

        // Wrap the arc in a VBox to center it horizontally
        VBox spinnerBox = new VBox(loadingSpinner);
        spinnerBox.setAlignment(Pos.CENTER); // Center align inside VBox
        spinnerBox.setMinHeight(60);
        spinnerBox.setMaxWidth(Double.MAX_VALUE);

        // Add the spinner container to the main VBox
        vbox.getChildren().add(spinnerBox);
    }

    /**
     * Loads more news articles in the background.
     * Simulates a 2-second network delay, then fetches and displays additional articles.
     * Also ensures that UI updates happen on the JavaFX Application Thread using Platform.runLater().
     */
    private void loadMoreArticles() {
        new Thread(() -> {
            try {
                // Simulate network delay (useful for showing loading animation)
                Thread.sleep(2000);

                // Fetch additional articles (reusing the same NewsService)
                List<NewsArticle> newArticles = NewsService.fetchNews();

                // Safely update the UI on the JavaFX thread
                Platform.runLater(() -> {
                    removeLoadingAnimation(); // Remove the spinner
                    for (NewsArticle article : newArticles) {
                        vbox.getChildren().add(createArticleBox(article));
                    }
                    isLoading = false; // Allow future loads
                });

            } catch (IOException e) {
                // Handle network/API error
                Platform.runLater(() -> {
                    removeLoadingAnimation();
                    showError("Failed to Load More Articles", e.getMessage());
                    isLoading = false;
                });
            } catch (InterruptedException e) {
                // Print any thread interruption error (e.g., during sleep)
                e.printStackTrace();
            }
        }).start();
    }

    /**
     * Removes the loading spinner from the VBox (if it exists).
     * This is usually called after articles have been loaded or on failure.
     */
    private void removeLoadingAnimation() {
        // Remove the spinner container (VBox) if it contains the Arc spinner
        vbox.getChildren().removeIf(node -> node instanceof VBox box && box.getChildren().contains(loadingSpinner));
        loadingSpinner = null;
    }

    /**
     * Displays an error popup using JavaFX Alert.
     *
     * @param header  The title or short message
     * @param content The detailed error message
     */
    private void showError(String header, String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
