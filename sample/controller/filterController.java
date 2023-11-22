package sample.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;

import javax.swing.*;
import java.net.URL;
import java.util.ResourceBundle;

public class filterController implements Initializable {
    @FXML
    private AnchorPane filter;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    public void removeChildren(MouseEvent event){
        ((StackPane) filter.getParent()).getChildren().remove(filter);
    }
}
