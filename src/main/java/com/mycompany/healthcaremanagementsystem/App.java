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

/**
 * Main application class for the Healthcare Management System.
 */
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

    /**
     * Starts the application by initializing the main stage and displaying the login window.
     *
     * @param stage The primary stage for the application.
     * @throws IOException If an I/O exception occurs while loading the login window.
     */
    @Override
    public void start(Stage stage) throws IOException {
        mainAppStage = stage;
        mainAppStage.setTitle("HealthLink Management System");
        changeWindow("loginForUser.fxml");
        mainAppStage.show();
    }

    /**
     * Changes the current window by loading the FXML file and updating the stage with the new scene.
     *
     * @param fxmlFileName The name of the FXML file to load.
     */
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

    /**
     * Retrieves the MySQL database connection instance.
     *
     * @return The MySQL database connection instance.
     */
    public static MySQLDatabaseConnection getDb() {
        return mysqlDB;
    }

    /**
     * Retrieves the currently logged-in user.
     *
     * @return The currently logged-in user.
     */
    public static UsersEntity getLoggedInUser() {
        return loggedInUser;
    }

    /**
     * Sets the currently logged-in user.
     *
     * @param loggedInUser The user to set as currently logged-in.
     */
    public static void setLoggedInUser(UsersEntity loggedInUser) {
        App.loggedInUser = loggedInUser;
    }
    

    /**
     * Retrieves the user being searched for.
     *
     * @return The user being searched for.
     */
    public static UsersEntity getSearchedUser() {
        return searchedUser;
    }

    /**
     * Sets the user being searched for.
     *
     * @param searchedUser The user to set as the one being searched for.
     */
    public static void setSearchedUser(UsersEntity searchedUser) {
        App.searchedUser = searchedUser;
    }

    /**
     * Retrieves the patient being searched for.
     *
     * @return The patient being searched for.
     */
    public static PatientEntity getSearchedPatient() {
        return searchedPatient;
    }

    /**
     * Sets the patient being searched for.
     *
     * @param searchedPatient The patient to set as the one being searched for.
     */
    public static void setSearchedPatient(PatientEntity searchedPatient) {
        App.searchedPatient = searchedPatient;
    }
 
    
}
