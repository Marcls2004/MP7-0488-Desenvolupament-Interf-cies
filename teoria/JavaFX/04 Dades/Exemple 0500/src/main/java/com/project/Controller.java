package com.project;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.event.ActionEvent;

import java.net.URL;
import java.util.ResourceBundle;


public class Controller implements Initializable {

    @FXML
    private TableView<AnimalsModel> animalsTable;
    @FXML
    private TableColumn<AnimalsModel, String> columnEspecie;
    @FXML
    private TableColumn<AnimalsModel, Integer> columnLongevitat;
    @FXML
    private TableColumn<AnimalsModel, Integer> columnPotes;

    @FXML
    private TextField fieldEspecie, fieldLongevitat, fieldPotes;
    @FXML
    private Button buttonAdd, buttonDelete, buttonRefresh;

    private ObservableList<AnimalsModel> animalsList;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        AppData db = AppData.getInstance();
        initData();

        animalsList = FXCollections.observableArrayList(AnimalsDAO.getAll());

        columnEspecie.setCellValueFactory(new PropertyValueFactory<>("especie"));
        columnLongevitat.setCellValueFactory(new PropertyValueFactory<>("longevitat"));
        columnPotes.setCellValueFactory(new PropertyValueFactory<>("numeropotes"));

        animalsTable.setItems(animalsList);

    }

    @FXML
    private void actionAdd(ActionEvent event) {
        String especie = fieldEspecie.getText();
        int longevitat = Integer.parseInt(fieldLongevitat.getText());
        int numeropotes = Integer.parseInt(fieldPotes.getText());

        AnimalsModel newAnimal = new AnimalsModel(especie, longevitat, numeropotes);
        AnimalsDAO.addItem(newAnimal);

        animalsList.add(newAnimal);
        fieldEspecie.clear();
        fieldLongevitat.clear();
        fieldPotes.clear();
    }

    @FXML
    private void actionDelete(ActionEvent event) {
        AnimalsModel selectedAnimal = animalsTable.getSelectionModel().getSelectedItem();
        if (selectedAnimal != null) {
            AnimalsDAO.deleteItem(selectedAnimal.getEspecie());
            animalsList.remove(selectedAnimal);
        }
    }

    @FXML
    private void actionRefresh(ActionEvent event) {
        animalsList.setAll(AnimalsDAO.getAll());
    }

    public static void initData() {
        AppData db = AppData.getInstance();

        db.update("DROP TABLE IF EXISTS animals");
        db.update("CREATE TABLE IF NOT EXISTS animals (" +
                "especie TEXT NOT NULL," +
                "longevitat INTEGER," +
                "numeropotes INTEGER)");

        db.update("INSERT INTO animals (especie, longevitat, numeropotes) VALUES ('Gos', 14, 4)");
        db.update("INSERT INTO animals (especie, longevitat, numeropotes) VALUES ('Gat', 15, 4)");
        db.update("INSERT INTO animals (especie, longevitat, numeropotes) VALUES ('Elefant', 70, 4)");
        db.update("INSERT INTO animals (especie, longevitat, numeropotes) VALUES ('Tortuga', 100, 4)");
        db.update("INSERT INTO animals (especie, longevitat, numeropotes) VALUES ('Colom', 6, 2)");
    }
}

