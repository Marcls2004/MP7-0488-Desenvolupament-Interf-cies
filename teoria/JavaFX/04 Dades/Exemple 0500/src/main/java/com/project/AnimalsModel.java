package com.project;

public class AnimalsModel {
    private String especie;
    private int longevitat;
    private int numeropotes;

    // Constructor
    public AnimalsModel(String especie, int longevitat, int numeropotes) {
        this.especie = especie;
        this.longevitat = longevitat;
        this.numeropotes = numeropotes;
    }

    // Getters
    public String getEspecie() {
        return especie;
    }

    public int getLongevitat() {
        return longevitat;
    }

    public int getNumeropotes() {
        return numeropotes;
    }

    // Setters
    public void setEspecie(String especie) {
        this.especie = especie;
    }

    public void setLongevitat(int longevitat) {
        this.longevitat = longevitat;
    }

    public void setNumeropotes(int numeropotes) {
        this.numeropotes = numeropotes;
    }
}
