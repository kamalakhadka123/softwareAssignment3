
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
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    

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

    @FXML
    private void adminHomeEvent(ActionEvent event) {
        
            App.changeWindow("AdministratorDashboard.fxml");
       
    }



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
    
        public TextField getFirstnameField() {
        return firstnameField;
    }

    public TextField getLastnameField() {
        return lastnameField;
    }

    public TextField getEmailField() {
        return emailField;
    }

    public TextField getPasswordField() {
        return passwordField;
    }

    public TextField getConfirmPassField() {
        return confirmPassField;
    }

    public TextField getGenderField() {
        return genderField;
    }

    public TextField getDateOfBirthField() {
        return dateOfBirthField;
    }

    public TextField getRoleField() {
        return roleField;
    }
    
        public void displayErrorMessage(boolean e,String msg)
    {
        error.setText(msg);
        error.setVisible(e);
    }
        
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
