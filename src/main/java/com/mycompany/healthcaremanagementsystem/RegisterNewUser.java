
package com.mycompany.healthcaremanagementsystem;

import Model.UsersEntity;
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
import javafx.scene.control.TextField;
import javafx.scene.text.Text;


public class RegisterNewUser implements Initializable {

    @FXML
    private MenuButton roleMenu;
    @FXML
    private MenuButton genderMenu;
    @FXML
    private TextField firstnameField;
    @FXML
    private TextField lastnameField;
    @FXML
    private TextField emailField;
    @FXML
    private TextField passwordField;
    @FXML
    private DatePicker dateOfBirthField;
    @FXML
    private TextField confirmPassField;
    @FXML
    private Text error;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    

    /**
     * Handles the action when the "Create User" button is clicked.
     *
     * @param event The ActionEvent triggering this method.
     */

    @FXML
    private void createUserBtn(ActionEvent event) {
        String firstName = firstnameField.getText();
        String lastName = lastnameField.getText();
        String gender = genderMenu.getText();
        java.sql.Date dob= null;
        String email = emailField.getText();
        String password = passwordField.getText();
        String confPass = confirmPassField.getText();
        String role = roleMenu.getText();
        
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
        else if(email.isBlank())
        {
            displayErrorMessage(true, "Email Address can not be blank");
        }
        else if(password.isBlank())
        {
            displayErrorMessage(true, "Password can not be blank");
        }
        else if(confPass.isBlank())
        {
            displayErrorMessage(true, "Confirm Password can not be blank");
        }
        else if(role.isBlank() || role.compareTo("Select Role") == 0)
        {
            displayErrorMessage(true, "Please select a role");
        }
        else if(!password.equalsIgnoreCase(confPass))
        {
            displayErrorMessage(true, "Passwords are not matching");
        }
        else{
            
            displayErrorMessage(false, "");
            dob = java.sql.Date.valueOf(dateOfBirthField.getValue());
            UsersEntity user = App.getDb().addUser(new UsersEntity(-1, firstName, lastName, gender, dob, email, password, role));
            

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Healthlink Management System");
            alert.setHeaderText("Add New User Success");
            alert.setContentText("A New User has been Added successfully\n\nUserID: "+user.getId());
            alert.getButtonTypes().setAll(ButtonType.OK);
            alert.showAndWait().ifPresent(response -> {
                if (response == ButtonType.OK) {

                }
            });
            
            emptyAllFields();
        }
    }

    /**
     * Handles the selection of gender from the MenuButton.
     *
     * @param event The ActionEvent triggering this method.
     */
    @FXML
    private void genderMenuEvent(ActionEvent event) {
        MenuItem selectedItem = (MenuItem) event.getSource();
        String selected = selectedItem.getText();
        genderMenu.setText(selected);
    }

    /**
     * Handles the selection of user role from the MenuButton.
     *
     * @param event The ActionEvent triggering this method.
     */
    @FXML
    private void roleMenuEvent(ActionEvent event) {
                MenuItem selectedItem = (MenuItem) event.getSource();
        String selected = selectedItem.getText();
        roleMenu.setText(selected);
    }

    /**
     * Displays an error message or clears the error message text.
     *
     * @param e   A boolean indicating whether to display an error message.
     * @param msg The error message text.
     */
            
    public void displayErrorMessage(boolean e,String msg)
    {
        error.setText(msg);
        error.setVisible(e);
    }

    /**
     * Handles the "Home" button click event.
     *
     * @param event The ActionEvent triggering this method.
     */
    @FXML
    private void homeBtn(ActionEvent event) {
        App.changeWindow("AdministratorDashboard.fxml");
    }

    /**
     * Handles the "Logout" button click event.
     *
     * @param event The ActionEvent triggering this method.
     */
    @FXML
    private void logoutBtn(ActionEvent event) {
        App.changeWindow("loginForUser.fxml");
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
        emailField.setText("");
        passwordField.setText("");
        confirmPassField.setText("");
        roleMenu.setText("Select Role");
    }

}
