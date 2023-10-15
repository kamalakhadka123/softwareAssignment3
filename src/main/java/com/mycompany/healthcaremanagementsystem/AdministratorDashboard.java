
package com.mycompany.healthcaremanagementsystem;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

/**
 * Controller class for the Administrator Dashboard window.
 */
public class AdministratorDashboard implements Initializable {


    /**
     * Initializes the controller class.
     *
     * @param url       The location used to resolve relative paths for the root object, or null if the location is not known.
     * @param rb        The resources used to localize the root object, or null if the root object was not localized.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Initialization code can be added here.
    }    

    /**
     * Event handler for creating a new user.
     *
     * @param event The ActionEvent triggered by the button click.
     * @throws IOException If an I/O exception occurs while changing the window.
     */
    @FXML
    private void createNewUserEvent(ActionEvent event) throws IOException {
        App.changeWindow("RegisterNewUser.fxml");
    }

    /**
     * Event handler for modifying a user.
     *
     * @param event The ActionEvent triggered by the button click.
     * @throws IOException If an I/O exception occurs while changing the window.
     */
    @FXML
    private void modifyUserEvent(ActionEvent event) throws IOException {
        App.changeWindow("UserSearch.fxml");
    }

    /**
     * Event handler for logging out.
     *
     * @param event The ActionEvent triggered by the button click.
     */
    @FXML
    private void logoutEvent(ActionEvent event) {
     
            App.changeWindow("loginForUser.fxml");
        
    }
    
    /**
     * Event handler for viewing analytics.
     *
     * @param event The ActionEvent triggered by the button click.
     */
     @FXML
    private void viewAnalyticsEvent(ActionEvent event) {
        App.changeWindow("ShowAnalytics.fxml");
    }


}
