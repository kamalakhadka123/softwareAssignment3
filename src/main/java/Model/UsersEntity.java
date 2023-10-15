
package Model;

import java.sql.Date;

/**
 * Represents a user entity with various attributes.
 */
public class UsersEntity {
    private long id;
    private String firstName;
    private String lastName;
    private String gender;
    private java.sql.Date dateOfBirth;
    private String email;
    private String password;
    private String role;

    /**
     * Constructs a new UsersEntity object with the specified attributes.
     *
     * @param id          The unique ID of the user.
     * @param firstName   The first name of the user.
     * @param lastName    The last name of the user.
     * @param gender      The gender of the user.
     * @param dateOfBirth The date of birth of the user.
     * @param email       The email address of the user.
     * @param password    The password of the user.
     * @param role        The role of the user (e.g., admin, patient).
     */
    public UsersEntity(long id, String firstName, String lastName, String gender, java.sql.Date dateOfBirth, String email, String password, String role) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.dateOfBirth = dateOfBirth;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    /**
     * Gets the unique ID of the user.
     *
     * @return The user's unique ID.
     */
    public long getId() {
        return id;
    }

    /**
     * Sets the unique ID of the user.
     *
     * @param id The user's unique ID to set.
     */
    public void setId(long id) {
        this.id = id;
    }
    

    /**
     * Gets the first name of the user.
     *
     * @return The first name of the user.
     */
    public String getFirstName() {
        return firstName;
    }
    
    /**
     * Sets the first name of the user.
     *
     * @param firstName The first name to set.
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Gets the last name of the user.
     *
     * @return The last name of the user.
     */
    public String getLastName() {
        return lastName;
    }

     /**
     * Sets the last name of the user.
     *
     * @param lastName The last name to set.
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Gets the gender of the user.
     *
     * @return The gender of the user.
     */
    public String getGender() {
        return gender;
    }

     /**
     * Sets the gender of the user.
     *
     * @param gender The gender to set.
     */
    public void setGender(String gender) {
        this.gender = gender;
    }

    /**
     * Gets the date of birth of the user.
     *
     * @return The date of birth of the user.
     */
    public java.sql.Date getDateOfBirth() {
        return dateOfBirth;
    }

    /**
     * Sets the date of birth of the user.
     *
     * @param dateOfBirth The date of birth to set.
     */
    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

     /**
     * Gets the email address of the user.
     *
     * @return The email address of the user.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the email address of the user.
     *
     * @param email The email address to set.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Gets the password of the user.
     *
     * @return The password of the user.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the password of the user.
     *
     * @param password The password to set.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Gets the role of the user (e.g., admin, patient).
     *
     * @return The role of the user.
     */
    public String getRole() {
        return role;
    }

    /**
     * Sets the role of the user (e.g., admin, patient).
     *
     * @param role The role to set.
     */
    public void setRole(String role) {
        this.role = role;
    }
    
    
    
    
}
