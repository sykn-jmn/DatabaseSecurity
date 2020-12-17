/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package frateoslogin;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 *
 * @author Al Bee
 */
public class FrtsloginController implements Initializable {
    
    private double xOffset = 0;
    private double yOffset = 0;


    @FXML
    private Button logInButton;
    @FXML
    private TextField textUsername;

    @FXML
    private PasswordField textPassword;

    @FXML
    private void exit(ActionEvent event) throws IOException{
        System.exit(0);
    }
    
    @FXML
    private Label label;


    public void toPass(ActionEvent e){
        textPassword.requestFocus();
    }

    public void toLog(ActionEvent e){
        logInButton.requestFocus();
    }

    public void checkUser(ActionEvent event){
        switch(DBConnector.checkUser(textUsername.getText(),textPassword.getText())){
            case Invalid:
                textUsername.setText("");
                textUsername.setPromptText("Unidentified User");
                textPassword.setText("");
                break;
            case Manager:
                goToManagerHome(event);
                break;
            case Employee:
                goToEmployeeHome(event);
                break;
        }

    }

    private void goToEmployeeHome(ActionEvent event){
        Parent manager_home_parent = null;
        try {
            manager_home_parent = FXMLLoader.load(getClass().getResource("EmployeeHome.fxml"));
        }catch(IOException e){
            e.printStackTrace();
        }
        Scene manager_home_scene = new Scene(manager_home_parent);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(manager_home_scene);
        manager_home_scene.setOnMousePressed(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event){
                xOffset = event.getSceneX();
                yOffset = event.getSceneY();
            }
        });
        manager_home_scene.setOnMouseDragged(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event){
                app_stage.setX(event.getScreenX() - xOffset);
                app_stage.setY(event.getScreenY() - yOffset);
            }
        });
        app_stage.show();
    }



    private void goToManagerHome(ActionEvent event){
        Parent manager_home_parent = null;
        try {
            manager_home_parent = FXMLLoader.load(getClass().getResource("ManagerHome.fxml"));
        }catch(IOException e){
            e.printStackTrace();
        }
        Scene manager_home_scene = new Scene(manager_home_parent);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(manager_home_scene);
        manager_home_scene.setOnMousePressed(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event){
                xOffset = event.getSceneX();
                yOffset = event.getSceneY();
            }
        });
        manager_home_scene.setOnMouseDragged(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event){
                app_stage.setX(event.getScreenX() - xOffset);
                app_stage.setY(event.getScreenY() - yOffset);
            }
        });
        app_stage.show();
      
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
