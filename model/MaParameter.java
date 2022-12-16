package model;

import java.io.Serializable;

public class MaParameter implements Serializable {
    private String paramText;
    private double paramWeight;
    private String paramValue;
    private double paramScore;
    private double calculatedScore;
    public MaParameter(String paramText){
        this.paramText = paramText;
        this.paramWeight = 0;
        this.paramValue = "";
        this.paramScore = 0;
        this.calculatedScore = getCalculatedScore();
    }
    public MaParameter(String paramText, double paramWeight, String paramValue,double paramScore){
        this.paramText = paramText;
        this.paramWeight = paramWeight;
        this.paramValue = paramValue;
        this.paramScore = paramScore;
        this.calculatedScore = getCalculatedScore();
    }


    public String getParamText() {
        return paramText;
    }
    public void setParamText(String paramText) {
        this.paramText = paramText;
    }


    public double getParamWeight() {
        return paramWeight;
    }
    public void setParamWeight(double paramWeight) {
        this.paramWeight = paramWeight;
    }


    public String getParamValue() {
        return paramValue;
    }
    public void setParamValue(String paramValue) {
        this.paramValue = paramValue;
    }


    public void setParamScore(double paramScore) {
        this.paramScore = paramScore;
    }
    public double getParamScore() {
        return paramScore;
    }


    public double getCalculatedScore(){
        double score = paramScore;
        double weight = paramWeight;
        double sum = score * weight;

        this.calculatedScore = sum;
      return sum;
    };

    @Override
    public String toString() {
        return String.valueOf(calculatedScore);
    }

    public MaParameter copy(){
        MaParameter p = new MaParameter(this.paramText,this.paramWeight,this.paramValue,this.paramScore);
        p.calculatedScore = this.calculatedScore;

        return p;

    }

}
