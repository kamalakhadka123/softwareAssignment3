
package com.mycompany.healthcaremanagementsystem;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.text.Text;

public class ShowBills implements Initializable {


    @FXML
    private Text patientNameLabel;
    @FXML
    private TextArea billArea;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    @FXML
    private void homeBtnEvent(ActionEvent event) {
        App.changeWindow("MedicalStaffDashboard.fxml");
    }

    @FXML
    private void logOutEvent(ActionEvent event) {
        App.changeWindow("loginForUser.fxml");
    }

  

    public TextArea getBillArea() {
        return billArea;
    }

    public Text getPatientNameLabel() {
        return patientNameLabel;
    }
    
    @FXML
    private void printEvent(ActionEvent event) {
        App.getDb().printPatientBill();
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Healthlink Management System");
            alert.setHeaderText("Bill Printed");
            alert.setContentText("Patient Bill has been printed");
            alert.getButtonTypes().setAll(ButtonType.OK);
            alert.showAndWait().ifPresent(response -> {
                if (response == ButtonType.OK) {

                }
            });
    }

}
