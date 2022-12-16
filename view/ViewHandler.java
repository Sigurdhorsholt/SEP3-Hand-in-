package view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.Model;

import java.io.IOException;
import java.io.Serializable;

public class ViewHandler {

    // Declare viewhandler
    private ViewHandler viewHandler;
    //Declare model
    private Model model;
    private Stage primartStage;


    private Scene mainScene;
    private Scene addCountryScene;
    private Scene countriesOverviewScene;
    private Scene macsGraphicScene;

    private CountriesOverviewController CountriesOverviewController;
    private AddCountryController addCountryController;
    private MainController mainController;
    private MacsGraphicController macsGraphicController;


//    used to check which scene should be active and shown
    public static final String MAIN_SCENE = "MAIN_SCENE";
    public static final String ADD_COUNTRY_SCENE = "ADD_COUNTRY_SCENE";
    public static final String COUNTRIES_OVERVIEW_SCENE = "COUNTRIES_OVERVIEW_SCENE";
    public static final String  MACS_GRAPHICS_SCENE = "MACS_GRAPHICS_SCENE";


    public ViewHandler(Stage primartStage, Model model) {

        this.primartStage = primartStage;
        this.model = model;


        // Loads Main scene
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("Main.fxml"));
        try {
            mainScene = new Scene(loader.load());
            mainController = loader.getController();
            mainController.init(this, model);
        } catch (IOException e) {
            System.out.println("could not find Main.fxml");
            System.exit(1);
        }

        // Loads add country scene
        loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("AddCountry.fxml"));
        try {
            addCountryScene = new Scene(loader.load());
            addCountryController = loader.getController();
            addCountryController.init(this, model);
        } catch (IOException e) {
            System.out.println("could not find AddCountry.fxml");
            System.exit(1);
        }

        // Loads Countries Overview Scene
        loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("CountriesOverview.fxml"));
        try {
            countriesOverviewScene = new Scene(loader.load());
            CountriesOverviewController = loader.getController();
            CountriesOverviewController.init(this, model);
        } catch (IOException e) {
            System.out.println("could not find Main CountriesOverview.fxml");
            System.exit(1);
        }

        // Loads Macs Grpahics Scene
        loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("MacsGraphic.fxml"));
        try {
            macsGraphicScene = new Scene(loader.load());
            macsGraphicController = loader.getController();
            macsGraphicController.init(this, model);
        } catch (IOException e) {
//            System.out.println("could not find Main MacsGraphics.fxml");
            e.printStackTrace();
            System.exit(1);
        }
        changeScene(MAIN_SCENE);
    }


    public void changeScene(String sceneName){

        if (MAIN_SCENE == sceneName){
            primartStage.setTitle("Home page");
            primartStage.setScene(mainScene);
            primartStage.show();
        } else if (ADD_COUNTRY_SCENE == sceneName) {
            primartStage.setTitle("Add new Country");
            primartStage.setScene(addCountryScene);
            primartStage.show();

        } else if (COUNTRIES_OVERVIEW_SCENE == sceneName) {
            primartStage.setTitle("Countries Overview");
            primartStage.setScene(countriesOverviewScene);
            primartStage.show();

        } else if (MACS_GRAPHICS_SCENE == sceneName) {
            primartStage.setTitle("Graphics Overview");
            primartStage.setScene(macsGraphicScene);
            primartStage.show();

        }


    }


}
