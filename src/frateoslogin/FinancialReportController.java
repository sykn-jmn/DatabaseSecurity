/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package frateoslogin;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * FXML Controller class
 *
 * @author Al Bee
 */
public class FinancialReportController implements Initializable {

    @FXML
    private TableColumn <Absent, Integer> absentDay;
    @FXML
    private TableColumn <Absent, String> absentMonth;
    @FXML private TableColumn <Absent, Integer> absentYear;
    @FXML private TableColumn <CashAdvance, Integer> cashDay;
    @FXML private TableColumn <CashAdvance, String> cashMonth;
    @FXML private TableColumn <CashAdvance, Integer> cashYear;
    @FXML private TableColumn <CashAdvance, Double> cashAmount;
    @FXML private TableColumn <Overtime, Integer> overDay;
    @FXML private TableColumn <Overtime, String> overMonth;
    @FXML private TableColumn <Overtime, Integer>overYear;
    @FXML private TableColumn <Overtime, Integer> overHours;


    @FXML private TableView absentTable, cashTable, hoursTable;

    @FXML
    private Label textMonth, textYear, textAbsent, textCash, textHours, textSalary;

    @FXML
    private javafx.scene.control.Button closeButton;

    @FXML
    private void confirm(ActionEvent e) throws IOException {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Absent[] absences = DBConnector.getAbsences();
        absentDay.setCellValueFactory(new PropertyValueFactory<>("day"));
        absentMonth.setCellValueFactory(new PropertyValueFactory<>("month"));
        absentYear.setCellValueFactory(new PropertyValueFactory<>("year"));

        CashAdvance[] cashAdvances = DBConnector.getCashAdvances();
        cashDay.setCellValueFactory(new PropertyValueFactory<>("day"));
        cashMonth.setCellValueFactory(new PropertyValueFactory<>("month"));
        cashYear.setCellValueFactory(new PropertyValueFactory<>("year"));
        cashAmount.setCellValueFactory(new PropertyValueFactory<>("amount"));

        Overtime[] overtimes = DBConnector.getOvertimes();
        overDay.setCellValueFactory(new PropertyValueFactory<>("day"));
        overMonth.setCellValueFactory(new PropertyValueFactory<>("month"));
        overYear.setCellValueFactory(new PropertyValueFactory<>("year"));
        overHours.setCellValueFactory(new PropertyValueFactory<>("hours"));


        if(absences!=null) {
            for (Absent absent : absences) {
                absentTable.getItems().add(absent);
            }
            absentTable.setSelectionModel(null);
        }

        if(cashAdvances!=null) {
            for (CashAdvance cashAdvance : cashAdvances) {
                cashTable.getItems().add(cashAdvance);
            }
            cashTable.setSelectionModel(null);
        }

        if(overtimes!=null) {
            for (Overtime overtime : overtimes) {
                hoursTable.getItems().add(overtime);
            }
            hoursTable.setSelectionModel(null);
        }

        EmployeeFinance finance = DBConnector.getFinancialValues();
        textMonth.setText(finance.getMonth());
        textYear.setText(""+finance.getYear());
        textAbsent.setText(""+finance.getAbsences());
        textCash.setText(""+finance.getCashAdvance());
        textSalary.setText(""+finance.getSalary());
        textHours.setText(""+finance.getHours());

        /**
         * id.setCellValueFactory(new PropertyValueFactory<>("id"));
         *         fname.setCellValueFactory(new PropertyValueFactory<>("first_name"));
         *         mname.setCellValueFactory(new PropertyValueFactory<>("middle_name"));
         *         lname.setCellValueFactory(new PropertyValueFactory<>("last_name"));
         *         role.setCellValueFactory(new PropertyValueFactory<>("role"));
         *         phone.setCellValueFactory(new PropertyValueFactory<>("phone"));
         *         address.setCellValueFactory(new PropertyValueFactory<>("address"));
         *         Employee[] employees = DBConnector.getEmployees();
         *         for(Employee e:employees) {
         *             table.getItems().add(e);
         *         }
         *         table.setSelectionModel(null);
         *
         *
         */
    }
}
