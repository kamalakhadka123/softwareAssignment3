
package com.mycompany.healthcaremanagementsystem;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextArea;
import javafx.scene.text.Text;

/**
 * Controller class for displaying and printing bills in a healthcare management system.
 */
public class ShowBills implements Initializable {


    @FXML
    private Text patientNameLabel;
    @FXML
    private TextArea billArea;

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
     * Handles the action when the "Home" button is clicked, changing the window to the medical staff dashboard.
     *
     * @param event The event triggered by the button click.
     */
    @FXML
    private void homeBtnEvent(ActionEvent event) {
        App.changeWindow("MedicalStaffDashboard.fxml");
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
     * Gets the TextArea used for displaying the bill.
     *
     * @return The TextArea for displaying the bill.
     */
    public TextArea getBillArea() {
        return billArea;
    }

    /**
     * Gets the Text label used for displaying the patient's name.
     *
     * @return The Text label for displaying the patient's name.
     */
    public Text getPatientNameLabel() {
        return patientNameLabel;
    }
    

    /**
     * Handles the action when the "Print" button is clicked, printing the patient's bill.
     *
     * @param event The event triggered by the button click.
     */
    @FXML
    private void printEvent(ActionEvent event) {
        App.getDb().printPatientBill();
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Healthlink Management System");
            alert.setHeaderText("Bill Printed");
            alert.setContentText("Patient Bill has been printed");
            alert.getButtonTypes().setAll(ButtonType.OK);
            alert.showAndWait().ifPresent(response -> {
                if (response == ButtonType.OK) {

                }
            });
    }
    
    

}
