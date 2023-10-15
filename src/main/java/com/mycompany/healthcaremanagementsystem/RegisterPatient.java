
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

/**
 * Controller class for registering a new patient in the system.
 */
public class RegisterPatient implements Initializable {


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

    /**
     * Initializes the controller class.
     *
     * @param url        The location to resolve relative paths for the root object, or null if the location is not known.
     * @param rb         The resources used to localize the root object, or null if the root object was not localized.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    @FXML
    private void genderMenuEvent(ActionEvent event) {
        MenuItem si = (MenuItem) event.getSource();
        String s = si.getText();
        genderMenu.setText(s);
    }

    @FXML
    private void addPatientBtn(ActionEvent event) {
            String fname = firstnameField.getText();
            String lname = lastnameField.getText();
            String gender = genderMenu.getText();
            java.sql.Date dateOfBirth= null;
            String address = addressField.getText();
            String contactNumber = contactNumberField.getText();
            String medicareCard = medicareCardField.getText();
            String medicalhistory = medicalHistoryField.getText();
            
        if(fname.isBlank())
        {
            displayErrorMessage(true, "Firstname can not be blank");
        }
        else if(lname.isBlank())
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
             dateOfBirth = java.sql.Date.valueOf(dateOfBirthField.getValue());
             
             PatientEntity p = new PatientEntity(-1, fname, lname, gender, dateOfBirth, address, contactNumber, medicareCard, medicalhistory);
             p = App.getDb().addPatient(p);
             
            
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("HealthLink Management System");
            alert.setHeaderText("ADD PATIENT SUCCESS");
            alert.setContentText("A New Patient has been Added successfully\n\nNewly added patient's PatientID: "+p.getPatientId());
            alert.getButtonTypes().setAll(ButtonType.OK);
            alert.showAndWait().ifPresent(response -> {
                if (response == ButtonType.OK) {

                }
            });
            
            emptyAllFields();
        }
    }

    /**
     * Handles the action when the "Home" button is clicked, changing the window to the medical staff dashboard.
     *
     * @param event The event triggered by the button click.
     */
    @FXML
    private void homeBtn(ActionEvent event) {
        App.changeWindow("MedicalStaffDashboard.fxml");
    }

    /**
     * Handles the action when the "Logout" button is clicked, changing the window to the login screen for users.
     *
     * @param event The event triggered by the button click.
     */
    @FXML
    private void logoutBtn(ActionEvent event) {
        App.changeWindow("loginForUser.fxml");
    }

    /**
     * Displays an error message with the specified text.
     *
     * @param e   Indicates whether to display the error message (true) or hide it (false).
     * @param msg The error message to display.
     */
    public void displayErrorMessage(boolean e,String msg)
    {
        error.setText(msg);
        error.setVisible(e);
    }

    /**
     * Clears all input fields on the form.
     */
    public void emptyAllFields()
    {
        firstnameField.setText("");
        lastnameField.setText("");
        genderMenu.setText("Select Gender");
        dateOfBirthField.setValue(null);
        addressField.setText("");
        contactNumberField.setText("");
        medicareCardField.setText("");
        medicalHistoryField.setText("");
    }


}
