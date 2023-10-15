
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

public class UserSearch implements Initializable {


    @FXML
    private Text error;
    @FXML
    private TextField searchField;
    @FXML
    private Text foundUserText;
    @FXML
    private Button updateBtn;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    @FXML
    private void logoutEvent(ActionEvent event) {
        
        App.changeWindow("loginForUser.fxml");
 
    }

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

    @FXML
    private void updateUserEvent(ActionEvent event) throws IOException {
        App.changeWindow("UpdateUser.fxml");
    }

    
        public void displayErrorMessage(boolean e,String msg)
    {
        error.setText(msg);
        error.setVisible(e);
    }

    @FXML
    private void homeBtn(ActionEvent event) {
        App.changeWindow("AdministratorDashboard.fxml");
    }

}
