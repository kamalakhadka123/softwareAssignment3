
package com.mycompany.healthcaremanagementsystem;

import Model.AppointmentEntity;
import Model.BillingInvoiceEntity;
import Model.PatientEntity;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class ScheduleAppointment implements Initializable {


    @FXML
    private TextField searchField;
    @FXML
    private Text foundUser;
    @FXML
    private DatePicker bookingDateField;
    @FXML
    private Button bookAppointmentBtn;
    @FXML
    private Text error;
    @FXML
    private MenuButton bookingReasonMenu;
    @FXML
    private MenuButton bookingTimeMenu;
    
    private boolean doPatientExist = false;
    @FXML
    private Label bookingForLabel;
    @FXML
    private Label bookingDateLabel;
    @FXML
    private Label bookingTimeLabel;
    

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    @FXML
    private void searchBtn(ActionEvent event) {
        try {
            int patientID = Integer.parseInt(searchField.getText());
            
            PatientEntity p = App.getDb().selectPatient(patientID);
            if(p == null)
            {
              showError(true, "Patient Not Found!");
              foundUser.setVisible(false);
              doPatientExist = false;
            }
            else{
               showError(false, "");
              App.setSearchedPatient(p);
              foundUser.setText("Book for the Patient: "+p.getFirstname()+" "+p.getLastname());
              foundUser.setVisible(true);
              doPatientExist = true;
                beginAppointment(true);

            }
            
        } catch (Exception e) {
            showError(true, "Patient ID Incorrect");
            foundUser.setVisible(false);
            doPatientExist = false;
        }
    }

    @FXML
    private void bookingMenuEvent(ActionEvent event) {
        MenuItem selectedItem = (MenuItem) event.getSource();
        String selected = selectedItem.getText();
        bookingReasonMenu.setText(selected);
    }

    @FXML
    private void bookAppointmentEvent(ActionEvent event) {
        if(doPatientExist)
        {
            String bookFor = bookingReasonMenu.getText();
            java.sql.Date dateBook= null;
            String bookTime = bookingTimeMenu.getText();
            
            if(bookFor.isBlank() || bookFor.equalsIgnoreCase("No Selection"))
            {
                showError(true, "Please Choose a reason for booking!");
            }
            else if(bookingDateField.getValue() == null)
            {
                showError(true, "Booking date can not be blank");
            }
            else if(bookTime.isBlank() || bookTime.equalsIgnoreCase("Select Time"))
            {
                showError(true, "Please Choose Booking Time");
            }
            else{
                showError(false, "");
                dateBook = java.sql.Date.valueOf(bookingDateField.getValue());
                
                AppointmentEntity a = new AppointmentEntity(-1, dateBook, bookFor, bookTime, App.getSearchedPatient().getPatientId());
                
                if(App.getDb().searchAppointment(a))
                {
                    showError(true, "This booking is full please select another.");
                }
                else{
                    showError(false, "");

                    a = App.getDb().addAppointmentData(a);

                    // Display a success alert
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Healthlink Management System");
                    alert.setHeaderText("Book Appointment");
                    alert.setContentText("Appointment has been booked successfully \nBooking Date: "+a.getAppointmentDate()+" "+a.getBookingTime());
                    alert.getButtonTypes().setAll(ButtonType.OK);
                    alert.showAndWait().ifPresent(response -> {
                        if (response == ButtonType.OK) {

                        }
                    });

                    emptyFields();
                    
                    // automatic generate the invoice
                    BillingInvoiceEntity billingInvoice = new BillingInvoiceEntity(-1, dateBook, bookFor, a.getAppointmentID(), a.getPatientID());
                    billingInvoice = App.getDb().addBillingInvoice(billingInvoice);
               }

            }
        }
        else{
            showError(true, "Patient search is required");
        }
    }
    
    public void showError(boolean e,String msg)
    {
        error.setText(msg);
        error.setVisible(e);
    }

    @FXML
    private void bookingTimeEvent(ActionEvent event) {
        MenuItem selectedItem = (MenuItem) event.getSource();
        String selected = selectedItem.getText();
        bookingTimeMenu.setText(selected);
    }
    
        public void emptyFields()
    {
        searchField.setText("");
        foundUser.setVisible(false);
        bookingDateField.setValue(null);
        bookingReasonMenu.setText("No Selection");
        bookingTimeMenu.setText("Select Time");
        beginAppointment(false);
        showError(false, "");
    }
        
    public void beginAppointment(boolean show)
    {
        bookingDateField.setVisible(show);
        bookingReasonMenu.setVisible(show);
        bookingTimeMenu.setVisible(show);
        bookAppointmentBtn.setVisible(show);
        bookingDateLabel.setVisible(show);
        bookingForLabel.setVisible(show);
        bookingTimeLabel.setVisible(show);
    }

    @FXML
    private void homeBtn(ActionEvent event) {
        App.changeWindow("MedicalStaffDashboard.fxml");
    }

    @FXML
    private void logOutBtn(ActionEvent event) {
        App.changeWindow("loginForUser.fxml");
    }


}
