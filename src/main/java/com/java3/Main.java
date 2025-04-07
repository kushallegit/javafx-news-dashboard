package com.java3;

import com.java3.view.NewsView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 * Main entry point for the JavaFX News Application.
 */
public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        try {
            // Create the main UI view
            NewsView newsView = new NewsView();
            Scene scene = newsView.createScene();

            // Load and apply external stylesheet
            var css = getClass().getResource("/styles.css");
            if (css != null) {
                scene.getStylesheets().add(css.toExternalForm());
            } else {
                System.out.println("Warning: styles.css not found.");
            }

            // Set the title of the application window
            primaryStage.setTitle("News App - By Kushal Gurung");

            // Set the main scene of the application
            primaryStage.setScene(scene);

            // Set the application window icon (logo)
            primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("/images/logo.png")));

            // Show the application window
            primaryStage.show();

        } catch (Exception e) {
            // Print any exception that occurs during launch
            e.printStackTrace(); // Optional: Show error pop-up
        }
    }

    /**
     * Launches the JavaFX application.
     */
    public static void main(String[] args) {
        launch(args);
    }
}
