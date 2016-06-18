package com.kotsuba.weathermap.app.gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Created by Kotsuba on 13.06.2016.
 */
public class App extends Application {

    public static void main(String[] args) throws Exception {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader();
        Parent root = loader.load(getClass().getResourceAsStream("/main.fxml"));
        stage.setTitle("WeatherMap");
        stage.setScene(new Scene(root));
        stage.setMinHeight(370);
        stage.setMinWidth(570);
        stage.show();
    }
}
