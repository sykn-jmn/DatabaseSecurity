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
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * FXML Controller class
 *
 * @author Al Bee
 */
public class EmployeeHomeController implements Initializable {
    private double xOffset = 0;
    private double yOffset = 0;

    @FXML
    TextField textName;
    @FXML
    private void exit(ActionEvent event) throws IOException{
        System.exit(0);
    }
    
    @FXML
    private void logout(ActionEvent event) throws IOException {
        DBConnector.logOut();
        Parent login_parent = FXMLLoader.load(getClass().getResource("Frtslogin.fxml"));
        Scene login_scene = new Scene(login_parent);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(login_scene);
        login_scene.setOnMousePressed(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event){
                xOffset = event.getSceneX();
                yOffset = event.getSceneY();
            }
        });
        login_scene.setOnMouseDragged(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event){
                app_stage.setX(event.getScreenX() - xOffset);
                app_stage.setY(event.getScreenY() - yOffset);
            }
        });
        app_stage.show();
    }

    @FXML
    private void freport(){
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("FinancialReport.fxml"));
            Parent root1 = (Parent) loader.load();
            Stage stage = new Stage();
            stage.setTitle("Financial Report");
            stage.setResizable(false);
            stage.setScene(new Scene(root1));
            stage.show();
        } catch (IOException e){
            System.out.println("Error");
            e.printStackTrace();
        }   
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        textName.setText(DBConnector.getUserName());
    }    
    
}
