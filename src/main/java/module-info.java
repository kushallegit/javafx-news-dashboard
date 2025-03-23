module com.sem3.java.assignment2 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.sem3.java.assignment2 to javafx.fxml;
    exports com.sem3.java.assignment2;
}