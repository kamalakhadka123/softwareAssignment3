
package Model;

import java.sql.Date;

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

    public long getPatientId() {
        return patientId;
    }

    public void setPatientId(long patientId) {
        this.patientId = patientId;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public java.sql.Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(java.sql.Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContactNum() {
        return contactNumber;
    }

    public void setContactNum(String contactNum) {
        this.contactNumber = contactNum;
    }

    public String getMedicareNumber() {
        return medicareNumber;
    }

    public void setMedicareNumber(String medicareNumber) {
        this.medicareNumber = medicareNumber;
    }

    public String getMedicalHistory() {
        return medicalHistory;
    }

    public void setMedicalHistory(String medicalHistory) {
        this.medicalHistory = medicalHistory;
    }

    
    
    
    
    
    
}
