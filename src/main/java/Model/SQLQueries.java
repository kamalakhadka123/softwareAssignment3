
package Model;


public class SQLQueries {
    
    public static String DROP_DATABASE_IF_EXIST = "DROP DATABASE IF EXISTS healthcaredb;";
    public static String CREATE_NEW_DATABASE = "CREATE DATABASE IF NOT EXISTS healthcaredb;";
    
    public static String USER_ENTITY_TABLE = "CREATE TABLE UserTable (\n" +
                                        "    UserID INT AUTO_INCREMENT PRIMARY KEY,\n" +
                                        "    FirstName VARCHAR(50) NOT NULL,\n" +
                                        "    LastName VARCHAR(50) NOT NULL,\n" +
                                        "    Gender VARCHAR(20),\n" +
                                        "    DateOfBirth DATE,\n" +
                                        "    EmailAddress VARCHAR(100),\n" +
                                        "    Password VARCHAR(255) NOT NULL,\n" +
                                        "    Role VARCHAR(20) NOT NULL\n" +
                                        ");";
    public static String USER_ID_AUTO_INCREMENT = "ALTER TABLE UserTable AUTO_INCREMENT = 1000;";
    
    public static String GET_USER_BY_ID = "SELECT * FROM UserTable WHERE UserID = ?";
    public static String GET_USER_BY_EMAIL = "SELECT * FROM UserTable WHERE EmailAddress = ?";
    public static String MODIFY_USER_BY_ID = "UPDATE UserTable SET firstname = ?, lastname = ?, gender = ? , dateofbirth = ?, emailaddress = ?, password = ?, role = ? WHERE UserId = ?";
    public static String REMOVE_USER_BY_ID = "DELETE From UserTable Where userid = ?";
    public static String INSERT_INTO_USER_TABLE = "INSERT INTO UserTable (FirstName, LastName, Gender, DateOfBirth, EmailAddress, Password, Role)\n" +
                                            "VALUES (?, ?, ?, ?, ?, ?, ?);";
    
    public static String CREATE_PATIENT_TABLE = "CREATE TABLE PatientTable (\n" +
                                                    "    patient_id INT AUTO_INCREMENT PRIMARY KEY,\n" +
                                                    "    firstname VARCHAR(255) NOT NULL,\n" +
                                                    "    lastname VARCHAR(255) NOT NULL,\n" +
                                                    "    gender VARCHAR(10),\n" +
                                                    "    dateOfBirth DATE,\n" +
                                                    "    address VARCHAR(255),\n" +
                                                    "    contactNum VARCHAR(20),\n" +
                                                    "    medicareNumber VARCHAR(20),\n" +
                                                    "    medicalHistory TEXT\n" +
                                                    ");";
    public static String ADD_PATIENT = "INSERT INTO PatientTable (firstname, lastname, gender, dateOfBirth, address, contactNum, medicareNumber, medicalHistory)\n"
                    + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
    public static String PATIENT_TABLE_AUTO_INCREMENT = "ALTER TABLE PatientTable AUTO_INCREMENT = 2000;";
    public static String SEARCH_PATIENT = "SELECT * From PatientTable where patient_id = ?";
    public static String UPDATE_PATIENT = "UPDATE PatientTable SET firstname = ?, lastname = ?, gender = ?, dateOfBirth = ?, address = ?, contactNum = ?, medicareNumber = ?, medicalHistory = ? WHERE patient_id = ?";
    public static String DELETE_PATIENT = "DELETE FROM PatientTable WHERE patient_id = ?";
    public static String GET_PATIENTS = "SELECT * FROM PatientTable";
    
    
    public static String CREATE_APPOINTMENT_TABLE = "CREATE TABLE AppointmentTable (\n" +
                                                "    appointment_id INT AUTO_INCREMENT PRIMARY KEY,\n" +
                                                "    appointment_date DATE NOT NULL,\n" +
                                                "    booked_service VARCHAR(100) NOT NULL,\n"
                                              + "    booking_time VARCHAR(20)," +
                                                "    patient_id INT,\n" +
                                                "    FOREIGN KEY (patient_id) REFERENCES PatientTable(patient_id)\n" +
                                                ");";
    public static String APPOINTMENT_TABLE_AUTO_INCREMENT = "ALTER TABLE AppointmentTable AUTO_INCREMENT = 3000;";
    public static String ADD_APPOINTMENT = "INSERT INTO AppointmentTable (appointment_date, booked_service, booking_time, patient_id) VALUES (?, ?, ?, ?)";
    public static String SEARCH_APPOINTMENT = "SELECT * FROM AppointmentTable WHERE booked_service = ? AND appointment_date = ? AND booking_time = ?";
    public static String DELETE_APPOINTMENT = "DELETE FROM AppointmentTable Where appointment_id = ?";
    public static String GET_ALL_APPOINTMENTS = "SELECT * FROM AppointmentTable";
    
    public static String CREATE_INVOICE_TABLE = "CREATE TABLE BillingInvoiceTable (\n" +
                                        "    invoice_id INT AUTO_INCREMENT PRIMARY KEY,\n" +
                                        "    amount_due DECIMAL(10, 2) NOT NULL,\n" +
                                        "    invoice_date DATE NOT NULL,\n" +
                                        "    services_provided TEXT NOT NULL,\n" +
                                        "    appointment_id INT,\n" +
                                        "    patient_id INT,\n" +
                                        "    FOREIGN KEY (appointment_id) REFERENCES AppointmentTable(appointment_id),\n" +
                                        "    FOREIGN KEY (patient_id) REFERENCES PatientTable(patient_id)\n" +
                                        ");";
    public static String INVOICE_TABLE_AUTO_INCREMENT = "ALTER TABLE BillingInvoiceTable AUTO_INCREMENT = 4000;";
    public static String ADD_INVOICE = "INSERT INTO BillingInvoiceTable (amount_due, invoice_date, services_provided, appointment_id, patient_id) VALUES (?, ?, ?, ?, ?)";
    public static String GET_ALL_PATIENT_INVOICE = "SELECT * FROM BillingInvoiceTable WHERE patient_id = ?";
    public static String GET_ALL_INVOICE = "SELECT * FROM BillingInvoiceTable";
}
