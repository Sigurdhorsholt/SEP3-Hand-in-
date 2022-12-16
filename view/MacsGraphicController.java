package view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BubbleChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import model.Model;

import javax.swing.*;
import java.net.URL;
import java.util.ResourceBundle;

public class MacsGraphicController implements Initializable {
    private Model model;
    private ViewHandler viewHandler;

    @FXML
    private BubbleChart<Double,Double> macsChart;

    public void init(ViewHandler viewHandler, Model model) {
        this.viewHandler = viewHandler;
        this.model = model;
    }
    public void SwitchMain(ActionEvent event) {
        viewHandler.changeScene(ViewHandler.MAIN_SCENE);
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        macsChart.getYAxis().setLabel("Market Attractiveness");
        macsChart.getXAxis().setLabel("Competitive Strength");

    }

    public void UpdateChart(ActionEvent event) {
        if (model.getCountries().size() > 1){
            for (int i = 1; i < model.getCountries().size(); i++) {
                String name = model.getCountries().get(i).getCountryName();
                int ma = (int) model.getCountries().get(i).getMarketAttractivenes();
                double cs = model.getCountries().get(i).getCompetetiveStrength();
                XYChart.Series series = new XYChart.Series();
                series.setName(name);
                series.getData().add(new XYChart.Data(cs,ma,0.1));
                macsChart.getData().addAll(series);
            }
        }else {
            JOptionPane.showMessageDialog(null,"There is currently now countries added. \n Please go to Homepage -> Add Country");

        }

    }
}
