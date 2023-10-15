
package com.mycompany.healthcaremanagementsystem;

import Model.BillingInvoiceEntity;
import Model.PatientEntity;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

/**
 * Controller class for searching and viewing patient bills in the Healthcare Management System.
 */
public class PatientBillSearch implements Initializable {


    @FXML
    private TextField searchField;
    @FXML
    private Text error;
    @FXML
    private Text foundUserText;
    @FXML
    private Button viewBillBtn;

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
     * Event handler for searching for a patient. Displays information about the patient if found.
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
              viewBillBtn.setVisible(false);

            }
            else{
              displayErrorMessage(false, "");
              App.setSearchedPatient(patient);
              foundUserText.setText("Patient=> FirstName: "+patient.getFirstname()+" LastName: "+patient.getLastname());
              foundUserText.setVisible(true);
              viewBillBtn.setVisible(true);

            }
            
        } catch (Exception e) {
            displayErrorMessage(true, "Patient ID Incorrect");
            foundUserText.setVisible(false);
            viewBillBtn.setVisible(false);

        }
        
    }

    /**
     * Event handler for viewing a patient's bills. Redirects to the "ShowBills.fxml" window.
     *
     * @param event The ActionEvent triggered by the user.
     */
    @FXML
    private void viewBillEvent(ActionEvent event) {
        
        List<BillingInvoiceEntity> li = App.getDb().getAllPatientInvoices(App.getSearchedPatient());
        if(li.size() == 0)
        {
            displayErrorMessage(true, "No bills found for the patient");
        }
        else{
            
        App.changeWindow("ShowBills.fxml");
        }
        
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
