
package com.mycompany.healthcaremanagementsystem;

import Model.PatientEntity;
import Model.UsersEntity;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

/**
 * Controller class for searching and updating patient information in the Healthcare Management System.
 */
public class PatientSearch implements Initializable {


    @FXML
    private Text error;
    @FXML
    private TextField searchField;
    @FXML
    private Text foundUserText;
    @FXML
    private Button updateBtn;

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
     * Event handler for logging out. Redirects to the "loginForUser.fxml" window.
     *
     * @param event The ActionEvent triggered by the user.
     */
    @FXML
    private void logoutEvent(ActionEvent event) {
        App.changeWindow("loginForUser.fxml");
    }

    /**
     * Event handler for searching for a patient. Displays patient information if found.
     *
     * @param event The ActionEvent triggered by the user.
     */
    @FXML
    private void searchBtnEvent(ActionEvent event) {
        try {
            int patientID = Integer.parseInt(searchField.getText());
            
            PatientEntity patient = App.getDb().selectPatient(patientID);
            if(patient == null)
            {
                displayErrorMessage(true, "Patient is not found");
              foundUserText.setVisible(false);
              updateBtn.setVisible(false);
            }
            else{
                displayErrorMessage(false, "");
              App.setSearchedPatient(patient);
              foundUserText.setText("Patient=> FirstName: "+patient.getFirstname()+" LastName: "+patient.getLastname());
              foundUserText.setVisible(true);
              updateBtn.setVisible(true);

            }
            
        } catch (Exception e) {
            displayErrorMessage(true, "PatientID is Incorrect");
            foundUserText.setVisible(false);
              updateBtn.setVisible(false);
            
        }
    }

    /**
     * Event handler for updating patient information. Redirects to the "PatientUpdate.fxml" window.
     *
     * @param event The ActionEvent triggered by the user.
     */
    @FXML
    private void updateUserEvent(ActionEvent event) {
        App.changeWindow("PatientUpdate.fxml");
    }

    /**
     * Event handler for returning to the Medical Staff Dashboard.
     *
     * @param event The ActionEvent triggered by the user.
     */
    @FXML
    private void homeBtn(ActionEvent event) {
        App.changeWindow("MedicalStaffDashboard.fxml");
    }

    /**
     * Display an error message or clear it.
     *
     * @param e   A boolean indicating whether to display the error message.
     * @param msg The error message to display.
     */
    public void displayErrorMessage(boolean e,String msg)
    {
        error.setText(msg);
        error.setVisible(e);
    }

}
