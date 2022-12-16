package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.*;
import java.util.ArrayList;

public class Model  {
private ObservableList<Country> countries;

    public ObservableList<Country> getCountries() {
        return countries;
    }


    public Model() {
        System.out.println("Model is live");
//        this.countries = FXCollections.observableArrayList();
      //  this.countries = new ArrayList<>();
        this.countries = FXCollections.observableArrayList();

        countries.add(new Country());
    }

    public void addCountry(Country country) {
        countries.add(country);
    }

    public void printModel() {
        System.out.println(this);
    }

    @Override
    public String toString() {
        return "Model: " +
                "Countries: " + countries +
                ",      ";
    }


    public void save() {

        try {
            FileOutputStream fileOut = new FileOutputStream("obj.bin");
            ObjectOutputStream write = new ObjectOutputStream(fileOut);



            for (int i = 0; i <countries.size(); i ++){
                String name = getCountries().get(i).getCountryName();
                double maScore = getCountries().get(i).getMarketAttractivenes();
                double csScore = getCountries().get(i).getCompetetiveStrength();

                write.writeObject(new Saver(name, maScore, csScore));
            }

//            write.writeObject(new Saver("Test", 4,4));
            write.close();

        } catch (FileNotFoundException e) {

            System.out.println("File not found or could not be opened");
            throw new RuntimeException(e);
        } catch (IOException e) {
            System.out.println("IO Error writing to file");
            throw new RuntimeException(e);
        }
        System.out.println("Done Writing");

    }

    public ArrayList<Saver> load(){

        ArrayList<Saver> listOfSaved = new ArrayList<>();
        Saver saver = new Saver();

        try {
            FileInputStream fileIn = new FileInputStream("obj.bin");
            ObjectInputStream read = new ObjectInputStream(fileIn);

            while (true){
                try {
                    saver = (Saver)read.readObject();
                    listOfSaved.add(saver);
                    System.out.println("THIS LIST:  "+listOfSaved);
                } catch (EOFException eof){
                    System.out.println("End of file");
                    break;
                } catch (ClassNotFoundException e) {
                    System.out.println("Class not found");
                    throw new RuntimeException(e);
                }

            }
            read.close();
            return listOfSaved;
        } catch (FileNotFoundException e) {
            System.out.println("File could not be found");
            throw new RuntimeException(e);
        } catch (IOException e) {
            System.out.println("Error reading file");
            throw new RuntimeException(e);
        }

    }
}
