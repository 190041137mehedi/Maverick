package sample.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import sample.Main;

import java.io.IOException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import static javafx.fxml.FXMLLoader.*;


public class registerController {
    @FXML
    TextField textField1, textField2,emailText,usernameText;
    @FXML
    PasswordField passwordText;

    @FXML
    DatePicker dobText;
    private Stage stage;
    private Scene scene;
    private Parent parent;

    public  String getDate(DatePicker datePicker){
        LocalDate localDate = datePicker.getValue();
        Instant instant = Instant.from(localDate.atStartOfDay(ZoneId.systemDefault()));
        Date date = Date.from(instant);

        return String.valueOf(localDate);
    }
    public void switchToLogin(ActionEvent event) throws IOException{

        Parent root = load(getClass().getResource("../view/login.fxml"));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        //String css = this.getClass().getResource("../css/login.css").toExternalForm();
        //scene.getStylesheets().add(css);
        stage.setScene(scene);
        stage.show();
        stage.setMinWidth(stage.getWidth());
        stage.setMinHeight(stage.getHeight());
    }
    public  void switchTohome(ActionEvent event) throws IOException {
        Main.client.setUserName(usernameText.getText());
        Main.client.setPassword(passwordText.getText());
//        System.out.println("Register,"+usernameText.getText()+","+passwordText.getText()+","+emailText.getText()+","+getDate(dobText));
        Main.client.VerifyUser("Register,"+Main.client.getUserName()+","+Main.client.getPassword()+","+emailText.getText()+","+getDate(dobText));
        Main.client.listenForMessage();
        Parent root = load(getClass().getResource("../view/sidebar.fxml"));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        //String css = this.getClass().getResource("../css/sidebar.css").toExternalForm();
        //scene.getStylesheets().add(css);
        stage.setScene(scene);
        stage.show();
        stage.setMinWidth(stage.getWidth());
        stage.setMinHeight(stage.getHeight());

    }
    public void switchTextField() throws IOException{
        textField1.setOnKeyPressed(event -> {
            if(event.getCode().equals(KeyCode.ENTER)){
                textField2.requestFocus();
            }
        });
    }
}