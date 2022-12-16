package view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.converter.DoubleStringConverter;
import model.Country;
import model.CsParameter;
import model.MaParameter;
import model.Model;

import javax.swing.*;
import java.net.URL;
import java.util.ResourceBundle;

public class AddCountryController implements Initializable {

    private Model model;
    private ViewHandler viewHandler;
    private Country country = new Country();


    @FXML
    private TextField nameInputTxt;
    // Market Attractiveness Table
    @FXML
    private TableView<MaParameter> maTable = new TableView<>();
    @FXML
    private TableColumn<MaParameter, String> MAparamText;
    @FXML
    private TableColumn<MaParameter, Double> MAparamScore;
    @FXML
    private TableColumn<MaParameter, String> MAparamValue;
    @FXML
    private TableColumn<MaParameter, Double> MAparamWeight;
// Competitive Strength Table
@FXML
private TableView<CsParameter> csTable = new TableView<>();
    @FXML
    private TableColumn<CsParameter, String> CSparamText;
    @FXML
    private TableColumn<CsParameter, Double> CSparamScore;
    @FXML
    private TableColumn<CsParameter, String> CSparamValue;
    @FXML
    private TableColumn<CsParameter, Double> CSparamWeight;


    public void init(ViewHandler viewHandler, Model model) {
        this.viewHandler = viewHandler;
        this.model = model;
    }
    public void SwitchMain(ActionEvent event) {
        viewHandler.changeScene(ViewHandler.MAIN_SCENE);
    }
    public void addAttractivessParameter(ActionEvent event) {
        String inputStr = JOptionPane.showInputDialog("Enter Parameter Text here");
        country.getMaParameters().add(new MaParameter(inputStr));
    }

    public void addAttractivessParameterSecond(ActionEvent event) {
        String inputStr = JOptionPane.showInputDialog("Enter Parameter Text here");
        country.getCsParameters().add(new CsParameter(inputStr));
    }

    public void submitData(ActionEvent event) {
        String countryName = nameInputTxt.getText().toLowerCase();
        boolean check = country.setCountryName(countryName);
        double sum1 = 0;
        double sum2 = 0;
        for (int i = 0; i < country.getCsParameters().size(); i++){
            double c = country.getCsParameters().get(i).getParamWeight();
            double p = country.getMaParameters().get(i).getParamWeight();
            sum1 += c;
            sum2 += p;
            System.out.println(p);
        }
        if (!check){
            JOptionPane.showMessageDialog(null,"Please Enter a Country Name to submit the data");
        } else if (sum1 != 100) {
            JOptionPane.showMessageDialog(null,"Competitive Strength weights must add up to 100%");
        }else if (sum2 != 100) {
            JOptionPane.showMessageDialog(null,"Market Attractiveness weights must add up to 100%");
        } else if (check) {
            country.calculateScores(country);
            model.addCountry(country.copy(country));
            model.printModel();
            nameInputTxt.setText("");
            viewHandler.changeScene(ViewHandler.MAIN_SCENE);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        // Create and set Market Attractiveness table
        maTable.setEditable(true);
        //Set paramtext column
        maTable.setItems(country.getMaParameters());

        MAparamText.setCellValueFactory( new PropertyValueFactory<MaParameter, String>("paramText"));

        //Set paramWeight column
        MAparamWeight.setCellValueFactory(new PropertyValueFactory<MaParameter, Double>("paramWeight"));
        MAparamWeight.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));
        MAparamWeight.setOnEditCommit(event -> {
            MaParameter parameter = event.getRowValue();
            parameter.setParamWeight(event.getNewValue());
        });
        //Set paramValue column
        MAparamValue.setCellValueFactory(new PropertyValueFactory<MaParameter, String>("paramValue"));
        MAparamValue.setCellFactory(TextFieldTableCell.forTableColumn());
        MAparamValue.setOnEditCommit(event -> {

            MaParameter parameter = event.getRowValue();
            parameter.setParamValue(event.getNewValue());
        });
        //Set MAparamScore column
        MAparamScore.setCellValueFactory(new PropertyValueFactory<MaParameter, Double>("paramScore"));
        MAparamScore.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));
        MAparamScore.setOnEditCommit(event -> {
            if(checkScoreRange(event.getNewValue())){
                MaParameter parameter = event.getRowValue();
                parameter.setParamScore(event.getNewValue());
            }
        });

        csTable.setEditable(true);
//        //Set paramtext column
        csTable.setItems(country.getCsParameters());

        CSparamText.setCellValueFactory( new PropertyValueFactory<CsParameter, String>("paramText"));

        CSparamWeight.setCellValueFactory( new PropertyValueFactory<CsParameter, Double>("paramWeight"));
        CSparamWeight.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));
        CSparamWeight.setOnEditCommit(event -> {
            CsParameter parameter = event.getRowValue();
            parameter.setParamWeight(event.getNewValue());
        });

//        Set paramWeight column
        MAparamWeight.setCellValueFactory(new PropertyValueFactory<MaParameter, Double>("paramWeight"));
        MAparamWeight.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));
        MAparamWeight.setOnEditCommit(event -> {
            MaParameter parameter = event.getRowValue();
            parameter.setParamWeight(event.getNewValue());
        });


        //Set paramValue column
        CSparamValue.setCellValueFactory(new PropertyValueFactory<CsParameter, String>("paramValue"));
        CSparamValue.setCellFactory(TextFieldTableCell.forTableColumn());
        CSparamValue.setOnEditCommit(event -> {
            CsParameter parameter = event.getRowValue();
            parameter.setParamValue(event.getNewValue());
        });
        //Set MAparamScore column
        CSparamScore.setCellValueFactory(new PropertyValueFactory<CsParameter, Double>("paramScore"));
        CSparamScore.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));
        CSparamScore.setOnEditCommit(event -> {
            if(checkScoreRange(event.getNewValue())){
                CsParameter parameter = event.getRowValue();
                parameter.setParamScore(event.getNewValue());
            }
        });

    }

    private boolean checkScoreRange(Double d) {
        if (d > 5 || d < 0) {
            JOptionPane.showMessageDialog(null, "Scores must be between 1-5");
            return false;
        } else  {
            return true;
        }
    }

    public void deleteMaParam(ActionEvent event) {
        MaParameter selectedItem =maTable.getSelectionModel().getSelectedItem();
        maTable.getItems().remove(selectedItem);
    }
    public void deleteCsParam(ActionEvent event) {
        CsParameter selectedItem =csTable.getSelectionModel().getSelectedItem();
        csTable.getItems().remove(selectedItem);
    }


}