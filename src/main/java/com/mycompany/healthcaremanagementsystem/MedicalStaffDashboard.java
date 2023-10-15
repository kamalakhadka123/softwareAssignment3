
package com.mycompany.healthcaremanagementsystem;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

public class MedicalStaffDashboard implements Initializable {

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    @FXML
    private void addPatientEvent(ActionEvent event) {
        App.changeWindow("RegisterPatient.fxml");
    }

    @FXML
    private void updatePatientEvent(ActionEvent event) {
        App.changeWindow("PatientSearch.fxml");
    }

    @FXML
    private void logoutEvent(ActionEvent event) {
        App.changeWindow("loginForUser.fxml");
    }

    @FXML
    private void scheduleAppointmentEvent(ActionEvent event) {
        App.changeWindow("ScheduleAppointment.fxml");
    }

    @FXML
    private void viewBillsEvent(ActionEvent event) {
        App.changeWindow("PatientBillSearch.fxml");
    }



}
