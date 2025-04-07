package com.java3;

import com.java3.view.NewsView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        try {
            NewsView newsView = new NewsView();
            Scene scene = newsView.createScene();

            var css = getClass().getResource("/styles.css");
            if (css != null) {
                scene.getStylesheets().add(css.toExternalForm());
            } else {
                System.out.println("Warning: styles.css not found.");
            }

            primaryStage.setTitle("News App - By Kushal Gurung");
            primaryStage.setScene(scene);
            primaryStage.show();

        } catch (Exception e) {
            e.printStackTrace(); // Optional: Show error pop-up
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
