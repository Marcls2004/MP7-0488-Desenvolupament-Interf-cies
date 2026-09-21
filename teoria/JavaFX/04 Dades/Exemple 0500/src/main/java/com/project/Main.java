package com.project;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application {

    final int WINDOW_WIDTH = 600;
    final int WINDOW_HEIGHT = 400;

    @Override
    public void start(Stage stage) throws Exception {

        // Carrega la vista inicial des del fitxer FXML
        Parent root = FXMLLoader.load(getClass().getResource("/assets/layout.fxml"));
        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.setTitle("JavaFX App");
        stage.setWidth(WINDOW_WIDTH);
        stage.setHeight(WINDOW_HEIGHT);
        stage.show();

        // Afegeix una icona només si no és un Mac
        if (!System.getProperty("os.name").contains("Mac")) {
            Image icon = new Image("file:icons/icon.png");
            stage.getIcons().add(icon);
        }

        // Afegeix un listener per detectar canvis en les dimensions de la finestra
        stage.widthProperty().addListener((obs, oldVal, newVal) -> {
            System.out.println("Width changed: " + newVal);
        });

        stage.heightProperty().addListener((obs, oldVal, newVal) -> {
            System.out.println("Height changed: " + newVal);
        });
    }

    public static void main(String[] args) {
        launch(args);
    }
}
