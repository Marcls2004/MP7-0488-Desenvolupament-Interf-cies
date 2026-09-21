package com.project;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class AnimalsDAO {

    public static AnimalsModel getItem(String especie) {
        String sql = "SELECT especie, longevitat, numeropotes FROM animals WHERE especie = '" + especie + "'";
        AppData db = AppData.getInstance();
        List<Map<String, Object>> results = db.query(sql);

        if (!results.isEmpty()) {
            Map<String, Object> row = results.get(0);
            return new AnimalsModel((String) row.get("especie"), (int) row.get("longevitat"),
                    (int) row.get("numeropotes"));
        }
        return null;
    }

    public static void addItem(AnimalsModel animal) {
        String sql = "INSERT INTO animals (especie, longevitat, numeropotes) VALUES ('"
                + animal.getEspecie() + "', "
                + animal.getLongevitat() + ", "
                + animal.getNumeropotes() + ")";
        AppData db = AppData.getInstance();
        db.update(sql);
    }

    public static void updateItem(AnimalsModel animal) {
        String sql = "UPDATE animals SET longevitat = "
                + animal.getLongevitat()
                + ", numeropotes = "
                + animal.getNumeropotes()
                + " WHERE especie = '"
                + animal.getEspecie() + "'";
        AppData db = AppData.getInstance();
        db.update(sql);
    }

    public static void deleteItem(String especie) {
        String sql = "DELETE FROM animals WHERE especie = '" + especie + "'";
        AppData db = AppData.getInstance();
        db.update(sql);
    }

    public static ArrayList<AnimalsModel> getAll() {
        String sql = "SELECT especie, longevitat, numeropotes FROM animals";
        AppData db = AppData.getInstance();
        ArrayList<AnimalsModel> list = new ArrayList<>();
        List<Map<String, Object>> results = db.query(sql);

        for (Map<String, Object> row : results) {
            String especie = (String) row.get("especie");
            int longevitat = (Integer) row.get("longevitat");
            int numeropotes = (Integer) row.get("numeropotes");
            list.add(new AnimalsModel(especie, longevitat, numeropotes));
        }
        return list;
    }
}
