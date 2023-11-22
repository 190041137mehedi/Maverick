package sample.controller;

import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static javafx.fxml.FXMLLoader.load;


public class homeController implements Initializable {
    private Stage stage;
    private Scene scene;
    private Parent parent;

    @FXML
    private AnchorPane anchorPane, anchorPane2, anchorPane3;
    @FXML
    private VBox vbox;
    @FXML
    private ToggleButton tbutton6;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        tbutton6.setSelected(true);
        vbox.setOnMouseEntered(e -> sideBarEnter());
        anchorPane2.setOnMouseExited(e -> sideBarExit());
    }

    public void sideBarEnter(){
        anchorPane3.setVisible(false);
        anchorPane3.setDisable(true);
        FadeTransition fadeTransition1=new FadeTransition(Duration.seconds(0.05),anchorPane);
        fadeTransition1.setFromValue(0);
        fadeTransition1.setToValue(1);
        fadeTransition1.play();
        TranslateTransition translateTransition1=new TranslateTransition(Duration.seconds(0.05),anchorPane);
        translateTransition1.setByX(+200);
        translateTransition1.play();
    }
    public void sideBarExit(){
        TranslateTransition translateTransition1=new TranslateTransition(Duration.seconds(0.05),anchorPane);
        translateTransition1.setByX(-200);
        translateTransition1.play();
        anchorPane3.setVisible(true);
        translateTransition1.setOnFinished(event -> {
                    anchorPane3.setDisable(false);
                }
        );
    }

    public void switchToLogin(ActionEvent event) throws IOException {
        Parent root = load(getClass().getResource("../view/login.fxml"));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        String css = this.getClass().getResource("../css/login.css").toExternalForm();
        scene.getStylesheets().add(css);
        stage.setScene(scene);
        stage.show();
    }

}