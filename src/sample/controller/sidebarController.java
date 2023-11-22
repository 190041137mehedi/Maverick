package sample.controller;

import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import static javafx.fxml.FXMLLoader.load;


public class sidebarController implements Initializable {
    private Stage stage;
    private Scene scene;
    private Parent parent;

    @FXML
    private AnchorPane anchorPane, anchorPane2, anchorPane3;
    @FXML
    private BorderPane contentArea;
    @FXML
    private VBox vbox;
    @FXML
    private ToggleButton tbutton1, tbutton2, tbutton3, tbutton4, tbutton5, tbutton6, tbutton7;

    @FXML
    private Button sbutton1, sbutton2, sbutton3, sbutton4, sbutton5, sbutton6, sbutton7;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        sbutton1.setOnAction(e -> setButtonSelected(tbutton1));
        sbutton2.setOnAction(e -> setButtonSelected(tbutton2));
        sbutton3.setOnAction(e -> setButtonSelected(tbutton3));
        sbutton4.setOnAction(e -> setButtonSelected(tbutton4));
        sbutton5.setOnAction(e -> setButtonSelected(tbutton5));
        sbutton6.setOnAction(e -> setButtonSelected(tbutton6));

        vbox.setOnMouseEntered(e -> sideBarEnter());
        anchorPane2.setOnMouseExited(e -> sideBarExit());
    }

    public void setButtonSelected(ToggleButton button){
        tbutton1.setSelected(false);
        tbutton2.setSelected(false);
        tbutton3.setSelected(false);
        tbutton4.setSelected(false);
        tbutton5.setSelected(false);
        tbutton6.setSelected(false);
        button.setSelected(true);
    }

    public void sideBarEnter(){
        anchorPane3.setVisible(false);
        anchorPane3.setDisable(true);
        FadeTransition fadeTransition1=new FadeTransition(Duration.seconds(0.0001),anchorPane);
        fadeTransition1.setFromValue(0);
        fadeTransition1.setToValue(1);
        fadeTransition1.play();
        TranslateTransition translateTransition1=new TranslateTransition(Duration.seconds(0.0001),anchorPane);
        translateTransition1.setByX(+200);
        translateTransition1.play();
    }
    public void sideBarExit(){
        TranslateTransition translateTransition1=new TranslateTransition(Duration.seconds(0.0001),anchorPane);
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
        stage.setMinWidth(stage.getWidth());
        stage.setMinHeight(stage.getHeight());
    }

    public void forum(MouseEvent event){
        loadUI("../view/forum");
    }

    public void loadUI(String ui){
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource(ui+".fxml"));
        } catch (IOException e) {
            Logger.getLogger(sidebarController.class.getName()).log(Level.SEVERE, null, e);
        }
        contentArea.setCenter(root);
    }

}