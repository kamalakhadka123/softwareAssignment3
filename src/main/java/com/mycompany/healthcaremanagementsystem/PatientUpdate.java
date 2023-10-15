
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
 * Controller class for updating patient information in the Healthcare Management System.
 */
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
     * Event handler for selecting a gender from the menu.
     *
     * @param event The ActionEvent triggered by the user.
     */
    @FXML
    private void genderMenuEvent(ActionEvent event) {
        MenuItem selectedItem = (MenuItem) event.getSource();
        String selected = selectedItem.getText();
        genderMenu.setText(selected);
    }

    /**
     * Event handler for updating patient information.
     *
     * @param event The ActionEvent triggered by the user.
     */
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
     * Event handler for logging out. Redirects to the "loginForUser.fxml" window.
     *
     * @param event The ActionEvent triggered by the user.
     */
    @FXML
    private void logoutBtn(ActionEvent event) {
        App.changeWindow("loginForUser.fxml");
    }

    /**
     * Gets the MenuButton for selecting a patient's gender.
     *
     * @return The MenuButton for gender selection.
     */
    public MenuButton getGenderMenu() {
        return genderMenu;
    }

    /**
     * Gets the TextField for entering the patient's first name.
     *
     * @return The TextField for the first name.
     */
    public TextField getFirstnameField() {
        return firstnameField;
    }

    /**
     * Gets the TextField for entering the patient's last name.
     *
     * @return The TextField for the last name.
     */
    public TextField getLastnameField() {
        return lastnameField;
    }

    /**
     * Gets the TextField for entering the patient's address.
     *
     * @return The TextField for the address.
     */
    public TextField getAddressField() {
        return addressField;
    }

    /**
     * Gets the TextField for entering the patient's contact number.
     *
     * @return The TextField for the contact number.
     */
    public TextField getContactNumberField() {
        return contactNumberField;
    }

    /**
     * Gets the DatePicker for selecting the patient's date of birth.
     *
     * @return The DatePicker for date of birth selection.
     */
    public DatePicker getDateOfBirthField() {
        return dateOfBirthField;
    }

    /**
     * Gets the TextField for entering the patient's Medicare card number.
     *
     * @return The TextField for the Medicare card number.
     */
    public TextField getMedicareCardField() {
        return medicareCardField;
    }

    /**
     * Gets the Text element for displaying error messages.
     *
     * @return The Text element for error messages.
     */
    public Text getError() {
        return error;
    }

    /**
     * Gets the TextArea for entering the patient's medical history.
     *
     * @return The TextArea for medical history input.
     */
    public TextArea getMedicalHistoryField() {
        return medicalHistoryField;
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
