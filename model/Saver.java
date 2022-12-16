package model;

import java.io.Serializable;

public class Saver implements Serializable {

    private String countryName;
    private double maScore;
    private double csScore;

    public Saver(){

    }

    public Saver(String countryName, double maScore, double csScore) {
        this.countryName = countryName;
        this.maScore = maScore;
        this.csScore = csScore;
    }

    @Override
    public String toString() {
        return countryName +
                ", maScore: " + maScore +
                ", csScore: " + csScore;
    }


    public double getCsScore() {
        return csScore;
    }

    public String getCountryName() {
        return countryName;
    }

    public double getMaScore() {
        return maScore;
    }

}
