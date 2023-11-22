package sample.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import sample.Main;

import java.io.IOException;

import static javafx.fxml.FXMLLoader.*;

public class loginController {
    @FXML
    TextField textField1, textField2;

    private Stage stage;
    private Scene scene;
    private Parent parent;

    public void switchToRegister(ActionEvent event) throws IOException{
        Parent root = load(getClass().getResource("../view/register.fxml"));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        //String css = this.getClass().getResource("../css/login.css").toExternalForm();
        //scene.getStylesheets().add(css);
        stage.setScene(scene);
        stage.show();
        stage.setMinWidth(stage.getWidth());
        stage.setMinHeight(stage.getHeight());
    }

    public void switchToHome(ActionEvent event) throws IOException{
        Main.client.setUserName(textField1.getText());
        Main.client.setPassword(textField2.getText());
        Main.client.VerifyUser("Login,"+Main.client.getUserName()+","+Main.client.getPassword());
        if(Main.client.ListenforAccknowledgement().equals("ACK")){
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
        else {
            System.out.println("Nak");
        }
    }

    public void switchTextField() throws IOException{
        textField1.setOnKeyPressed(event -> {
            function();
            if(event.getCode().equals(KeyCode.ENTER)){
                Main.client.setUserName(textField1.getText());
                textField2.requestFocus();
            }
        });
    }
    public void switchTextField2() throws IOException{
        textField2.setOnKeyPressed(event -> {
            function();
            if(event.getCode().equals(KeyCode.ENTER)){
                Main.client.setUserName(textField2.getText());
            }
        });
    }

    public void function(){
        System.out.println(textField1.getText());
    }
}