/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package frateoslogin;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * FXML Controller class
 *
 * @author Al Bee
 */
public class AddEmployeeController implements Initializable {

    @FXML
    private TextField textPicture;

    @FXML
    private TextField textFirst;

    @FXML
    private TextField textMiddle;

    @FXML
    private TextField textLast;

    @FXML
    private TextField textContact;

    @FXML
    private TextField textStreet;

    @FXML
    private TextField textPurok;

    @FXML
    private TextField textBarangay;

    @FXML
    private TextField textCity;

    @FXML
    private TextField textProvince;

    @FXML
    private ChoiceBox choiceRole;


    @FXML
    private void submit(ActionEvent e){
        String first_name = textFirst.getText();
        String middle_name = textMiddle.getText();
        String last_name = textLast.getText();
        String contact = textContact.getText();
        String street = textStreet.getText();
        String purok = textPurok.getText();
        String barangay = textBarangay.getText();
        String city = textCity.getText();
        String province = textProvince.getText();
        String role = (String)choiceRole.getValue();
        if(DBConnector.addEmployee(first_name,
                middle_name,
                last_name,
                contact,
                DBConnector.checkAddress(street, purok, barangay, city, province),
                role)){
            textPicture.setText("Employee Added");
        }else{
            textPicture.setText("Error!");
        }
    }

    @FXML
    private javafx.scene.control.Button closeButton;

    @FXML
    private void cancel(ActionEvent e) throws IOException{
    Stage stage = (Stage) closeButton.getScene().getWindow();
    stage.close();
    }

    /**
     * Initializes the controller class.
     */

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        String[] roles = DBConnector.getRoles();
        for (int i = 0; i < roles.length;i++){
            choiceRole.getItems().add(roles[i]);
        }
    }
}
