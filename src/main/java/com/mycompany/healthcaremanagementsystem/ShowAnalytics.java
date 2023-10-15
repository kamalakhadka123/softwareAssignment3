
package com.mycompany.healthcaremanagementsystem;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.scene.control.TextArea;

/**
 * Controller class for displaying analytics.
 */
public class ShowAnalytics implements Initializable {


    @FXML
    private TextArea analyticsArea;
    
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
     * Handles the action when the "Home" button is clicked, changing the window to the administrator dashboard.
     *
     * @param event The event triggered by the button click.
     */
    @FXML
    private void homeBtnEvent(ActionEvent event) {
        App.changeWindow("AdministratorDashboard.fxml");
    }

    /**
     * Handles the action when the "Log Out" button is clicked, changing the window to the user login screen.
     *
     * @param event The event triggered by the button click.
     */
    @FXML
    private void logOutEvent(ActionEvent event) {
        App.changeWindow("loginForUser.fxml");
    }

    /**
     * Gets the TextArea used for displaying analytics.
     *
     * @return The TextArea for displaying analytics.
     */
    public TextArea getAnalyticsArea() {
        return analyticsArea;
    }
    
    

}
