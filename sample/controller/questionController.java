package sample.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;


public class questionController implements Initializable {
    @FXML
    Label TitleLabel;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
       String s="asbdb";
        //TitleLabel.setText(s);

    }
   public void  setTitle(String s){
       //s="asbdb";
       TitleLabel.setText(s);
   }

}
