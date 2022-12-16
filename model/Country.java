package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.Serializable;

public class Country implements Serializable {
    //private ArrayList<Parameter> parameters = new ArrayList<>();

    private ObservableList<CsParameter> csParameters;
    private ObservableList<MaParameter> maParameters;


    private String countryName;
    private double marketAttractivenes;
    private double competetiveStrength;


    public Country(String countryName, double marketAttractivenes, double competetiveStrength){

        this.countryName = countryName;
        this.marketAttractivenes = marketAttractivenes;
        this.competetiveStrength = competetiveStrength;

    }
    public Country(){
        this.countryName = "Default";
        // instantiates the observableArralist
        // //- this is used in the controller when the table is initialized
    this.maParameters = FXCollections.observableArrayList();
    maParameters.add(new MaParameter("Market Size (Industry size) - Listed in bn $",0,"",0));
    maParameters.add(new MaParameter("Market Growth (Industry growth) - year To year - Listed in % ",0,"",0));
    maParameters.add(new MaParameter("Competitive situation Listed as 1 2 3 for heavy, medium, low",0,"",0));
    maParameters.add(new MaParameter("Economic stability (GDP per Capita)",0,"",0));
    maParameters.add(new MaParameter("Political stability - Index value of 1-100",0,"",0));
    maParameters.add(new MaParameter("Infrastructure Listed as 1 2 3 for Good, medium, bad",0,"",0));

    this.csParameters = FXCollections.observableArrayList();
        csParameters.add(new CsParameter("Current market share in % (if currently present in the country",0,"",0));
        csParameters.add(new CsParameter("Product fit to customers - Listed as 1 2 3 for Good, Medium, Bad",0,"",0));
        csParameters.add(new CsParameter("Product fit to customers - Listed as 1 2 3 for Good, Medium, Bad",0,"",0));
        csParameters.add(new CsParameter("Current distribution channel network - Listed As 1 2 3 for Large, Medium, Small",0,"",0));

    }

    // Used in controller to access parameters associated with each country
    public ObservableList<MaParameter> getMaParameters() {
        return maParameters;
    }
    public ObservableList<CsParameter> getCsParameters() {
        return csParameters;
    }

    // Used in controller to set the
    public boolean setCountryName(String countryName) {
        if (countryName.isEmpty()){
            return false;
        } else {
            this.countryName = countryName;
             return true;
        }
    }
    @Override
    public String toString() {

        return "\nCountry name: "+ countryName+ "\n" +
                "Market attractiveness: " + marketAttractivenes + "\n"+
                "Competitive Strength: " + competetiveStrength;
    }
    public double getMarketAttractivenes(){
        return this.marketAttractivenes;
    }
    public double getCompetetiveStrength(){
        return this.competetiveStrength;
    }


    public Country copy(Country country){
        Country c = new Country();
        c.countryName = country.countryName;
        c.marketAttractivenes = this.marketAttractivenes;
        c.competetiveStrength = this.competetiveStrength;
        return c;
    }


    public void calculateScores(Country country){
        double sum1 = 0;
        for (int i = 0; i < country.getMaParameters().size(); i++){
            sum1 += country.getMaParameters().get(i).getCalculatedScore();
        }
        double sum2 = 0;
        for (int i = 0; i < country.getCsParameters().size(); i++){
            sum2 += country.getCsParameters().get(i).getCalculatedScore();
        }
    this.marketAttractivenes = sum1/100;
    this.competetiveStrength = sum2/100;
    }

public String getCountryName(){
        return this.countryName;
}

}
