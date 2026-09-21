package com.project;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

public class Controller implements Initializable {

    @FXML
    private AnchorPane container;
    @FXML
    private Label labelStyle;
    @FXML
    private Button buttonChange0;
    @FXML
    private Button buttonChange1;
    @FXML
    private Button buttonBorderRed;

    // Called when the FXML file is loaded
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        labelStyle.getStyleClass().add("label");
    }

    @FXML
    private void setStyle0(ActionEvent event) {
        container.getStylesheets().clear();
        container.getStylesheets().add("/assets/style0.css");
        buttonChange0.setDisable(true);
        buttonChange1.setDisable(false);
    }

    @FXML
    private void setStyle1(ActionEvent event) {
        container.getStylesheets().clear();
        container.getStylesheets().add("/assets/style1.css");
        buttonChange0.setDisable(false);
        buttonChange1.setDisable(true);
    }

    @FXML
    private void setBorderRed(ActionEvent event) {
        String currentStyle = labelStyle.getStyle();
        String redBorderStyle = "-fx-border-color: red;";

        container.getStylesheets().clear();

        if (currentStyle.contains(redBorderStyle)) {
            // If the style already contains the red border, remove it
            labelStyle.setStyle(currentStyle.replace(redBorderStyle, ""));
        } else {
            // If the style does not contain the red border, add it
            labelStyle.setStyle(currentStyle + redBorderStyle);
        }
        buttonChange0.setDisable(false);
        buttonChange1.setDisable(false);
    }
}