
package com.mycompany.healthcaremanagementsystem;

import Model.UsersEntity;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

/**
 * Controller class for updating user information.
 */
public class UpdateUser implements Initializable {


    @FXML
    private TextField firstnameField;
    @FXML
    private TextField lastnameField;
    @FXML
    private TextField emailField;
    @FXML
    private TextField passwordField;
    @FXML
    private TextField confirmPassField;
    @FXML
    private Text error;
    @FXML
    private TextField genderField;
    @FXML
    private TextField dateOfBirthField;
    @FXML
    private TextField roleField;

    /**
     * Initializes the controller class.
     *
     * @param url The location to resolve relative paths for the root object, or null if the location is not known.
     * @param rb The resources used to localize the root object, or null if the root object was not localized.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    /**
     * Handles the action when the "Delete User" button is clicked, deleting the user.
     *
     * @param event The event triggered by the button click.
     */
    @FXML
    private void deleteUserEvent(ActionEvent event) {
        

        if(App.getLoggedInUser().getId() == App.getSearchedUser().getId())
        {
             showMessageBox("Failed to Delete", "Logged in user can not be deleted");
        }
        else{
            App.getDb().deleteUser(App.getSearchedUser().getId());
            showMessageBox("Delete User Success", "The User has been deleted Successfully");
            App.changeWindow("UserSearch.fxml");
        }
    }

    /**
     * Handles the action when the "Home" button is clicked, changing the window to the administrator dashboard.
     *
     * @param event The event triggered by the button click.
     */
    @FXML
    private void adminHomeEvent(ActionEvent event) {
        
            App.changeWindow("AdministratorDashboard.fxml");
       
    }


    /**
     * Handles the action when the "Update User" button is clicked, updating user information.
     *
     * @param event The event triggered by the button click.
     */
    @FXML
    private void updateUserBtn(ActionEvent event) {
        String firstName = firstnameField.getText();
        String lastName = lastnameField.getText();
        String gender = genderField.getText();
        String dateOfBirth= dateOfBirthField.getText();
            String email = emailField.getText();
            String password = passwordField.getText();
            String confPass = confirmPassField.getText();
            String role = roleField.getText();
        if(firstName.isBlank())
        {
            displayErrorMessage(true, "FirstName can not be blank");
        }
        else if(lastName.isBlank())
        {
            displayErrorMessage(true, "LastName can not be blank");
        }
        else if(gender.isBlank())
        {
            displayErrorMessage(true, "Gender can not be blank");
        }
        else if(dateOfBirth.isBlank())
        {
            displayErrorMessage(true, "Date of birth can not be blank");
        }
        else if(email.isBlank())
        {
            displayErrorMessage(true, "Email can not be blank");
        }
        else if(password.isBlank())
        {
            displayErrorMessage(true, "Password can not be blank");
        }
        else if(confPass.isBlank())
        {
            displayErrorMessage(true, "Confirm Password can not be blank");
        }
        else if(role.isBlank())
        {
            displayErrorMessage(true, "Role can not be blank");
        }
        else if(password.compareTo(confPass) != 0)
        {
            displayErrorMessage(true, "Passwords are not matching");
        }
        else{
            boolean isDateCorrect = false;
            java.sql.Date dobInSQL = null;
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            try {
                  java.util.Date utilDate = dateFormat.parse(dateOfBirth);
                        dobInSQL = new java.sql.Date(utilDate.getTime());
                        isDateCorrect = true;
            } catch (Exception e) {
                       displayErrorMessage(true, "Date Of Birth format Incorrect => 'YYYY-MM-DD' is required");
            }
            if(isDateCorrect)
            {
                if(!role.equalsIgnoreCase("admin") && !role.equalsIgnoreCase("medical staff"))
                {
                    displayErrorMessage(true, "Role can be either Admin or Medical Staff");
                }
                else{
                    UsersEntity u = new UsersEntity(App.getSearchedUser().getId(), firstName, lastName, gender, dobInSQL, email, password, role);
                    u = App.getDb().modifyUserByID(u);
                    if(u == null)
                    {
                        showMessageBox("Failed", "Failed to Update the User");
                    }
                    else{
                        showMessageBox("Success", "The User FirstName: "+u.getFirstName()+" LastName: "+u.getLastName()+" has been updated");
                    }
                }      
            }
        }
        
    }
        /**
         * Gets the value in the firstname field.
         *
         * @return The TextField for the first name.
         */
    public TextField getFirstnameField() {
        return firstnameField;
    }

    /**
     * Gets the value in the lastname field.
     *
     * @return The TextField for the last name.
     */
    public TextField getLastnameField() {
        return lastnameField;
    }

    
    /**
     * Gets the value in the email field.
     *
     * @return The TextField for the email.
     */
    public TextField getEmailField() {
        return emailField;
    }

    /**
     * Gets the value in the password field.
     *
     * @return The TextField for the password.
     */
    public TextField getPasswordField() {
        return passwordField;
    }

    /**
     * Gets the value in the confirm password field.
     *
     * @return The TextField for the confirm password.
     */
    public TextField getConfirmPassField() {
        return confirmPassField;
    }

    /**
     * Gets the value in the gender field.
     *
     * @return The TextField for the gender.
     */
    public TextField getGenderField() {
        return genderField;
    }
    

    
    /**
     * Gets the value in the date of birth field.
     *
     * @return The TextField for the date of birth.
     */
    public TextField getDateOfBirthField() {
        return dateOfBirthField;
    }

    /**
     * Gets the value in the role field.
     *
     * @return The TextField for the user role.
     */
    public TextField getRoleField() {
        return roleField;
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
     * Displays an alert message box with the specified header and content.
     *
     * @param header  The header for the alert message.
     * @param content The content of the alert message.
     */
    public void showMessageBox(String header, String content)
    {
            // Display a success alert
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Health Care Management System");
            alert.setHeaderText(header);
            alert.setContentText(content);
            alert.getButtonTypes().setAll(ButtonType.OK);
            alert.showAndWait().ifPresent(response -> {
                if (response == ButtonType.OK) {

                }
            });
    }
    


    
    

}
