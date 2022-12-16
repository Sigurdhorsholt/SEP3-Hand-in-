import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.Model;
import view.*;

import java.io.Serializable;

public class Main extends Application {


    // Declare viewhandler
    private ViewHandler viewHandler;
    //Declare model
    private Model model;




    public static void main(String[] args) {
        Application.launch(Main.class);
        System.out.println("Main works");


    }
    @Override
    public void start(Stage primaryStage) {
        // generate the model on start of program
        model = generateModel();
        //initiates viewhandler
        viewHandler = new ViewHandler(primaryStage, model);
    }
    public Model generateModel(){

        //calls constructor of model class
        Model model = new Model();
        return model;
    }




}