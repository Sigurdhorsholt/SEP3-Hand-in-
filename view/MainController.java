package view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import model.Country;
import model.Model;
import model.Saver;

import javax.swing.*;
import java.util.ArrayList;

public class MainController  {

    private ViewHandler viewHandler;
    private Model model;

    private boolean checkloaded = true;

    @FXML
    private ListView<Country> countriesList;



    public void init(ViewHandler viewHandler, Model model) {
        this.viewHandler = viewHandler;
        this.model = model;
        countriesList.setItems(model.getCountries());

    }


    public void SwitchAddCountry(ActionEvent event) {
        viewHandler.changeScene(ViewHandler.ADD_COUNTRY_SCENE);
    }
    public void SwitchMacsGraphics(ActionEvent event) {
        viewHandler.changeScene(ViewHandler.MACS_GRAPHICS_SCENE);
    }
    public void SwitchCountryOverview(ActionEvent event) {
        viewHandler.changeScene(ViewHandler.COUNTRIES_OVERVIEW_SCENE);
    }

    public void printModel(ActionEvent event) {
        model.printModel();
    }

    public void saveModel(ActionEvent event) {
        model.save();
    }

    public void loadModel(ActionEvent event) {
        if (checkloaded){
            ArrayList<Saver> saved = model.load();

            for (int i = 1; i < saved.size(); i++){
                String name = saved.get(i).getCountryName();
                double maScore = saved.get(i).getMaScore();
                double csScore = saved.get(i).getCsScore();
                Country savedCountry = new Country(name,maScore,csScore);
                model.getCountries().add(savedCountry);
            }
            System.out.println("UPDATED MODEL");
            model.printModel();
            checkloaded = false;
        } else {
            JOptionPane.showMessageDialog(null, "You have already loaded in this session." +
                    " Loading again will cause duplicate data");
        }
    }
}
