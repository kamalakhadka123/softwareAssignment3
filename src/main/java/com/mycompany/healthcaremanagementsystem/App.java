package com.mycompany.healthcaremanagementsystem;

import Model.MySQLDatabaseConnection;
import Model.PatientEntity;
import Model.UsersEntity;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class App extends Application {

    private static Stage mainAppStage; 
    private static Scene currentDisplayStage; 
    private static MySQLDatabaseConnection mysqlDB;
    private static UsersEntity loggedInUser;
    private static UsersEntity searchedUser;
    private static PatientEntity searchedPatient;
    public static boolean databaseCreated = false;
    private static final String DBUSER = "root"; // need to change as per your mysql username
    private static final String DBPASSWORD = "pass2580!"; //// need to change as per your mysql password

    @Override
    public void start(Stage stage) throws IOException {
        mainAppStage = stage;
        mainAppStage.setTitle("HealthLink Management System");
        changeWindow("loginForUser.fxml");
        mainAppStage.show();
    }
    
    public static void changeWindow(String fxmlFileName) {
        try {
            FXMLLoader loader = new FXMLLoader(App.class.getResource(fxmlFileName));
            Parent root = loader.load();

            if (!databaseCreated) {
                mysqlDB = new MySQLDatabaseConnection(DBUSER, DBPASSWORD);
                databaseCreated = true;
            }

            if(fxmlFileName.equalsIgnoreCase("UpdateUser.fxml"))
            {
                UpdateUser uu = (UpdateUser) loader.getController();
                getDb().updateUserViewSet(uu, getSearchedUser());
            }
            if(fxmlFileName.equalsIgnoreCase("PatientUpdate.fxml"))
            {
                PatientUpdate pu = (PatientUpdate) loader.getController();
                getDb().updatePatientViewSet(pu, getSearchedPatient());
            }
            if(fxmlFileName.equalsIgnoreCase("ShowBills.fxml"))
            {
                ShowBills sb = (ShowBills) loader.getController();
                getDb().setBillView(sb, getSearchedPatient());
            }
            if(fxmlFileName.equalsIgnoreCase("ShowAnalytics.fxml")){
                ShowAnalytics sha = (ShowAnalytics) loader.getController();
                getDb().setAnalyticsView(sha);
            }
            
            currentDisplayStage = new Scene(root);
            mainAppStage.setScene(currentDisplayStage);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch();
    }

    public static MySQLDatabaseConnection getDb() {
        return mysqlDB;
    }

    public static UsersEntity getLoggedInUser() {
        return loggedInUser;
    }

    public static void setLoggedInUser(UsersEntity loggedInUser) {
        App.loggedInUser = loggedInUser;
    }

    public static UsersEntity getSearchedUser() {
        return searchedUser;
    }

    public static void setSearchedUser(UsersEntity searchedUser) {
        App.searchedUser = searchedUser;
    }

    public static PatientEntity getSearchedPatient() {
        return searchedPatient;
    }

    public static void setSearchedPatient(PatientEntity searchedPatient) {
        App.searchedPatient = searchedPatient;
    }
 
    
}