/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package frateoslogin;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * FXML Controller class
 *
 * @author Al Bee
 */
public class ViewEmployeeController implements Initializable{

    /**
     * Initializes the controller class.
     */

    @FXML
    TableColumn<Employee, Integer> id;
    @FXML
    TableColumn<Employee, String> fname;
    @FXML
    TableColumn<Employee, String> mname;
    @FXML
    TableColumn<Employee, String> lname;
    @FXML
    TableColumn<Employee, String> role;
    @FXML
    TableColumn<Employee, String> phone;
    @FXML
    TableColumn<Employee, String> address;
    @FXML
    TableView<Employee> table;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        fname.setCellValueFactory(new PropertyValueFactory<>("first_name"));
        mname.setCellValueFactory(new PropertyValueFactory<>("middle_name"));
        lname.setCellValueFactory(new PropertyValueFactory<>("last_name"));
        role.setCellValueFactory(new PropertyValueFactory<>("role"));
        phone.setCellValueFactory(new PropertyValueFactory<>("phone"));
        address.setCellValueFactory(new PropertyValueFactory<>("address"));
        Employee[] employees = DBConnector.getEmployees();
        for(Employee e:employees) {
            table.getItems().add(e);
        }
        table.setSelectionModel(null);
    }
}
