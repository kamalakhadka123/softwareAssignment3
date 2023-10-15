
package com.mycompany.healthcaremanagementsystem;

import Model.UsersEntity;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

/**
 * Controller class for user login.
 */
public class loginForUser implements Initializable {


    @FXML
    private TextField emailField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Text error;

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
     * Handles the action when the "Login" button is clicked, attempting to log in the user.
     *
     * @param event The event triggered by the button click.
     */
    @FXML
    private void loginEvent(ActionEvent event) {
        String userID = emailField.getText();
        String password = passwordField.getText();
        // user login checks 
        if(userID.isBlank())
        {
            displayErrorMessage(true, "UserID can not be blank");
        }
        else if(password.isBlank())
        {
            displayErrorMessage(true, "Password can not be blank");
        }
        else{
            boolean valid = false;
            long uID = -1;
            try {
                uID = Long.parseLong(userID);
                valid = true;
            } catch (Exception e) {
                displayErrorMessage(true, "User ID invalid");
            }
            if(valid)
            {
                UsersEntity u = App.getDb().selectUserByID(uID);
                if(u == null)
                {
                    displayErrorMessage(true, "User not found!");
                }
                else{
                    if(u.getPassword().compareTo(password) == 0)
                    {
                        displayErrorMessage(false, "");
                        if(u.getRole().equalsIgnoreCase("admin"))
                        {
                           
                                App.changeWindow("AdministratorDashboard.fxml");
                                App.setLoggedInUser(u);
                        
                        }
                        else if(u.getRole().equalsIgnoreCase("medical staff"))
                        {
                            App.changeWindow("MedicalStaffDashboard.fxml");
                            App.setLoggedInUser(u);
                        }

                    }
                    else{
                        displayErrorMessage(true, "Password is Incorrect");
                    }
                }   
            }   
        }
        
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

}
