
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

public class PatientSearch implements Initializable {


    @FXML
    private Text error;
    @FXML
    private TextField searchField;
    @FXML
    private Text foundUserText;
    @FXML
    private Button updateBtn;

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

    @FXML
    private void updateUserEvent(ActionEvent event) {
        App.changeWindow("PatientUpdate.fxml");
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
