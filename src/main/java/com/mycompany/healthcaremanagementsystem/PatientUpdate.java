
package com.mycompany.healthcaremanagementsystem;

import Model.PatientEntity;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class PatientUpdate implements Initializable {


    @FXML
    private MenuButton genderMenu;
    @FXML
    private TextField firstnameField;
    @FXML
    private TextField lastnameField;
    @FXML
    private TextField addressField;
    @FXML
    private TextField contactNumberField;
    @FXML
    private DatePicker dateOfBirthField;
    @FXML
    private TextField medicareCardField;
    @FXML
    private Text error;
    @FXML
    private TextArea medicalHistoryField;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    @FXML
    private void genderMenuEvent(ActionEvent event) {
        MenuItem selectedItem = (MenuItem) event.getSource();
        String selected = selectedItem.getText();
        genderMenu.setText(selected);
    }

    @FXML
    private void updatePatientBtn(ActionEvent event) {
            String firstName = firstnameField.getText();
            String lastName = lastnameField.getText();
            String gender = genderMenu.getText();
            java.sql.Date dob= null;
            String address = addressField.getText();
            String contactNumber = contactNumberField.getText();
            String medicareCard = medicareCardField.getText();
            String medicalhistory = medicalHistoryField.getText();
            
        if(firstName.isBlank())
        {
            displayErrorMessage(true, "Firstname can not be blank");
        }
        else if(lastName.isBlank())
        {
            displayErrorMessage(true, "LastName can not be blank");
        }
        else if(gender.isBlank() || gender.compareTo("Select Gender") == 0)
        {
            displayErrorMessage(true, "Please select a gender");
        }
        else if(dateOfBirthField.getValue() == null)
        {
            displayErrorMessage(true, "Please select date of birth");
        }
        else if(address.isBlank())
        {
            displayErrorMessage(true, "Address can not be blank");
        }
        else if(contactNumber.isBlank())
        {
            displayErrorMessage(true, "Contactnumber can not be blank");
        }
        else if(medicareCard.isBlank())
        {
            displayErrorMessage(true, "Medicare Card Number can not be blank");
        }
        else if(medicalhistory.isBlank())
        {
            displayErrorMessage(true, "Medical History can not be blank");
        }
        else{
             displayErrorMessage(false, "");
             dob = java.sql.Date.valueOf(dateOfBirthField.getValue());
             
             PatientEntity patient = new PatientEntity(App.getSearchedPatient().getPatientId(), firstName, lastName, gender, dob, address, contactNumber, medicareCard, medicalhistory);
             App.getDb().modifyPatient(patient);
             

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Healthlink Management System");
            alert.setHeaderText("Update Patient Success");
            alert.setContentText("A New Patient has been Updated");
            alert.getButtonTypes().setAll(ButtonType.OK);
            alert.showAndWait().ifPresent(response -> {
                if (response == ButtonType.OK) {

                }
            });

        }
    }

    @FXML
    private void homeBtn(ActionEvent event) {
        App.changeWindow("MedicalStaffDashboard.fxml");
    }

    @FXML
    private void logoutBtn(ActionEvent event) {
        App.changeWindow("loginForUser.fxml");
    }

    public MenuButton getGenderMenu() {
        return genderMenu;
    }

    public TextField getFirstnameField() {
        return firstnameField;
    }

    public TextField getLastnameField() {
        return lastnameField;
    }

    public TextField getAddressField() {
        return addressField;
    }

    public TextField getContactNumberField() {
        return contactNumberField;
    }

    public DatePicker getDateOfBirthField() {
        return dateOfBirthField;
    }

    public TextField getMedicareCardField() {
        return medicareCardField;
    }

    public Text getError() {
        return error;
    }

    public TextArea getMedicalHistoryField() {
        return medicalHistoryField;
    }
    
    public void displayErrorMessage(boolean e,String msg)
    {
        error.setText(msg);
        error.setVisible(e);
    }

}
