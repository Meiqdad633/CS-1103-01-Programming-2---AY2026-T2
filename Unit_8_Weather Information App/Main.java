package com.weatherapp;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage stage) {
        TextField cityField = new TextField();
        cityField.setPromptText("Enter city name...");
        Button search = new Button("Search");
        Label temp = new Label("-");
        Label humidity = new Label("-");
        Label wind = new Label("-");
        Label condition = new Label("-");

        BorderPane root = new BorderPane();
        ToolBar top = new ToolBar(new Label("City:"), cityField, search);
        root.setTop(top);

        VBox card = new VBox(8,
            new Label("Temperature:"), temp,
            new Label("Humidity:"), humidity,
            new Label("Wind:"), wind,
            new Label("Condition:"), condition
        );
        card.setStyle("-fx-padding: 16;");
        root.setCenter(card);

        Scene scene = new Scene(root, 600, 400);
        stage.setTitle("Weather Information App");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
