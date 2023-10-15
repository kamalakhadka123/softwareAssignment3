
package com.mycompany.healthcaremanagementsystem;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

/**
 * Controller class for the Medical Staff Dashboard in the Healthcare Management System.
 */
public class MedicalStaffDashboard implements Initializable {

    /**
     * Initializes the controller class.
     *
     * @param url The location used to resolve relative paths for the root object.
     * @param rb The resources used to localize the root object.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    /**
     * Event handler for adding a new patient. Redirects to the "RegisterPatient.fxml" window.
     *
     * @param event The ActionEvent triggered by the user.
     */
    @FXML
    private void addPatientEvent(ActionEvent event) {
        App.changeWindow("RegisterPatient.fxml");
    }

    /**
     * Event handler for updating patient information. Redirects to the "PatientSearch.fxml" window.
     *
     * @param event The ActionEvent triggered by the user.
     */
    @FXML
    private void updatePatientEvent(ActionEvent event) {
        App.changeWindow("PatientSearch.fxml");
    }

    /**
     * Event handler for logging out. Redirects to the "loginForUser.fxml" window.
     *
     * @param event The ActionEvent triggered by the user.
     */
    @FXML
    private void logoutEvent(ActionEvent event) {
        App.changeWindow("loginForUser.fxml");
    }

    /**
     * Event handler for scheduling an appointment. Redirects to the "ScheduleAppointment.fxml" window.
     *
     * @param event The ActionEvent triggered by the user.
     */
    @FXML
    private void scheduleAppointmentEvent(ActionEvent event) {
        App.changeWindow("ScheduleAppointment.fxml");
    }

    /**
     * Event handler for viewing patient bills. Redirects to the "PatientBillSearch.fxml" window.
     *
     * @param event The ActionEvent triggered by the user.
     */
    @FXML
    private void viewBillsEvent(ActionEvent event) {
        App.changeWindow("PatientBillSearch.fxml");
    }



}
