
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

public class PatientBillSearch implements Initializable {


    @FXML
    private TextField searchField;
    @FXML
    private Text error;
    @FXML
    private Text foundUserText;
    @FXML
    private Button viewBillBtn;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    @FXML
    private void logoutEvent(ActionEvent event) {
        App.changeWindow("loginForUser.fxml");
    }

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

    @FXML
    private void homeBtn(ActionEvent event) {
        App.changeWindow("MedicalStaffDashboard.fxml");
    }
    
    public void displayErrorMessage(boolean e,String msg)
    {
        error.setText(msg);
        error.setVisible(e);
    }


}
