
package com.mycompany.healthcaremanagementsystem;

import Model.UsersEntity;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

/**
 * Controller class for searching and managing users in a healthcare management system.
 */
public class UserSearch implements Initializable {


    @FXML
    private Text error;
    @FXML
    private TextField searchField;
    @FXML
    private Text foundUserText;
    @FXML
    private Button updateBtn;

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
     * Handles the action when the "Logout" button is clicked, changing the window to the user login screen.
     *
     * @param event The event triggered by the button click.
     */
    @FXML
    private void logoutEvent(ActionEvent event) {
        
        App.changeWindow("loginForUser.fxml");
 
    }

     /**
     * Handles the action when the "Search" button is clicked to find a user by ID.
     *
     * @param event The event triggered by the button click.
     */
    @FXML
    private void searchBtnEvent(ActionEvent event) {
        try {
            int userID = Integer.parseInt(searchField.getText());
            
            // if user id is correct
            UsersEntity user = App.getDb().selectUserByID(userID);
            if(user == null)
            {
               displayErrorMessage(true, "User is not found");
               foundUserText.setVisible(false);
              updateBtn.setVisible(false);
            }
            else{
              App.setSearchedUser(user);
              foundUserText.setText("User =>  FirstName: "+user.getFirstName()+" LastName: "+user.getLastName());
              foundUserText.setVisible(true);
              updateBtn.setVisible(true);

            }
            
        } catch (Exception e) {
            displayErrorMessage(true, "UserID is incorrect");
            foundUserText.setVisible(false);
              updateBtn.setVisible(false);
            
        }
    }

    /**
     * Handles the action when the "Update User" button is clicked, changing the window to update user information.
     *
     * @param event The event triggered by the button click.
     * @throws IOException If an I/O error occurs while changing the window.
     */
    @FXML
    private void updateUserEvent(ActionEvent event) throws IOException {
        App.changeWindow("UpdateUser.fxml");
    }

    
        public void displayErrorMessage(boolean e,String msg)
    {
        error.setText(msg);
        error.setVisible(e);
    }

    /**
     * Handles the action when the "Home" button is clicked, changing the window to the administrator dashboard.
     *
     * @param event The event triggered by the button click.
     */
    @FXML
    private void homeBtn(ActionEvent event) {
        App.changeWindow("AdministratorDashboard.fxml");
    }

}
