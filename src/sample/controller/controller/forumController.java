package sample.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import sample.Main;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import static javafx.fxml.FXMLLoader.load;

public class forumController implements Initializable {
    @FXML
    private ComboBox comboBox;
    @FXML
    private Button menuButton, applyFilter, cancelFilter;
    @FXML
    private StackPane filterPane;
    @FXML
    private VBox questionArea;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        comboBox.getItems().removeAll(comboBox.getItems());
        comboBox.getItems().addAll("MOST RECENT", "MOST POPULAR", "UNANSWERED");
        comboBox.getSelectionModel().select("MOST RECENT");


        for(int i=0; i<5; i++) {
            displayQuestion(i);
        }
    }

    public void displayQuestion(int i){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("../view/question.fxml"));
            HBox hBox = fxmlLoader.load();

            questionController q=new questionController();
                    q=fxmlLoader.getController();
            q.setTitle(Main.client.q.get(i));
            questionArea.getChildren().add(hBox);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void filter(MouseEvent event){
        loadUI("../view/filter");
    }

    public void clearStackPane(){
        filterPane.getChildren().clear();
    }

    public void loadUI(String ui){
        Parent root = null;
        try {
            root = load(getClass().getResource(ui+".fxml"));
        } catch (IOException e) {
            Logger.getLogger(sidebarController.class.getName()).log(Level.SEVERE, null, e);
        }
        filterPane.getChildren().setAll(root);
    }
}
