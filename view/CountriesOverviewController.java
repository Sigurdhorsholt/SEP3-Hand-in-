package view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import model.Country;
import model.Model;

public class CountriesOverviewController{

    private Model model;
    private ViewHandler viewHandler;

    @FXML
    ListView<Country> overViewList;

    public void init(ViewHandler viewHandler, Model model) {
        this.viewHandler = viewHandler;
        this.model = model;
//
    }

    public void SwitchMain(ActionEvent event) {
        viewHandler.changeScene(ViewHandler.MAIN_SCENE);
    }


    public void updateTable(ActionEvent event) {
    }
}
