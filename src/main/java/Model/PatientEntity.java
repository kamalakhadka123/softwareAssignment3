
package Model;

import java.sql.Date;

/**
 * Represents a patient entity with various attributes.
 */
public class PatientEntity {
    private long patientId;
    private String firstname;
    private String lastname;
    private String gender;
    private java.sql.Date dateOfBirth;
    private String address;
    private String contactNumber;
    private String medicareNumber;
    private String medicalHistory;

    /**
     * Constructs a new PatientEntity object with the specified attributes.
     *
     * @param patientId      The unique ID of the patient.
     * @param firstname      The first name of the patient.
     * @param lastname       The last name of the patient.
     * @param gender         The gender of the patient.
     * @param dateOfBirth    The date of birth of the patient.
     * @param address        The address of the patient.
     * @param contactNum     The contact number of the patient.
     * @param medicareNumber The Medicare number of the patient.
     * @param medicalHistory The medical history of the patient.
     */
    public PatientEntity(long patientId, String firstname, String lastname, String gender, java.sql.Date dateOfBirth, String address, String contactNum, String medicareNumber, String medicalHistory) {
        this.patientId = patientId;
        this.firstname = firstname;
        this.lastname = lastname;
        this.gender = gender;
        this.dateOfBirth = dateOfBirth;
        this.address = address;
        this.contactNumber = contactNum;
        this.medicareNumber = medicareNumber;
        this.medicalHistory = medicalHistory;
    }

    /**
     * Gets the unique ID of the patient.
     *
     * @return The patient's unique ID.
     */
    public long getPatientId() {
        return patientId;
    }

    /**
     * Sets the unique ID of the patient.
     *
     * @param patientId The patient's unique ID to set.
     */
    public void setPatientId(long patientId) {
        this.patientId = patientId;
    }

    /**
     * Gets the first name of the patient.
     *
     * @return The first name of the patient.
     */
    
    public String getFirstname() {
        return firstname;
    }
    
    /**
     * Sets the first name of the patient.
     *
     * @param firstname The first name to set.
     */
    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    /**
     * Gets the last name of the patient.
     *
     * @return The last name of the patient.
     */
    public String getLastname() {
        return lastname;
    }

    /**
     * Sets the last name of the patient.
     *
     * @param lastname The last name to set.
     */
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    /**
     * Gets the gender of the patient.
     *
     * @return The gender of the patient.
     */
    public String getGender() {
        return gender;
    }

    /**
     * Sets the gender of the patient.
     *
     * @param gender The gender to set.
     */
    public void setGender(String gender) {
        this.gender = gender;
    }

    /**
     * Gets the date of birth of the patient.
     *
     * @return The date of birth of the patient.
     */
    public java.sql.Date getDateOfBirth() {
        return dateOfBirth;
    }

    /**
     * Sets the date of birth of the patient.
     *
     * @param dateOfBirth The date of birth to set.
     */
    public void setDateOfBirth(java.sql.Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    /**
     * Gets the address of the patient.
     *
     * @return The address of the patient.
     */
    public String getAddress() {
        return address;
    }

    /**
     * Sets the address of the patient.
     *
     * @param address The address to set.
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Gets the contact number of the patient.
     *
     * @return The contact number of the patient.
     */
    public String getContactNum() {
        return contactNumber;
    }

    /**
     * Sets the contact number of the patient.
     *
     * @param contactNum The contact number to set.
     */
    public void setContactNum(String contactNum) {
        this.contactNumber = contactNum;
    }

     /**
     * Gets the Medicare number of the patient.
     *
     * @return The Medicare number of the patient.
     */
    public String getMedicareNumber() {
        return medicareNumber;
    }
    

    /**
     * Sets the Medicare number of the patient.
     *
     * @param medicareNumber The Medicare number to set.
     */
    public void setMedicareNumber(String medicareNumber) {
        this.medicareNumber = medicareNumber;
    }
    
    /**
     * Gets the medical history of the patient.
     *
     * @return The medical history of the patient.
     */

    public String getMedicalHistory() {
        return medicalHistory;
    }

    /**
     * Sets the medical history of the patient.
     *
     * @param medicalHistory The medical history to set.
     */
    public void setMedicalHistory(String medicalHistory) {
        this.medicalHistory = medicalHistory;
    }

    
    
    
    
    
    
}
